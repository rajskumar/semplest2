package semplest.service.bidding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import semplest.bidding.estimation.EstimatorData;
import semplest.server.protocol.ProtocolEnum;
import semplest.server.protocol.ProtocolEnum.AdEngine;
import semplest.server.protocol.adengine.AdEngineID;
import semplest.server.protocol.adengine.AdEngineInitialData;
import semplest.server.protocol.adengine.BidElement;
import semplest.server.protocol.adengine.BudgetObject;
import semplest.server.protocol.adengine.KeywordDataObject;
import semplest.server.protocol.adengine.TrafficEstimatorObject;
import semplest.server.service.SemplestConfiguration;
import semplest.server.service.springjdbc.SemplestDB;
import semplest.service.google.adwords.GoogleAdwordsServiceImpl;

import com.google.api.adwords.v201109.cm.KeywordMatchType;

public class TestBidding {

	private String google = ProtocolEnum.AdEngine.Google.name();
	private String msn = ProtocolEnum.AdEngine.MSN.name();

	private String networkSetting = ProtocolEnum.NetworkSetting.SearchOnly.name();


	//private static Gson gson = new Gson();
	private static final Logger logger = Logger.getLogger(BidGeneratorObj.class);



	private int maxRetry;// = 10; // maximum number of times we will
	private int sleepPeriod;// = 500; // in millisecond
	private int sleepBackOffTime;// = 1000; // after k-th failure wait for sleepPeriod + k*sleepBackOffTime ms

	private Long maxMicroBid;// = 3000000L; 
	private Long stepAboveFpCPC;// = 500000L;
	private Long defaultMicroBid;// = 1000000L;
	private Long maxDefaultMicroBid;// = 1500000L; 


	// traffic estimator bid steps
	private Long stepFirst;// = 100000L; 
	private Long stepSecond;// = 600000L;
	private Long stepRest;// = 800000L;


	private String googleAccountID = null;
	private Long msnAccountID = null;
	private Long campaignID;
	private Long adGroupID;

	private GoogleAdwordsServiceImpl clientGoogle;
	private KeywordDataObject[] keywordDataObjs;
	private KeywordDataObject keywordDataObj;

	private HashMap<String,Long> wordIDMap;
	private HashMap<String,Long> wordBidMap;


	private HashMap<String,Long> firstPageCPCMap;
	private HashMap<String,Double> qualityScoreMap;

	private HashSet<String> compKeywords; // competitive
	private HashSet<String> nonCompKeywords; // non-competitive
	private HashSet<String> noInfoKeywords; // competitive but no-info
	private HashSet<String> notSelectedKeywords; // competitive but not selected by optimizer

	/*
	private HashMap<String,Long> compKeywordBids; // competitive
	private HashMap<String,Long> nonCompKeywordBids; // non-competitive
	private HashMap<String,Long> noInfoKeywordBids; // competitive but no-info
	private HashMap<String,Long> notSelectedKeywordBids; // competitive but not selected by optimizer
	 */

	private HashMap<String,EstimatorData> clickDataMap = new HashMap<String,EstimatorData>();
	private HashMap<String,EstimatorData> costDataMap = new HashMap<String,EstimatorData>();

