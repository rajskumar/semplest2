package semplest.server.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import semplest.client.nio.NIOClient;
import semplest.client.nio.NIOResponseHandler;
import semplest.server.protocol.ProtocolJSON;
import semplest.server.protocol.ProtocolSocketDataObject;

public class PingService implements Runnable
{
	private final Socket pingSocket;
	private final int frequencyMS;
	private final String serviceName;
	ProtocolSocketDataObject pingData = null;
	private ProtocolJSON json = new ProtocolJSON();
	static final Logger logger = Logger.getLogger(PingService.class);
	
	//
	public static void main(String[] args)
	{
		
	}
	
	
	public PingService(Socket pingSocket, String serviceName, int frequencyMS)
	{
		this.pingSocket = pingSocket;
		this.frequencyMS = frequencyMS;
		this.serviceName = serviceName;
		//
		pingData = new ProtocolSocketDataObject();
		pingData.setHeader(ProtocolJSON.SEMplest_PING);
		pingData.setclientServiceName(serviceName);
		
	}
	@Override
	public void run()
	{
		try
		{
			byte[] bytes = new byte[200]; //178 bytes coming back in response
			String jsonStr = json.createJSONFromSocketDataObj(pingData);
			byte[] returnData = ProtocolJSON.createBytePacketFromString(jsonStr);
			//Starting pinging
			while (true)
			{
				logger.debug("Send Ping");
				pingSocket.getOutputStream().write(returnData);
				pingSocket.getOutputStream().flush();
				//wait for return data
				int numBytes = pingSocket.getInputStream().read(bytes);
				logger.debug("Read bytes pingSocket = " + numBytes + ":" + bytes.toString());
				Thread.sleep(frequencyMS);
			}
		}
		catch (InterruptedException e)
		{
			logger.info("Ping Service failed for " + this.serviceName);
			e.printStackTrace();
		}
		catch (IOException e)
		{
			logger.info("Ping Service failed for " + this.serviceName);
			e.printStackTrace();
		}
		
	}

}
