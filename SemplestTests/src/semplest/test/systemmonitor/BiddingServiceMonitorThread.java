package semplest.test.systemmonitor;

import java.util.HashMap;

import semplest.server.protocol.ProtocolEnum.ServiceStatus;
import semplest.services.client.api.SemplestBiddingServiceClient;
import semplest.test.systemmonitor.MonitorData.SERVER;
import semplest.test.systemmonitor.MonitorData.SERVICE;

public class BiddingServiceMonitorThread implements Runnable {

	private long sleep_time;
	private HashMap<SERVER, MonitorData> monitorData;
	private boolean stop = false;	
	
	private SERVICE service = SERVICE.Bidding;
	private String clientTimeout = "30000"; 
	private Notification alert = new Notification();

	public BiddingServiceMonitorThread(long interval_min, HashMap<SERVER, MonitorData> monitorDataTemplate) {
		this.sleep_time = interval_min * 60 * 1000;		
		monitorData = monitorDataTemplate;
	}
	
	@Override
	public void run() {		
		while(!stop){	
			try {
				for(SERVER s : SERVER.values()){
					String esbUrl = monitorData.get(s).getEsbUrl();
					SemplestBiddingServiceClient biddingService = new SemplestBiddingServiceClient(esbUrl, clientTimeout);
					try {
						String ret = biddingService.checkStatus(clientTimeout);
						
						System.out.println(service.name() + " is running fine on " + s.name());
						monitorData.get(s).setServiceStatus(service, ServiceStatus.Good);
						if(monitorData.get(s).getServiceStatus(service).goesUp()){
							//if the service used to be down, but just came back up, send notification
							alert.sendNotification(s, service, null);
						}
					} 
					catch (Exception e) {
						e.printStackTrace();
						System.out.println(service.name() + " not running on " + s.name());						
						//The service is not healthy
						monitorData.get(s).setServiceStatus(service, ServiceStatus.Bad);				
						if(monitorData.get(s).getServiceStatus(service).goesDown()){
							//if the service just went down. send notification
							alert.sendNotification(s, service, e.getMessage());
						}
					}	
				}				
				
				Thread.sleep(sleep_time);	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}			
	}		
}