	public TestBidding() throws Exception { // constructor
		try
		{
			clientGoogle = new GoogleAdwordsServiceImpl();
		}
		catch (Exception e)
		{
			logger.error("Unable to create Google Client " + e.getMessage());
			//e.printStackTrace();
			throw new Exception("Unable to create Google Client " + e.getMessage());
		}

		try{
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Unable to sleep! May have error in default config values! "+e.getMessage());
		}
		maxRetry = (Integer) SemplestConfiguration.configData.get("SemplestBiddingMaxRetry");
		sleepPeriod = (Integer) SemplestConfiguration.configData.get("SemplestBiddingSleepPeriod");
		sleepBackOffTime = (Integer) SemplestConfiguration.configData.get("SemplestBiddingSleepBackOffTime");

		maxMicroBid = (Long) SemplestConfiguration.configData.get("SemplestBiddingMaxMicroBid"); 
		stepAboveFpCPC = (Long) SemplestConfiguration.configData.get("SemplestBiddingStepAboveFpCPC"); 
		defaultMicroBid = (Long) SemplestConfiguration.configData.get("SemplestBiddingDefaultMicroBid"); 
		maxDefaultMicroBid = (Long) SemplestConfiguration.configData.get("SemplestBiddingMaxDefaultMicroBid"); 

		stepFirst = (Long) SemplestConfiguration.configData.get("SemplestBiddingStepFirst");
		stepSecond = (Long) SemplestConfiguration.configData.get("SemplestBiddingStepSecond");
		stepRest = (Long) SemplestConfiguration.configData.get("SemplestBiddingStepRest");



		wordIDMap = new HashMap<String,Long>();
		wordBidMap = new HashMap<String,Long>();


		firstPageCPCMap = new HashMap<String,Long>();
		qualityScoreMap = new HashMap<String,Double>();

		compKeywords = new HashSet<String>(); // competitive
		nonCompKeywords = new HashSet<String>(); // non-competitive
		noInfoKeywords = new HashSet<String>(); // competitive but no-info
		notSelectedKeywords = new HashSet<String>(); // competitive but not selected by optimizer

		/*
		compKeywordBids = new HashMap<String,Long>(); // competitive
		nonCompKeywordBids = new HashMap<String,Long>(); // non-competitive
		noInfoKeywordBids = new HashMap<String,Long>(); // competitive but no-info
		notSelectedKeywordBids = new HashMap<String,Long>(); // competitive but not selected by optimizer
		 */


		clickDataMap = new HashMap<String,EstimatorData>();
		costDataMap = new HashMap<String,EstimatorData>();
	}


	public HashMap<String,AdEngineInitialData> getInitialValues(Integer promotionID, 
			ArrayList<String> searchEngine) throws Exception{

		HashMap<String,AdEngineInitialData> initValues = new HashMap<String,AdEngineInitialData>();
		for (String se : searchEngine) {
			if (!AdEngine.existsAdEngine(se)){
				throw new Exception("Ad engine "+ se + " Not Found");
			}
			// Long defaultMicroBid = 1000000L; // $1.00
			AdEngineInitialData adEngineInitialDataObject = new AdEngineInitialData();
			adEngineInitialDataObject.setSemplestMatchType(ProtocolEnum.SemplestMatchType.Exact.name());
			adEngineInitialDataObject.setDefaultMicroBid(defaultMicroBid);
			adEngineInitialDataObject.setNetworkSetting(networkSetting);
			//			adEngineInitialDataObject.setBiddingMethod(biddingMethod)
			// SemplestDB.storeDefaultBid(promotionID, se, defaultMicroBid);
			initValues.put(se, adEngineInitialDataObject);
		}

		return initValues;
	}



	public Boolean setBidsInitial(Integer promotionID, String searchEngine, BudgetObject budgetData) throws Exception {

		/* ******************************************************************************************* */
		// declarations
		int k;
		TrafficEstimatorObject o = null, o2 = null;

		logger.info("setBidsInitial called for ad engine "+searchEngine);

		/* ******************************************************************************************* */
		// 0. Check if Ad engine name is valid
		if (!AdEngine.existsAdEngine(searchEngine)){
			throw new Exception("Ad engine "+ searchEngine + " Not Found");
		}


		/* ******************************************************************************************* */
		// 1. Database call: get campaign specific IDs	
		try{ 
			AdEngineID adEngineInfo = SemplestDB.getAdEngineID(promotionID, searchEngine); 
			if (searchEngine.equalsIgnoreCase(google)){
				googleAccountID = String.valueOf(adEngineInfo.getAccountID());
			} else if(searchEngine.equalsIgnoreCase(msn)){
				msnAccountID = adEngineInfo.getAccountID();
			} else {
				throw new Exception("Ad engine type "+searchEngine+" is not yet implemented!!");
			}
			campaignID = adEngineInfo.getCampaignID();
			adGroupID = adEngineInfo.getAdGroupID();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Failed to get AdEngineID from the database. "+e.getMessage());
			throw new Exception("Failed to get AdEngineID from the database. "+e.getMessage());
		}

		logger.info("Got campaign related IDs from the database" + " Google Account" + googleAccountID +  ":" + "CampaignID = " + String.valueOf(campaignID) + ":" + String.valueOf(adGroupID));





		/* ******************************************************************************************* */
		// 2. Database call: get remaining days and budget
		// now get it as function argument
		//BudgetObject budgetData = SemplestDB.getBudget(promotionID);
		Double remBudget = budgetData.getRemainingBudgetInCycle();
		Integer remDays = budgetData.getRemainingDays();
		double targetDailyBudget = (remBudget/remDays)*7; // use weekly budget as target daily budget



		/* ******************************************************************************************* */
		// 3. [google] API call: get adgroup criterion for all keywords
		if(searchEngine.equalsIgnoreCase(google)){
			k=0;
			while(true) {
				Thread.sleep(sleepPeriod+k*sleepBackOffTime);
				try {
					keywordDataObjs = clientGoogle.getAllBiddableAdGroupCriteria(googleAccountID, adGroupID, true);
					break;
				} catch (Exception e) {
					if (k<=maxRetry) {
						//e.printStackTrace();
						logger.error("Received exception getAllBiddableAdGroupCriteria AccountID = " + googleAccountID + " AdGroupID = " +
								String.valueOf(adGroupID) + ": will retry..., k="+k+e.getMessage());
						k++;				
					} else {
						//e.printStackTrace();
						logger.error("Failed to get BiddableAdGroupCriteria from Google after "+k+" efforts "+e.getMessage());
						throw new Exception("Failed to get BiddableAdGroupCriteria from Google after "+k+" efforts "+e.getMessage());
					}
				} // try-catch
			} // while(true)

			for(KeywordDataObject b: keywordDataObjs){
				wordIDMap.put(b.getKeyword(),b.getBidID());
			}

			logger.info("Got biddable adgroup criterion from Google.");
		} // if(searchEngine.equalsIgnoreCase(google))





		/* ******************************************************************************************* */
		// 4. [google] Decide competitive, non-competitive and no-info
		if(searchEngine.equalsIgnoreCase(google)){
			for(int i=0; i<keywordDataObjs.length; i++){
				keywordDataObj = keywordDataObjs[i];
				if(keywordDataObj.getFirstPageCpc()==null){
					// logger.info((i+1)+": Received no firstPageCPC info for "+keywordDataObj.getKeyword());
					noInfoKeywords.add(keywordDataObj.getKeyword());
				} else {
					// logger.info((i+1)+": "+keywordDataObj.getKeyword()+": "+
					//		keywordDataObj.getFirstPageCpc()*1e-6 + ": " + keywordDataObj.getQualityScore());
					firstPageCPCMap.put(keywordDataObj.getKeyword(), new Long(keywordDataObj.getFirstPageCpc()));
					qualityScoreMap.put(keywordDataObj.getKeyword(), new Double(Math.pow(keywordDataObj.getQualityScore(),1)));
					if(keywordDataObj.isIsEligibleForShowing()) {
						compKeywords.add(keywordDataObj.getKeyword());
					} else {
						nonCompKeywords.add(keywordDataObj.getKeyword());
					} 
				} // if(keywordDataObj.getFirstPageCpc()==null)
			} // for(int i=0; i<keywordDataObjs.length; i++)

			logger.info("Decided competitive keywords");
		} // if(searchEngine.equalsIgnoreCase(google))



		/* ******************************************************************************************* */
		// 5. SE API call: get traffic estimator data

		/* *************************************** */
		//    a. [google] only competitive keywords
		//        i. some keywords are pushed back to non-competitive category if
		//           information available is not useful

		if(searchEngine.equalsIgnoreCase(google)){
			if (compKeywords.size()>0) {
				try {
					o = getTrafficEstimatorDataForGoogle();
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("Failed to get Google traffic estimator data. "+e.getMessage());
					throw new Exception("Failed to get Google traffic estimator data. "+e.getMessage());
				}
				logger.info("Got traffic estimator data.");
			} else {
				logger.info("No traffic estimator data to be fetched.");
			}
		} // if(searchEngine.equalsIgnoreCase(google))

		/* *************************************** */
		//    b. [msn] for all keywords and compute firstPage CPC from the data

		if(searchEngine.equalsIgnoreCase(msn)){
			throw new Exception("Method not implemented for MSN yet!!");
		} // if(searchEngine.equalsIgnoreCase(msn))







		/* ******************************************************************************************* */
		// 6. [google] Compute bids for competitive keywords

		// TODO: bidOptimizer to be added
		// for the time being put all keywords to the category of keywords not selected by optimizer

		// imp: consider the case that compKeywords is empty

		for(String s : compKeywords){
			notSelectedKeywords.add(s);
		}
		compKeywords.clear();

		// logger.info("Computed bids for competitive keywords.");
		logger.info("No competitive keywords.");



		/* ******************************************************************************************* */
		// 7. [google] Compute bids for competitive keywords which optimizer didn't select
		//    a. Bid $0.5 above firstPage CPC if below $3.00	
		if (notSelectedKeywords.size()>0) {
			for(String s : notSelectedKeywords){
				wordBidMap.put(s,Math.min(firstPageCPCMap.get(s)+stepAboveFpCPC,maxMicroBid));
			}
			logger.info("Computed bids for competitive keywords which optimizer didn't select for bidding.");
		}


		/* ******************************************************************************************* */
		// 8. Compute bits for all other keywords with firstPage CPC
		if (nonCompKeywords.size()>0) {
			for(String s : nonCompKeywords){
				wordBidMap.put(s,Math.min(firstPageCPCMap.get(s)+stepAboveFpCPC,maxMicroBid));
			}
			logger.info("Computed bids for rest of the keywords with first page cpc.");
		}

		/* ******************************************************************************************* */
		// 9. For the rest of keywords without firstPage CPC leave out for bidding with default bid 

		if(noInfoKeywords.size()>0) {
			for(String s : noInfoKeywords){
				wordBidMap.put(s,null); // bid using default bid
			}
			logger.info("Left the remaining keywords for default bidding");
		}




		/* ******************************************************************************************* */
		// 10. Use traffic estimator to compute average CPC based on these bids and use that as default bid for the campaign
		//    a. [google] use only competitive (both used and unused by optimizer) keywords
		//    b. [msn] use all keywords


		// defaultMicroBid = 1000000L;
		Long totalDailyCost = 0L;
		Float totalDailyClick = 0F;

		if(searchEngine.equalsIgnoreCase(google)){
			try{
				o2 = getTrafficEstimationForKeywordsGoogle(googleAccountID, campaignID, 
						KeywordMatchType.EXACT,	wordBidMap);
			} catch (Exception e) {
				logger.error("Failed to get Google traffic estimator data. "+e.getMessage());
				//e.printStackTrace();
				throw new Exception("Failed to get Google traffic estimator data. "+e.getMessage());
			}
			if (o2!=null) {
				String[] words = o2.getListOfKeywords();
				for (int i=0; i < words.length; i++)
				{
					HashMap<Long, TrafficEstimatorObject.BidData> points = o2.getMapOfPoints(words[i],KeywordMatchType.EXACT.getValue());
					Iterator<Long> bidIT = points.keySet().iterator();
					while(bidIT.hasNext())
					{
						Long abid= bidIT.next();
						totalDailyClick += (points.get(abid).getMaxClickPerDay() + points.get(abid).getMinClickPerDay())/2;
						totalDailyCost += (points.get(abid).getMaxTotalDailyMicroCost() + points.get(abid).getMinTotalDailyMicroCost())/2;
					}
				}
				if(totalDailyClick>0.01){
					defaultMicroBid = (((Long) new Double(totalDailyCost.doubleValue()/totalDailyClick).longValue())/10000L)*10000L;
					defaultMicroBid = Math.min(defaultMicroBid, maxDefaultMicroBid);
				}
			}

		} // if(searchEngine.equalsIgnoreCase(google))

		logger.info("Computed expected cpc and set default bid at "+defaultMicroBid);

		/* ******************************************************************************************* */
		// 11. [google] Database call: write adgroup criterion
		if(keywordDataObjs!=null && keywordDataObjs.length>0) {
			if(searchEngine.equalsIgnoreCase(google)){
				try {
					SemplestDB.storeKeywordDataObjects(promotionID, google,
							new ArrayList<KeywordDataObject>(Arrays.asList(keywordDataObjs)));
				} catch (Exception e) {
					logger.error("Failed to store Google adGroupCriterion data to database. "+e.getMessage());
					//e.printStackTrace();
					throw new Exception("Failed to store Google adGroupCriterion data to database. "+e.getMessage());
				}
			} // if(searchEngine.equalsIgnoreCase(google))

			logger.info("Stored adgroup criterion data to database for Google");
		}


		/* ******************************************************************************************* */
		// 12. Database call: write traffic estimator data
		if(searchEngine.equalsIgnoreCase(google)){
			if(o!=null) {
				try {
					SemplestDB.storeTrafficEstimatorData(promotionID, google, o);
				} catch (Exception e) {
					logger.error("Failed to write traffic estimator data "+e.getMessage());
					//e.printStackTrace();
					throw new Exception("Failed to write traffic estimator data "+e.getMessage());
				}
				logger.info("Stored traffic estimator data to database");
			} else {
				logger.info("No traffic estimator data to write to the database");
			}
		} // if(searchEngine.equalsIgnoreCase(google))

		if(searchEngine.equalsIgnoreCase(msn)){
			throw new Exception("Method not implemented for MSN yet!!");
		} // if(searchEngine.equalsIgnoreCase(msn))





		/* ******************************************************************************************* */
		// 13. Database call: write default bid for campaign
		// remember to update the bids for the words with default bid with database


		if(defaultMicroBid != SemplestDB.getDefaultBid(promotionID, searchEngine)) {
			SemplestDB.storeDefaultBid(promotionID, searchEngine, defaultMicroBid);
			SemplestDB.UpdateDefaultBidForKeywords(promotionID, searchEngine);
		}

		logger.info("Stroed default bid "+ defaultMicroBid +" to databse and requested for updating all bids for keywords with default bid.");




		/* ******************************************************************************************* */
		// 14. Database call: write bid, matchType, competition status
		ArrayList<BidElement> bidsMatchType = new ArrayList<BidElement>();

		String competitiveType="";
		String matchType = ProtocolEnum.SemplestMatchType.Exact.name(); 
		Boolean isActive = true;
		Boolean isNegative = false;

		Iterator<String> keyIT = wordBidMap.keySet().iterator();
		while(keyIT.hasNext())
		{
			String word = keyIT.next();
			if (compKeywords.contains(word)){
				competitiveType = ProtocolEnum.SemplestCompetitionType.Comp.name();
			} else if (notSelectedKeywords.contains(word)){
				competitiveType = ProtocolEnum.SemplestCompetitionType.NotSelected.name();
			} else if (nonCompKeywords.contains(word)){
				competitiveType = ProtocolEnum.SemplestCompetitionType.NonComp.name();
			} else if(noInfoKeywords.contains(word)){
				competitiveType = ProtocolEnum.SemplestCompetitionType.NoInfo.name();
			} else {
				throw new Exception("Unknown competition type. Internal error in bidding service!");
			}
			bidsMatchType.add(new BidElement(word, wordIDMap.get(word), 
					(wordBidMap.get(word)==null) ? defaultMicroBid : wordBidMap.get(word), 
							matchType, competitiveType, wordBidMap.get(word)==null, isActive, isNegative)); 
		}


		try{
			if (bidsMatchType.size()>0) {
				SemplestDB.storeBidObjects(promotionID, searchEngine, bidsMatchType);
				logger.info("Stroed bid data to the databse for "+bidsMatchType.size()+" keywords.");
			} else {
				logger.info("No bid data to write to the databse");
			}
		} catch (Exception e) {
			logger.error("ERROR: Unable to store bid data to the database. "+e.getMessage());
			//e.printStackTrace();
			throw new Exception("Failed to store bid data to the database. "+e.getMessage());
		}





		/* ******************************************************************************************* */
		// 15. Database call: write targeted daily budget etc
		try{
			SemplestDB.storeTargetedDailyBudget(promotionID, searchEngine, totalDailyCost, totalDailyClick.intValue());
			logger.info("Stroed targeted daily budget data to the databse");
		} catch (Exception e) {
			logger.error("ERROR: Unable to store targeted daily budget data to the database. "+e.getMessage());
			//e.printStackTrace();
			throw new Exception("Failed to store targeted daily budget data to the database ."+e.getMessage());
		}




		/* ******************************************************************************************* */
		// 16. SE API call: Update matchType, bid for keywords
		if(searchEngine.equalsIgnoreCase(google)){
			keyIT = wordBidMap.keySet().iterator();
			while(keyIT.hasNext())
			{
				String word = keyIT.next();
				k=0;
				while(true) {
					Thread.sleep(sleepPeriod+k*sleepBackOffTime);
					try {
						clientGoogle.setBidForKeyWord(googleAccountID, wordIDMap.get(word), adGroupID,wordBidMap.get(word));
						logger.info("Updated bid for keyword "+word+" via Google API with bid value "+wordBidMap.get(word));
						break;
					} catch (Exception e) {
						if (k<=maxRetry) {
							//e.printStackTrace();
							logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
							k++;				
						} else {
							e.printStackTrace();
							logger.error("Failed to update bids for keyword "+word+" "+e.getMessage());
							throw new Exception("Failed to update bids for keyword "+word+" "+e.getMessage());
						}
					} // try-catch
				} // while(true) 
			}
		} // if(searchEngine.equalsIgnoreCase(google))

		if(searchEngine.equalsIgnoreCase(msn)){
			throw new Exception("Method not implemented for MSN yet!!");
		} // if(searchEngine.equalsIgnoreCase(msn))

		logger.info("Updated bids and match type for keywords via the search engine API.");





		/* ******************************************************************************************* */
		// 17. SE API call: Update default bid for campaign
		if(searchEngine.equalsIgnoreCase(google)){
			k=0;
			while(true) {
				Thread.sleep(sleepPeriod+k*sleepBackOffTime);
				try {
					clientGoogle.updateDefaultBid(googleAccountID, adGroupID, defaultMicroBid);
					break;
				} catch (Exception e) {
					if (k<=maxRetry) {
						//e.printStackTrace();
						logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
						k++;				
					} else {
						//e.printStackTrace();
						logger.error("Failed to update default microBid via Google API. "+e.getMessage());
						throw new Exception("Failed to update default microBid via Google API. "+e.getMessage());
					}
				} // try-catch
			} // while(true) 
		}
		logger.info("Updated the default bid via search engine API.");



		return new Boolean(true);

	} // setBidsInitial()


private TrafficEstimatorObject getTrafficEstimatorDataForGoogle() throws Exception{
		
		// declare the variables
		TrafficEstimatorObject o;
		int k;

		// get info for the first page CPC point
		HashMap<String, Long> bids = new HashMap<String, Long>();
		for (String s : compKeywords){
			bids.put(s, firstPageCPCMap.get(s)+stepFirst); // stepFirst above fp CPC
		}
		logger.info("Number of initial competitive keywords: "+bids.size());

		
		k=0;
		while(true) {
			Thread.sleep(sleepPeriod+k*sleepBackOffTime);
			try {
				logger.info("Fetching traffic estimator data for Point 1");
				o = getTrafficEstimationForKeywordsGoogle(googleAccountID, campaignID, KeywordMatchType.EXACT, bids);
				break;
			} catch (Exception e) {
				if (k<=maxRetry) {
					//e.printStackTrace();
					logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
					k++;				
				} else {
					//e.printStackTrace();
					logger.error("Failed to get traffic estimator data from Google after "+k+" efforts ."+e.getMessage());
					throw new Exception("Failed to get traffic estimator data from Google after "+k+" efforts ."+e.getMessage());
				}
			}
		}
		
		String[] words = o.getListOfKeywords();
		for (int i=0; i < words.length; i++)
		{
			HashMap<Long, TrafficEstimatorObject.BidData> points = o.getMapOfPoints(words[i],KeywordMatchType.EXACT.getValue());
			Iterator<Long> bidIT = points.keySet().iterator();
			while(bidIT.hasNext())
			{
				Long abid= bidIT.next();
				if(points.get(abid).getMaxTotalDailyMicroCost() < 10000L){
					logger.info("Moving keyword \""+words[i]+"\" to non-competitive category from competitive category.");
					logger.info(words[i] + ": " + abid/1e6 + ": " + points.get(abid).getMaxAveCPC()/1e6 + ": " + points.get(abid).getMaxClickPerDay());
					compKeywords.remove(words[i]);
					nonCompKeywords.add(words[i]);
					continue;
				} else {
					//logger.info(words[i]+":: Total daily cost: $"+points.get(abid).getMaxTotalDailyMicroCost()/1e6);

					 logger.info("Got valid data from traffic estimator for keyword \""+words[i]+"\".");
					 logger.info(words[i] + ": " + abid/1e6 + ": " + points.get(abid).getMaxAveCPC()/1e6 + ": " + points.get(abid).getMaxClickPerDay());
					
					EstimatorData clickDataObj = new EstimatorData();
					clickDataObj.addData(abid/1e6, (points.get(abid).getMaxClickPerDay() + points.get(abid).getMinClickPerDay())/2);
					clickDataMap.put(words[i], clickDataObj);
					
					EstimatorData costDataObj = new EstimatorData();
					costDataObj.addData(abid/1e6, (points.get(abid).getMaxTotalDailyMicroCost() + points.get(abid).getMinTotalDailyMicroCost())/(2*1e6));
					costDataMap.put(words[i], costDataObj);
				}
			}
		}
		logger.info("Number of intermediate competitive keywords: "+compKeywords.size());

		
		// get the second point
		bids = new HashMap<String, Long>();
		for (String s : compKeywords){
			bids.put(s, firstPageCPCMap.get(s)+stepSecond);
		}
		k=0;
		while(true) {
			Thread.sleep(sleepPeriod+k*sleepBackOffTime);
			try {
				logger.info("Fetching traffic estimator data for Point 2");
				o = getTrafficEstimationForKeywordsGoogle(googleAccountID, campaignID, KeywordMatchType.EXACT, bids);
				break;
			} catch (Exception e) {
				if (k<=maxRetry) {
					//e.printStackTrace();
					logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
					k++;				
				} else {
					//e.printStackTrace();
					logger.error("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
					throw new Exception("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
				}
			}
		}
		words = o.getListOfKeywords();
		for (int i=0; i < words.length; i++)
		{
			HashMap<Long, TrafficEstimatorObject.BidData> points = o.getMapOfPoints(words[i],KeywordMatchType.EXACT.getValue());
			Iterator<Long> bidIT = points.keySet().iterator();
			while(bidIT.hasNext())
			{
				Long abid= bidIT.next();
				
				 logger.info("Got valid data from traffic estimator for keyword \""+words[i]+"\".");
				 logger.info(words[i] + ": " + abid/1e6 + ": " + points.get(abid).getMaxAveCPC()/1e6 + ": " + points.get(abid).getMaxClickPerDay());

				EstimatorData clickDataObj = clickDataMap.get(words[i]);
				clickDataObj.addData(abid/1e6, (points.get(abid).getMaxClickPerDay() + points.get(abid).getMinClickPerDay())/2);
				clickDataMap.put(words[i], clickDataObj);

				EstimatorData costDataObj = costDataMap.get(words[i]);
				costDataObj.addData(abid/1e6, (points.get(abid).getMaxTotalDailyMicroCost() + points.get(abid).getMinTotalDailyMicroCost())/(2*1e6));
				costDataMap.put(words[i], costDataObj);
				
				logger.info(words[i]+":: Total daily cost: $"+points.get(abid).getMaxTotalDailyMicroCost()/1e6);


				/*
				// now check if we are getting the same data 
				double [] bidArray = costDataMap.get(words[i]).getBidArray();
				Arrays.sort(bidArray);
				double [] costArray = costDataMap.get(words[i]).getData(bidArray);
				if (Math.abs(costArray[0]-costArray[costArray.length-1])<1e-6){
					// System.out.println("Moving keyword \""+words[i]+"\" to non-competitive category from competitive category.");
					compKeywords.remove(words[i]);
					nonCompKeywords.add(words[i]);
					clickDataMap.remove(words[i]);
					costDataMap.remove(words[i]);
					continue;
				}
				*/

			}
		}
		

		
		
		// get the next 4 points uniformly (for the time being)
		for( int j=2; j<8; j++) {
			bids = new HashMap<String, Long>();
			for (String s : compKeywords){
				bids.put(s, firstPageCPCMap.get(s)+j*stepRest);
			}
			System.out.println("j="+j);
			k=0;
			while(true) {
				Thread.sleep(sleepPeriod+k*sleepBackOffTime);
				try {
					logger.info("Fetching traffic estimator data for Point "+(j+1));
					o = getTrafficEstimationForKeywordsGoogle(googleAccountID, campaignID, KeywordMatchType.EXACT, bids);
					break;
				} catch (Exception e) {
					if (k<=maxRetry) {
						//e.printStackTrace();
						logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
						k++;				
					} else {
						//e.printStackTrace();
						logger.error("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
						throw new Exception("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
					}
				}
			}
			words = o.getListOfKeywords();
			for (int i=0; i < words.length; i++)
			{
				HashMap<Long, TrafficEstimatorObject.BidData> points = o.getMapOfPoints(words[i],KeywordMatchType.EXACT.getValue());
				Iterator<Long> bidIT = points.keySet().iterator();
				while(bidIT.hasNext())
				{
					Long abid= bidIT.next();

					 logger.info("Got valid data from traffic estimator for keyword \""+words[i]+"\".");
					 logger.info(words[i] + ": " + abid/1e6 + ": " + points.get(abid).getMaxAveCPC()/1e6 + ": " + points.get(abid).getMaxClickPerDay());

					EstimatorData clickDataObj = clickDataMap.get(words[i]);
					clickDataObj.addData(abid/1e6, (points.get(abid).getMaxClickPerDay() + points.get(abid).getMinClickPerDay())/2);
					clickDataMap.put(words[i], clickDataObj);

					EstimatorData costDataObj = costDataMap.get(words[i]);
					costDataObj.addData(abid/1e6, (points.get(abid).getMaxTotalDailyMicroCost() + points.get(abid).getMinTotalDailyMicroCost())/(2*1e6));
					costDataMap.put(words[i], costDataObj);
					
					logger.info(words[i]+":: Total daily cost: $"+points.get(abid).getMaxTotalDailyMicroCost()/1e6);


				}
			}
		} // for( int j=0; j<4; j++)
		
		

		for (int i=0; i < words.length; i++) {
			// now check if we are getting the same data 
			double [] bidArray = costDataMap.get(words[i]).getBidArray();
			Arrays.sort(bidArray);
			double [] costArray = costDataMap.get(words[i]).getData(bidArray);
//			if (Math.abs(costArray[0]-costArray[costArray.length-1])<1e-6){
			if (costArray[costArray.length-1]<costArray[0]+1e-4){
				 logger.info("Moving keyword \""+words[i]+"\" to non-competitive category from competitive category.");
				compKeywords.remove(words[i]);
				nonCompKeywords.add(words[i]);
				clickDataMap.remove(words[i]);
				costDataMap.remove(words[i]);
				continue;
			}
		}
		
		logger.info("Number of final competitive keywords: "+compKeywords.size());
		
		return o;
	}

	
	
	private TrafficEstimatorObject getTrafficEstimationForKeywordsGoogle(String accountID, Long campaignID, KeywordMatchType matchType,
			HashMap<String, Long> KeywordWithBid) throws Exception {
//			HashMap<String, Long> KeywordWithBid, GoogleAdwordsServiceImpl client) throws Exception {
//		HashMap<String, Double> KeywordWithBid, GoogleAdwordsServiceClient client) throws Exception {
		
		TrafficEstimatorObject o = null, o2 = null;
		int i=0, k;
		boolean firstLoop = true;
		
		HashMap<String,Long> newKeywordWithBid = new HashMap<String,Long>();
		Iterator it = KeywordWithBid.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = (Map.Entry) it.next();
			String s = (String) me.getKey();
			if (KeywordWithBid.get(s) != null){
				newKeywordWithBid.put(s,KeywordWithBid.get(s));
				i++;
			}
			if((i%500==0 && i>0) || (!it.hasNext())){
				k=0;
				while(true) {
					try {
						if(newKeywordWithBid.size()>0){
							Thread.sleep(sleepPeriod+k*sleepBackOffTime);
							logger.info("Calling Google API for traffic estimator data.");
							o2 = clientGoogle.getTrafficEstimationForKeywords(accountID, campaignID, matchType, newKeywordWithBid);
						}
						break;
					} catch (Exception e) {
						if (k<=maxRetry) {
							//e.printStackTrace();
							logger.error("Received exception : will retry..., k="+k+" "+e.getMessage());
							k++;				
						} else {
							//e.printStackTrace();
							logger.error("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
							throw new Exception("Failed to get traffic estimator data from Google after "+k+" efforts "+e.getMessage());
						}
					}
				}
				newKeywordWithBid.clear();
				if(firstLoop || o==null){
					o=o2;
					firstLoop=false;
				} else {
					o.addGoogleTrafficEstimatorObject(o2,KeywordMatchType.EXACT.getValue());
				}
			} // if(i/500==0) 
		} // while(it.hasNext())
		
		
		return o;
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}