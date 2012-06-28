package semplest.service.google.adwords;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import semplest.other.DateTimeCeiling;
import semplest.other.DateTimeFloored;
import semplest.server.encryption.AESBouncyCastle;
import semplest.server.keyword.KeywordMatchingType;
import semplest.server.protocol.ProtocolEnum.AdEngine;
import semplest.server.protocol.SemplestString;
import semplest.server.protocol.adengine.BidSimulatorObject;
import semplest.server.protocol.adengine.GeoTargetObject;
import semplest.server.protocol.adengine.KeywordDataObject;
import semplest.server.protocol.adengine.KeywordProbabilityObject;
import semplest.server.protocol.adengine.ReportObject;
import semplest.server.protocol.adengine.TrafficEstimatorObject;
import semplest.server.protocol.google.AdGroupAdRetriableGoogleOperation;
import semplest.server.protocol.google.AdGroupCriterionRetriableGoogleOperation;
import semplest.server.protocol.google.AdGroupRetriableGoogleOperation;
import semplest.server.protocol.google.BudgetOrderRetriableGoogleOperation;
import semplest.server.protocol.google.CampaignAdExtensionRetriableGoogleOperation;
import semplest.server.protocol.google.CampaignCriterionRetriableGoogleOperation;
import semplest.server.protocol.google.CampaignRetriableGoogleOperation;
import semplest.server.protocol.google.CreateAccountRetriableGoogleOperation;
import semplest.server.protocol.google.GoogleAdGroupObject;
import semplest.server.protocol.google.GoogleAddAdRequest;
import semplest.server.protocol.google.GoogleAddAdsRequest;
import semplest.server.protocol.google.GoogleAddKeywordRequest;
import semplest.server.protocol.google.GoogleRefreshSiteLinksRequest;
import semplest.server.protocol.google.GoogleRelatedKeywordObject;
import semplest.server.protocol.google.GoogleSetBidForKeywordRequest;
import semplest.server.protocol.google.GoogleSiteLink;
import semplest.server.protocol.google.UpdateAdRequest;
import semplest.server.protocol.google.UpdateAdsRequestObj;
import semplest.server.protocol.google.KeywordToolStats;
import semplest.server.service.SemplestConfiguration;
import semplest.server.service.springjdbc.PromotionObj;
import semplest.server.service.springjdbc.storedproc.GetAllPromotionDataSP;
import semplest.server.service.springjdbc.storedproc.UpdateAdEngineAPIChargeSP;
import semplest.services.client.interfaces.GoogleAdwordsServiceInterface;
import semplest.util.SemplestUtils;

import com.google.api.adwords.lib.AdWordsService;
import com.google.api.adwords.lib.AdWordsServiceLogger;
import com.google.api.adwords.lib.AdWordsUser;
import com.google.api.adwords.lib.AuthToken;
import com.google.api.adwords.lib.utils.MapUtils;
import com.google.api.adwords.v201109.cm.Ad;
import com.google.api.adwords.v201109.cm.AdGroup;
import com.google.api.adwords.v201109.cm.AdGroupAd;
import com.google.api.adwords.v201109.cm.AdGroupAdOperation;
import com.google.api.adwords.v201109.cm.AdGroupAdPage;
import com.google.api.adwords.v201109.cm.AdGroupAdReturnValue;
import com.google.api.adwords.v201109.cm.AdGroupAdServiceInterface;
import com.google.api.adwords.v201109.cm.AdGroupAdStatus;
import com.google.api.adwords.v201109.cm.AdGroupBidLandscape;
import com.google.api.adwords.v201109.cm.AdGroupBidLandscapePage;
import com.google.api.adwords.v201109.cm.AdGroupCriterion;
import com.google.api.adwords.v201109.cm.AdGroupCriterionOperation;
import com.google.api.adwords.v201109.cm.AdGroupCriterionPage;
import com.google.api.adwords.v201109.cm.AdGroupCriterionReturnValue;
import com.google.api.adwords.v201109.cm.AdGroupCriterionServiceInterface;
import com.google.api.adwords.v201109.cm.AdGroupOperation;
import com.google.api.adwords.v201109.cm.AdGroupPage;
import com.google.api.adwords.v201109.cm.AdGroupReturnValue;
import com.google.api.adwords.v201109.cm.AdGroupServiceInterface;
import com.google.api.adwords.v201109.cm.AdGroupStatus;
import com.google.api.adwords.v201109.cm.ApiException;
import com.google.api.adwords.v201109.cm.ApprovalStatus;
import com.google.api.adwords.v201109.cm.Bid;
import com.google.api.adwords.v201109.cm.BidLandscapeLandscapePoint;
import com.google.api.adwords.v201109.cm.BiddableAdGroupCriterion;
import com.google.api.adwords.v201109.cm.Budget;
import com.google.api.adwords.v201109.cm.BudgetBudgetDeliveryMethod;
import com.google.api.adwords.v201109.cm.BudgetBudgetPeriod;
import com.google.api.adwords.v201109.cm.Campaign;
import com.google.api.adwords.v201109.cm.CampaignCriterion;
import com.google.api.adwords.v201109.cm.CampaignCriterionOperation;
import com.google.api.adwords.v201109.cm.CampaignCriterionPage;
import com.google.api.adwords.v201109.cm.CampaignCriterionReturnValue;
import com.google.api.adwords.v201109.cm.CampaignCriterionServiceInterface;
import com.google.api.adwords.v201109.cm.CampaignOperation;
import com.google.api.adwords.v201109.cm.CampaignPage;
import com.google.api.adwords.v201109.cm.CampaignReturnValue;
import com.google.api.adwords.v201109.cm.CampaignServiceInterface;
import com.google.api.adwords.v201109.cm.CampaignStatus;
import com.google.api.adwords.v201109.cm.Criterion;
import com.google.api.adwords.v201109.cm.CriterionBidLandscape;
import com.google.api.adwords.v201109.cm.CriterionBidLandscapePage;
import com.google.api.adwords.v201109.cm.CriterionType;
import com.google.api.adwords.v201109.cm.DataServiceInterface;
import com.google.api.adwords.v201109.cm.DateRange;
import com.google.api.adwords.v201109.cm.Keyword;
import com.google.api.adwords.v201109.cm.KeywordMatchType;
import com.google.api.adwords.v201109.cm.Language;
import com.google.api.adwords.v201109.cm.ListReturnValue;
import com.google.api.adwords.v201109.cm.Location;
import com.google.api.adwords.v201109.cm.ManualCPC;
import com.google.api.adwords.v201109.cm.ManualCPCAdGroupBids;
import com.google.api.adwords.v201109.cm.ManualCPCAdGroupCriterionBids;
import com.google.api.adwords.v201109.cm.Money;
import com.google.api.adwords.v201109.cm.NegativeCampaignCriterion;
import com.google.api.adwords.v201109.cm.NetworkSetting;
import com.google.api.adwords.v201109.cm.Operation;
import com.google.api.adwords.v201109.cm.Operator;
import com.google.api.adwords.v201109.cm.OrderBy;
import com.google.api.adwords.v201109.cm.Paging;
import com.google.api.adwords.v201109.cm.Predicate;
import com.google.api.adwords.v201109.cm.PredicateOperator;
import com.google.api.adwords.v201109.cm.QualityInfo;
import com.google.api.adwords.v201109.cm.RateExceededError;
import com.google.api.adwords.v201109.cm.Selector;
import com.google.api.adwords.v201109.cm.SortOrder;
import com.google.api.adwords.v201109.cm.TextAd;
import com.google.api.adwords.v201109.info.ApiUsageInfo;
import com.google.api.adwords.v201109.info.ApiUsageRecord;
import com.google.api.adwords.v201109.info.ApiUsageType;
import com.google.api.adwords.v201109.info.InfoSelector;
import com.google.api.adwords.v201109.info.InfoServiceInterface;
import com.google.api.adwords.v201109.mcm.Account;
import com.google.api.adwords.v201109.mcm.CreateAccountOperation;
import com.google.api.adwords.v201109.mcm.CreateAccountServiceInterface;
import com.google.api.adwords.v201109.o.AdGroupEstimateRequest;
import com.google.api.adwords.v201109.o.Attribute;
import com.google.api.adwords.v201109.o.AttributeType;
import com.google.api.adwords.v201109.o.AverageTargetedMonthlySearchesSearchParameter;
import com.google.api.adwords.v201109.o.CampaignEstimateRequest;
import com.google.api.adwords.v201109.o.CriterionAttribute;
import com.google.api.adwords.v201109.o.DoubleAttribute;
import com.google.api.adwords.v201109.o.ExcludedKeywordSearchParameter;
import com.google.api.adwords.v201109.o.IdeaType;
import com.google.api.adwords.v201109.o.KeywordEstimate;
import com.google.api.adwords.v201109.o.KeywordEstimateRequest;
import com.google.api.adwords.v201109.o.KeywordMatchTypeSearchParameter;
import com.google.api.adwords.v201109.o.LocationSearchParameter;
import com.google.api.adwords.v201109.o.LongAttribute;
import com.google.api.adwords.v201109.o.LongComparisonOperation;
import com.google.api.adwords.v201109.o.RelatedToKeywordSearchParameter;
import com.google.api.adwords.v201109.o.RelatedToUrlSearchParameter;
import com.google.api.adwords.v201109.o.RequestType;
import com.google.api.adwords.v201109.o.SearchParameter;
import com.google.api.adwords.v201109.o.StatsEstimate;
import com.google.api.adwords.v201109.o.TargetingIdea;
import com.google.api.adwords.v201109.o.TargetingIdeaPage;
import com.google.api.adwords.v201109.o.TargetingIdeaSelector;
import com.google.api.adwords.v201109.o.TargetingIdeaServiceInterface;
import com.google.api.adwords.v201109.o.TrafficEstimatorResult;
import com.google.api.adwords.v201109.o.TrafficEstimatorSelector;
import com.google.api.adwords.v201109.o.TrafficEstimatorServiceInterface;
import com.google.api.adwords.v201109_1.billing.BudgetOrder;
import com.google.api.adwords.v201109_1.billing.BudgetOrderOperation;
import com.google.api.adwords.v201109_1.billing.BudgetOrderReturnValue;
import com.google.api.adwords.v201109_1.billing.BudgetOrderServiceInterface;
import com.google.api.adwords.v201109_1.cm.AdExtension;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtension;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtensionOperation;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtensionPage;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtensionReturnValue;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtensionServiceInterface;
import com.google.api.adwords.v201109_1.cm.CampaignAdExtensionStatus;
import com.google.api.adwords.v201109_1.cm.Sitelink;
import com.google.api.adwords.v201109_1.cm.SitelinksExtension;
import com.google.gson.Gson;

public class GoogleAdwordsServiceImpl implements GoogleAdwordsServiceInterface
{
	private static final Logger logger = Logger.getLogger(GoogleAdwordsServiceImpl.class);
	private static Gson gson = new Gson();
	// THis needs to be read from the Database
	private final String email; // = "adwords@semplest.com";
	private final String password; // = "ic0system";
	private final String userAgent; // = "Icosystem";
	private final String developerToken; // = "2H8l6aUm6K_Q44vDvxs3Og";
	private final boolean useSandbox; // = false; // true; // // true; //

	public GoogleAdwordsServiceImpl() throws Exception
	{
		try
		{
			String key = (String) SemplestConfiguration.configData.get("SemplestEncryptionkey");
			AESBouncyCastle aes = AESBouncyCastle.getInstance(key);
			email = (String) SemplestConfiguration.configData.get("AdwordsEmail");
			password = aes.decrypt((String) SemplestConfiguration.configData.get("AdwordsPassword"));
			userAgent = (String) SemplestConfiguration.configData.get("AdwordsUserAgent");
			developerToken = (String) SemplestConfiguration.configData.get("AdwordsDeveloperToken");
			useSandbox = (Boolean) SemplestConfiguration.configData.get("AdwordsUseSandbox");
			logger.info("Initialized Google API sandbox=" + useSandbox);
		}
		catch (Exception e)
		{
			final String errMsg = "Unable to initialize Google API";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
	}

	public static void main(String[] args)
	{
		// Log SOAP XML request and response.
		try
		{
			//BasicConfigurator.configure();
			ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("Service.xml");
			Object object = new Object();
			SemplestConfiguration configDB = new SemplestConfiguration(object);
			Thread configThread = new Thread(configDB);
			configThread.start();
			synchronized (object)
			{
				object.wait();
			}
			
			GoogleAdwordsServiceImpl test = new GoogleAdwordsServiceImpl();
			final GoogleAddAdRequest req = new GoogleAddAdRequest(75, "555555555frozen yogurt", "1111111any flavor", "33333333333healthy");
			final List<GoogleAddAdRequest> addAdTextRequests = new ArrayList<GoogleAddAdRequest>();
			addAdTextRequests.add(req);
			final GoogleAddAdsRequest request = new GoogleAddAdsRequest("54104", 3066601739L, "http://www.aol.com", "http://www.weddingchannel.com", addAdTextRequests); 
			test.addTextAds(request);
			
			/*GetAllPromotionDataSP getPromoDataSP = new GetAllPromotionDataSP();
			getPromoDataSP.execute(60);
			PromotionObj promoObj = getPromoDataSP.getPromotionData();
			Long cumulativeUnitsUsedFromStart = test.getSpentAPIUnitsPerAccountID(promoObj.getAdvertisingEngineAccountPK(), promoObj.getPromotionStartDate(), new Date());
			if (cumulativeUnitsUsedFromStart != null && cumulativeUnitsUsedFromStart > 0)
			{
				UpdateAdEngineAPIChargeSP updateApiSP = new UpdateAdEngineAPIChargeSP();
				Double newCost = updateApiSP.execute(promoObj.getAdvertisingEngineAccountPK(), AdEngine.Google, cumulativeUnitsUsedFromStart);
				logger.info("Added additional API Cost of " + newCost + " to Google Account " + promoObj.getAdvertisingEngineAccountPK());
			}*/
			//final Long spend = test.getSpentAPIUnitsPerAccountID(2387614989L, new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 15)), new Date());
			//final Long spend = test.getSpentAPIUnitsPerAccountID(2387614989L, new Date(), new Date());
			//System.out.println("Spend: " + spend);
			
			/*Long accountID = 2387614989L;
			GoogleAdwordsServiceImpl g = new GoogleAdwordsServiceImpl();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -5);
			Long api = g.getSpentAPIUnitsPerAccountID(accountID,cal.getTime(),new
					java.util.Date());

			System.out.println("API COST=" + api);*/
			/*
			final String accountID = "54100";
			final Long adGroupID = 3066031127L;
			final String keyword = "HelloKeyword";
			final KeywordMatchType matchType = KeywordMatchType.EXACT;
			final Long microBidAmount = 5 * 1000000L;
			final KeywordDataObject returnedKeyword = g.addKeyWordToAdGroup(accountID, adGroupID, keyword, matchType, microBidAmount);
			logger.info("Returned keyword: " + returnedKeyword);
			*/
			
			
			/*
			ArrayList<GoogleSiteLink> siteLinks = new ArrayList<GoogleSiteLink>();
			GoogleSiteLink l = new GoogleSiteLink();
			l.setLinkText("TestSiteLink");
			l.setLinkURL("http://www.semplest.com");
			siteLinks.add(l);
			Boolean res = g.refreshSiteLinkForCampaign("54100", 637295L, siteLinks);
			
			System.out.println(res);
			*/
			
			// Long id = g.GetActiveSitelinkExtension("54102", 656140L);
			// System.out.println("id=" + id);
			
			//String url = "www.summithillsfloristnj.com";
			/*String[] keywords = new String[]
					{"wedding dresses", "bridesmaids dresses", "mother of the bride dresses", "flower girl dresses",
					"wedding accessories", "engagement rings", "wedding rings", "tuxedos", "wedding invitations", "wedding registry"};*/
			//{ "wedding flowers", "flower centerpieces", "floral shop", "flower arrangement", "arrange flower" };
			
			
			
			//int numberResults = 100;
			//int categoryId = 11476; // Wedding Flowers
			//String accountID = null;
			//String [] exclude_keywords = null;
			/*
			ArrayList<KeywordToolStats> keyWordIdeaList; 
			try{ 
				keyWordIdeaList = g.getGoogleKeywordIdeas(keywords, 1000); 
//				for (KeywordToolStats w : keyWordIdeaList){
//					System.out.println(w.getKeyword());
//				}
			} catch (Exception e){ 
				logger.error("Problem", e); 
			}
			*/

			
			/*
			 * ArrayList<KeywordToolStats> stats =
			 * g.getGoogleVolumeCompetition(keywords, null); for
			 * (KeywordToolStats k : stats) { System.out.println(k.getKeyword()
			 * + "," + k.getMatchType() + "," + k.getAverageMonthlySearches() +
			 * "," + k.getCompetition()); }
			*/
			
			/*
			 * AdEngineID adEngineInfo = SemplestDB.getAdEngineID(62, "Google");
			 * GoogleAdwordsServiceImpl g = new GoogleAdwordsServiceImpl();
			 * KeywordDataObject[] keyData =
			 * g.getAllBiddableAdGroupCriteria(String
			 * .valueOf(adEngineInfo.getAccountID()),
			 * adEngineInfo.getAdGroupID(), true); //"54100",3066028785L, true);
			 * for (int i = 0; i < keyData.length; i++) {
			 * System.out.println(keyData[i].getKeyword()); }
			*/
			
			/*
			 * AdWordsServiceLogger.log(); String accountID = "6048920973"; //
			 * Get AdWordsUser from "~/adwords.properties". //AdWordsUser user =
			 * new AdWordsUser(email, password, "6048920973", userAgent,
			 * developerToken, useSandbox); GoogleAdwordsServiceImpl g = new
			 * GoogleAdwordsServiceImpl(); AdGroup[] res =
			 * g.getAdGroupsByCampaignId("6048920973", 75239229L, false); if
			 * (res != null) { for(int i = 0; i < res.length; i++) {
			 * System.out.println(res[i].getCampaignName() + ":" +
			 * res[i].getName()); } } Campaign[] camp =
			 * g.getCampaignsByAccountId(accountID, false); if (camp != null) {
			 * for(int i = 0; i < camp.length; i++) {
			 * System.out.println(camp[i].getName()); } }
			 */
			
			/*
			 * AdWordsUser user = new AdWordsUser(email, password, null,
			 * userAgent, developerToken, true); ServicedAccountServiceInterface
			 * accountService =
			 * user.getService(AdWordsService.V201109.SERVICED_ACCOUNT_SERVICE);
			 * ServicedAccountGraph ret = accountService.get(new
			 * ServicedAccountSelector()); Account[] accounts =
			 * ret.getAccounts(); for(Account a : accounts){
			 * System.out.println("accountId = " + a.getCustomerId()); }
			*/
			
			/*
			 * GoogleAdwordsServiceImpl g = new GoogleAdwordsServiceImpl();
			 * Campaign ret = g.CreateOneCampaignForAccount("54100", "temp1",
			 * CampaignStatus.PAUSED, BudgetBudgetPeriod.DAILY, 1000000L);
			 * System.out.println("campaignId = " + ret.getId());
			*/


			// Campaign c = g.CreateOneCampaignForAccount(null, "test",
			// CampaignStatus.ACTIVE, BudgetBudgetPeriod.MONTHLY, 1000000L);
			// String accountID = "2188810777"; // "5058200123";// "8019925375";
			// //

			// String accountID = "2188810777"; // "5058200123";// "8019925375";
			// //
			
			// "6048920973";
			//Long adGroupID = 3074331030L;
			//Long campaignID = 77290470L;
			//Long accountID = 9036397375L;

			// g.getSpentAPIUnitsPerAccountID(accountID,new java.util.Date(),new
			// java.util.Date());

			/*
			 * 
			 * ArrayList<HashMap<String, String>> ret =
			 * g.getCampaignsByAccountId("2188810777", false); String id =
			 * ret.get(0).values().toString(); System.out.println(id);
			 */

			// ArrayList<HashMap<String, String>> ret =
			// g.getCampaignsByAccountId("2188810777", false);
			//String id = ret.get(0).values().toString();
			//System.out.println(id);

			// KeywordDataObject[] c =
			// g.getAllBiddableAdGroupCriteria(accountID, adGroupID, true);

			/*
			 * String accountID = "2188810777"; // "5058200123";// "8019925375";
			 * // // "6048920973"; Long adGroupID = 3380873349L; Long campaignID
			 * = 77290470L;
			 * 
			 * 
			 * HashMap<String, Double> bids = new HashMap<String, Double>();
			 * bids.put("wedding flower", 2.0); HashMap<String, Double> bids2 =
			 * new HashMap<String, Double>(); bids2.put("wedding photo", 8.0);
			 * 
			 * GoogleTrafficEstimatorObject o =
			 * g.getTrafficEstimationForKeywords(accountID, campaignID,
			 * KeywordMatchType.EXACT, bids); String[] keywords =
			 * o.getListOfKeywords(); for (int i=0; i < keywords.length; i++) {
			 * HashMap<Double, GoogleTrafficEstimatorObject.BidData> points =
			 * o.getMapOfPoints(keywords[i]); Iterator<Double> bidIT =
			 * points.keySet().iterator(); while(bidIT.hasNext()) { Double abid=
			 * bidIT.next(); System.out.println(keywords[i] + ":" + abid + ":" +
			 * points.get(abid).getMaxAveCPC()); } }
			 * GoogleTrafficEstimatorObject o2 =
			 * g.getTrafficEstimationForKeywords(accountID, campaignID,
			 * KeywordMatchType.EXACT, bids2);
			 * o.addGoogleTrafficEstimatorObject(o2); keywords =
			 * o.getListOfKeywords(); for (int i=0; i < keywords.length; i++) {
			 * HashMap<Double, GoogleTrafficEstimatorObject.BidData> points =
			 * o.getMapOfPoints(keywords[i]); Iterator<Double> bidIT =
			 * points.keySet().iterator(); while(bidIT.hasNext()) { Double abid=
			 * bidIT.next(); System.out.println(keywords[i] + ":" + abid + ":" +
			 * points.get(abid).getMaxAveCPC()); } }
			 */
			/*
			 * Double[] bids = o.getBidList(); for (int i = 0; i < bids.length;
			 * i++) { System.out.println(bids[i] + " Aveclicks=" +
			 * o.getMaxAveClickPerDay(bids[i]) + " AveCPC=" +
			 * o.getAveCPC(bids[i])); }
			 */

			// File f = g.getReportForAccount(accountID);
			// f.getName();
			/*
			 * ArrayList<HashMap<String, String>> camp =
			 * g.getCampaignsByAccountId(accountID, false);
			 * 
			 * Long campaignID = new Long(camp.get(0).get("Id"));
			 * System.out.println(campaignID); AdGroup[] adgroups =
			 * g.getAdGroupsByCampaignId(accountID, campaignID, false);
			 * adGroupID = adgroups[0].getId();
			 */
			/*
			 * GoogleBidObject[] c = g.getAllBiddableAdGroupCriteria(accountID,
			 * adGroupID); for (int i = 0; i < c.length; i++) {
			 * System.out.println(c[i].getKeyword() + ":" + c[i].getBidID()); }
			 */
			// MyTestKeyword3:34632214029
			// Long keywordID = 41030523L; // wedding venue
			// g.getBidLandscapeForAdgroup(accountID, adGroupID); // ,
			// keywordID);
			// g.reportfields();
			// ArrayList<HashMap<String, String>> res =
			// g.getCampaignsByAccountId(accountID, false);
			// System.out.println(res.get(0).get("Name"));
			// TargetingIdea[] res = g.GetRelatedKeywords("Red Shoes",
			// KeywordMatchType.EXACT, 20);

			/*
			 * GoogleRelatedKeywordObject res =
			 * g.GetRelatedKeywordsForURL("http://www.peanutbutter.com/",
			 * "peanut butter", KeywordMatchType.EXACT, 20); ArrayList<String>
			 * keys = res.getKeywords(); for (int i = 0; i < keys.size(); i++) {
			 * System.out.println(keys.get(i)); ArrayList<String> match =
			 * res.getMatchTypesForKeyword(keys.get(i)); for (int j = 0; j <
			 * match.size(); j++) { System.out.println(match.get(j)); } }
			 */
			/*
			 * SemplestString ss = new SemplestString(); ArrayList<ReportObject>
			 * f = g.getReportForAccount(ss.toSemplestString(accountID));
			 * for(ReportObject t : f){ logger.info("Keyword: " + t.getKeyword()
			 * + "; " + "Bidamount: " + t.getMicroBidAmount() + "; " +
			 * "BidMatchType: " + t.getBidMatchType() + "; " +
			 * "NumberImpressions: " + t.getNumberImpressions() + "; " +
			 * "NumberClick: " + t.getNumberClick() + "; " + "AveragePosition: "
			 * + t.getAveragePosition() + "; " + "AverageCPC: " +
			 * t.getAverageCPC() + "; " + "QualityScore: " +
			 * t.getQualityScore()+ "; " + "ApprovalStatus: " +
			 * t.getApprovalStatus()+ "; " + "FirstPageCPC: " +
			 * t.getFirstPageCPC()+ "; " + "TransactionDate: " +
			 * t.getTransactionDate()+ "; " + "AccountID: " + t.getAccountID()+
			 * "; " + "CampaignID: " + t.getCampaignID()+ "; " + "Cost: " +
			 * t.getMicroCost() + "; " ); } //
			 */

		}
		catch (Exception e)
		{
			logger.error("Problem", e);
		}

	}

	public String getSpentAPIUnitsPerAccountID(String json) throws Exception
	{
		logger.debug("call getSpentAPIUnitsPerAccountID(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING); // protocolJson.getHashMapFromJson(json);
		Long accountID = Long.parseLong(data.get("accountID"));
		Long unitsSpent = getSpentAPIUnitsPerAccountID(accountID, gson.fromJson(data.get("startDate"), java.util.Date.class), gson.fromJson(data.get("endDate"), java.util.Date.class));
		// convert result to Json String
		return gson.toJson(unitsSpent);
	}

	/*
	 *UNIT_COUNT_FOR_CLIENTS : Returns the number of API units recorded for a subset of clients over the given date range. The given dates are inclusive; 
	 *to get the unit count for a single day, supply it as both the start and end date. Specify the apiUsageType, dateRange and clientEmails parameters.
	 */
	@Override
	public Long getSpentAPIUnitsPerAccountID(Long accountID, java.util.Date startDate, java.util.Date endDate) throws Exception
	{
		try
		{
			//AdWordsServiceLogger.log();
			//logger.info("AdWordsServiceLogger.log() called");
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// Get the INFO_SERVICe.

			InfoServiceInterface infoService = user.getService(AdWordsService.V201109.INFO_SERVICE);
			InfoSelector selector = new InfoSelector();
			selector.setApiUsageType(ApiUsageType.UNIT_COUNT_FOR_CLIENTS);
			selector.setClientCustomerIds(new long[]
			{ accountID });
			String start = "";
			String end = "";
			if (startDate != null && endDate != null)
			{
				start = SemplestUtils.DATE_FORMAT_YYYYMMDD.format(startDate);
				end = SemplestUtils.DATE_FORMAT_YYYYMMDD.format(endDate);
			}
			else
			{
				throw new Exception("Date is null");
			}
			// selector.setClientEmails(new String[]{email});
			selector.setDateRange(new DateRange(start, end));
			ApiUsageInfo info = infoService.get(selector);
			Long res = null;
			if (info != null && info.getApiUsageRecords() != null && info.getApiUsageRecords().length>0)
			{
				res = info.getApiUsageRecords(0).getCost();
			}
			return res;

		}
		catch (ServiceException se)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]";
			logger.error(errMsg, se);
			throw new Exception(errMsg, se);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]: " + e.dumpToString();
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}

	}

	public Long getSpentAPIUnitsPerMethodAndAccountID(Long accountID, String serviceName, String methodName, java.util.Date startDate, java.util.Date endDate) throws Exception
	{
		try
		{
			//AdWordsServiceLogger.log();
			//logger.info("AdWordsServiceLogger.log() called");
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// Get the INFO_SERVICe.

			InfoServiceInterface infoService = user.getService(AdWordsService.V201109.INFO_SERVICE);
			InfoSelector selector = new InfoSelector();
			selector.setApiUsageType(ApiUsageType.UNIT_COUNT);
			selector.setClientCustomerIds(new long[]
			{ accountID });
			String start = "";
			String end = "";
			if (startDate != null && endDate != null)
			{
				start = SemplestUtils.DATE_FORMAT_YYYYMMDD.format(startDate);
				end = SemplestUtils.DATE_FORMAT_YYYYMMDD.format(endDate);
			}
			else
			{
				throw new Exception("Date is null");
			}
			// selector.setClientEmails(new String[]{email});
			selector.setDateRange(new DateRange(start, end));
			//Specify method
			selector.setServiceName(serviceName);
			selector.setMethodName(methodName);
			ApiUsageInfo info = infoService.get(selector);
			Long res = null;
			if (info != null && info.getApiUsageRecords() != null && info.getApiUsageRecords().length>0)
			{
				res = info.getApiUsageRecords(0).getCost();
			}
			return info.getCost();

		}
		catch (ServiceException se)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]";
			logger.error(errMsg, se);
			throw new Exception(errMsg, se);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]: " + e.dumpToString();
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem getting SpentAPIUnitsPerAccountID for AccountID [" + accountID + "], StateDate [" + startDate + "], EndDate [" + endDate + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}

	}
	
	
	/*
	 * Account
	 */
	public String CreateOneAccountService(String json) throws Exception
	{
		logger.debug("call CreateOneAccountService(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING); // protocolJson.getHashMapFromJson(json);
		Account account = CreateOneAccountService(data.get("currencyCode"), data.get("dateTimeZone"), data.get("companyName"), data.get("descriptiveName"));
		return gson.toJson(account);
	}

	@Override
	public Account CreateOneAccountService(String currencyCode, String dateTimeZone, String companyName, String descriptiveName) throws Exception
	{
		logger.info("Will try to Create Account for CompanyName [" + companyName + "], DescriptiveName [" + descriptiveName + "], CurrencyCode [" + currencyCode + "], DateTimeZone [" + dateTimeZone + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			final CreateAccountServiceInterface createAccountService = user.getService(AdWordsService.V201109.CREATE_ACCOUNT_SERVICE);
			final Account account = new Account();
			if (currencyCode == null)
			{
				currencyCode = "USD";
			}
			if (dateTimeZone == null)
			{
				dateTimeZone = "America/New_York";
			}
			account.setCurrencyCode(currencyCode);
			account.setDateTimeZone(dateTimeZone);
			account.setCompanyName(companyName);
			final CreateAccountOperation operation = new CreateAccountOperation();
			operation.setOperand(account);
			operation.setDescriptiveName(descriptiveName);
			operation.setOperator(Operator.ADD);
			final CreateAccountOperation[] operations = new CreateAccountOperation[]{ operation };
			final CreateAccountRetriableGoogleOperation retriableOperation = new CreateAccountRetriableGoogleOperation(createAccountService, operations, SemplestUtils.DEFAULT_RETRY_COUNT); 
			final Account[] result = retriableOperation.performOperation();
			return result[0];
		}
		catch (ServiceException e)
		{
			throw new Exception("Create Account for CompanyName [" + companyName + "], DescriptiveName [" + descriptiveName + "], CurrencyCode [" + currencyCode + "], DateTimeZone [" + dateTimeZone + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Create Account for CompanyName [" + companyName + "], DescriptiveName [" + descriptiveName + "], CurrencyCode [" + currencyCode + "], DateTimeZone [" + dateTimeZone + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Create Account for CompanyName [" + companyName + "], DescriptiveName [" + descriptiveName + "], CurrencyCode [" + currencyCode + "], DateTimeZone [" + dateTimeZone + "]", e);
		}

	}

	@Override
	public void addAccountBudget(Long microBudgetAmount, String customerId, String orderId) throws Exception
	{

	}

	@Override
	public String[] getClientAccounts() throws Exception
	{
		AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
		// Get the InfoService.
		InfoServiceInterface infoService = user.getService(AdWordsService.V201109.INFO_SERVICE);
		// ClientEmails to look up clientCustomerId for.
		String[] clientEmails = new String[]
		{ email };
		// Create selector.
		InfoSelector selector = new InfoSelector();
		selector.setApiUsageType(ApiUsageType.UNIT_COUNT_FOR_CLIENTS);
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		selector.setDateRange(new DateRange(today, today));
		selector.setClientEmails(clientEmails);
		selector.setIncludeSubAccounts(true);
		// Get api usage info.
		ApiUsageInfo apiUsageInfo = infoService.get(selector);
		for (ApiUsageRecord record : apiUsageInfo.getApiUsageRecords())
		{
			System.out.printf("Client with email '%s' has ID '%d'.\n", record.getClientEmail(), record.getClientCustomerId());

		}
		return null;
	}

	@Override
	public Budget[] getAccountBudgets(String customerId) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccountBudget(Budget budgetForUpdate, Long microBudgetAmount, String customerId, String orderId) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void addAccountBudget(DateTimeFloored start, DateTimeCeiling end, Long microBudgetAmount, String string) throws Exception
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountBudgetCannotChangeTheStartDateOfTheCurrentBudget(Budget budgetForUpdate, DateTimeCeiling end, Long microBudgetAmount,
			String string) throws Exception
	{
		// TODO Auto-generated method stub

	}

	public String AddAdGroup(String json) throws Exception
	{
		logger.debug("call AddAdGroup" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long campaignID = Long.parseLong(data.get("campaignID"));
		Long defaultMicroBid = Long.parseLong(data.get("defaultMicroBid"));
		Long adGroupID = AddAdGroup(data.get("accountID"), campaignID, data.get("AdGroupName"), AdGroupStatus.fromString(data.get("status")), defaultMicroBid);
		return gson.toJson(adGroupID);
	}

	@Override
	public Long AddAdGroup(String accountID, Long campaignID, String AdGroupName, AdGroupStatus status, Long defaultMicroBid) throws Exception
	{
		logger.info("Will try to Add AdGroup for AccountID [" + accountID + "], CampaignID [" + campaignID + "], AdGroupName [" + AdGroupName + "], AdGroupStatus [" + status + "], DefaultMicroBid [" + defaultMicroBid + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final AdGroupServiceInterface adGroupService = user.getService(AdWordsService.V201109.ADGROUP_SERVICE);
			final AdGroup adGroup = new AdGroup();
			adGroup.setName(AdGroupName);
			adGroup.setStatus(status);
			adGroup.setCampaignId(campaignID);
			final ManualCPCAdGroupBids adGroupBids = new ManualCPCAdGroupBids();
			final Money money = new Money();
			money.setMicroAmount(defaultMicroBid);
			adGroupBids.setKeywordMaxCpc(new Bid(money));
			adGroup.setBids(adGroupBids);
			final AdGroupOperation operation = new AdGroupOperation();
			operation.setOperand(adGroup);
			operation.setOperator(Operator.ADD);
			final AdGroupOperation[] operations = new AdGroupOperation[]{ operation };
			final AdGroupRetriableGoogleOperation retriableOperation = new AdGroupRetriableGoogleOperation(adGroupService, operations, SemplestUtils.DEFAULT_RETRY_COUNT); 			
			final AdGroupReturnValue result = retriableOperation.performOperation();
			if (result != null && result.getValue() != null)
			{
				return result.getValue()[0].getId();
			}
			else
			{
				return null;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem Adding AdGroup for AccountID [" + accountID + "], CampaignID [" + campaignID + "], AdGroupName [" + AdGroupName + "], AdGroupStatus [" + status + "], DefaultMicroBid [" + defaultMicroBid + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem Adding AdGroup for AccountID [" + accountID + "], CampaignID [" + campaignID + "], AdGroupName [" + AdGroupName + "], AdGroupStatus [" + status + "], DefaultMicroBid [" + defaultMicroBid + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem Adding AdGroup for AccountID [" + accountID + "], CampaignID [" + campaignID + "], AdGroupName [" + AdGroupName + "], AdGroupStatus [" + status + "], DefaultMicroBid [" + defaultMicroBid + "]", e);
		}
	}

	public String addTextAds(String json) throws Exception
	{
		logger.debug("call addTextAds (" + json + ")");		
		final Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		final String requestString = data.get("request");		
		final GoogleAddAdsRequest request = gson.fromJson(requestString, GoogleAddAdsRequest.class);
		final Map<GoogleAddAdRequest, Long> response = addTextAds(request);
		return gson.toJson(response);
	}
	
	public static AdGroupAdOperation getAddAdOperation(Long adGroupID, String headline, String description1, String description2, String displayURL, String url)
	{
		final TextAd textAd = new TextAd();
		textAd.setHeadline(SemplestUtils.getTrimmedNonNullString(headline));
		textAd.setDescription1(SemplestUtils.getTrimmedNonNullString(description1));
		textAd.setDescription2(SemplestUtils.getTrimmedNonNullString(description2));
		textAd.setDisplayUrl(SemplestUtils.getTrimmedNonNullString(displayURL));
		textAd.setUrl(SemplestUtils.getTrimmedNonNullString(url));
		final AdGroupAd textAdGroupAd = new AdGroupAd();
		textAdGroupAd.setAdGroupId(adGroupID.longValue());
		textAdGroupAd.setAd(textAd);
		final AdGroupAdOperation textAdGroupAdOperation = new AdGroupAdOperation();
		textAdGroupAdOperation.setOperand(textAdGroupAd);
		textAdGroupAdOperation.setOperator(Operator.ADD);
		return textAdGroupAdOperation;
	}
	
	public GoogleAddAdRequest getTextRequestForGoogleAd(final TextAd ad, final List<GoogleAddAdRequest> textRequests)
	{
		final String adHeadline = ad.getHeadline();
		final String adDescription1 = ad.getDescription1();
		final String adDescription2 = ad.getDescription2();
		for (final GoogleAddAdRequest textRequest : textRequests)
		{
			final String textRequestHeadline = textRequest.getHeadline();
			final String textRequestDescription1 = textRequest.getDescription1();
			final String textRequestDescription2 = textRequest.getDescription2();
			if (adHeadline.equals(textRequestHeadline) && adDescription1.equals(textRequestDescription1) && adDescription2.equals(textRequestDescription2))
			{
				return textRequest;
			}
		}
		return null;
	}
	
	/*
	 * Ads can show, including spaces, 25 characters for the title, 70
	 * characters for the ad text and 35 characters for a Display URL (or
	 * approximately 17 for languages that use non-ASCII (multi-byte)
	 * characters). NEED to Break up 35/Desc1, 35 Desc2
	 */
	@Override
	public final Map<GoogleAddAdRequest, Long> addTextAds(final GoogleAddAdsRequest request)
			throws Exception
	{
		logger.info("Will try to add Text Ads:\n" + request.toStringPretty());
		final String accountID = request.getAccountID();
		final Long adGroupID = request.getAdGroupID();
		final String displayURL = request.getDisplayURL();
		if (!displayURL.startsWith("http"))
		{
			throw new Exception("DisplayURL [" + displayURL + "] within the following GoogleAddAdsRequest does NOT start with 'http': [" + request + "]");
		}
		final String url = request.getDisplayURL();
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final AdGroupAdServiceInterface adGroupAdService = user.getService(AdWordsService.V201109.ADGROUP_AD_SERVICE);
			final List<AdGroupAdOperation> operationList = new ArrayList<AdGroupAdOperation>();
			final List<GoogleAddAdRequest> textRequests = request.getAddAdTextRequests();
			for (final GoogleAddAdRequest textRequest : textRequests)
			{
				final String headline = textRequest.getHeadline();
				final String description1 = textRequest.getDescription1();
				final String description2 = textRequest.getDescription2();
				final AdGroupAdOperation adGroupAdOperation = getAddAdOperation(adGroupID, headline, description1, description2, displayURL, url);
				operationList.add(adGroupAdOperation);
			}			
			final AdGroupAdOperation[] operations = operationList.toArray(new AdGroupAdOperation[operationList.size()]);
			final AdGroupAdRetriableGoogleOperation retriableOperation = new AdGroupAdRetriableGoogleOperation(adGroupAdService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);
			final AdGroupAdReturnValue result = retriableOperation.performOperation();
			if (result != null && result.getValue() != null)
			{
				final Map<GoogleAddAdRequest, Long> requestToGoogleAdIdMap = new HashMap<GoogleAddAdRequest, Long>();
				final AdGroupAd[] adGroupAds = result.getValue();
				for (final AdGroupAd adGroupAd : adGroupAds)
				{
					final Ad ad = adGroupAd.getAd();
					if (ad instanceof TextAd)
					{
						final TextAd textAd = (TextAd)ad;
						final GoogleAddAdRequest textRequestForGoogleAd = getTextRequestForGoogleAd(textAd, textRequests);	
						if (textRequestForGoogleAd == null)
						{
							logger.error("Could not find the original AddAd request associated with the Ad that was created in Google.  This is not expected.");							
						}				
						else
						{
							final Long googleAdID = textAd.getId();
							requestToGoogleAdIdMap.put(textRequestForGoogleAd, googleAdID);
						}
					}					
					else
					{
						logger.warn("Found a GoogleAd within results that is not instanceof TextAd but [" + ad.getClass().getName() + "]");
					}
				}
				logger.info("Generated " + requestToGoogleAdIdMap.size() + " mappings between Original Semplest Ad Requests <-> Google Ad Ids");
				return requestToGoogleAdIdMap;
			}
			else
			{
				throw new Exception("Problem adding ads for the following request because the result received from Google operation is empty: [" + request + "]");
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem adding ads for request [" + request + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem adding ads for request [" + request + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem adding ads for request [" + request + "]", e);
		}

	}

	public String getAllAdGroupCriteria(String json) throws Exception
	{
		logger.debug("call getAllAdGroupCriteria(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		AdGroupCriterion[] res = getAllAdGroupCriteria(data.get("accountID"), adGroupID, Boolean.valueOf(data.get("ActiveOnly")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public AdGroupCriterion[] getAllAdGroupCriteria(String accountID, Long adGroupID, Boolean ActiveOnly) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
			Selector selector = new Selector();
			selector.setFields(new String[]{ "Id", "KeywordText", "KeywordMatchType", "ApprovalStatus", "Status", "MaxCpc", "QualityScore", "FirstPageCpc", "SystemServingStatus" });
			selector.setOrdering(new OrderBy[]{ new OrderBy("AdGroupId", SortOrder.ASCENDING) });
			Predicate adGroupIdPredicate = new Predicate("AdGroupId", PredicateOperator.IN, new String[]{ adGroupID.toString() });
			if (ActiveOnly)
			{
				Predicate statusPredicate = new Predicate("Status", PredicateOperator.IN, new String[]{ "ACTIVE" });
				selector.setPredicates(new Predicate[]{ adGroupIdPredicate, statusPredicate });
			}
			else
			{
				selector.setPredicates(new Predicate[]{ adGroupIdPredicate });
			}
			AdGroupCriterionPage page = adGroupCriterionService.get(selector);
			if (page.getEntries() != null && page.getEntries().length > 0)
			{
				return page.getEntries();
			}
			else
			{
				return new AdGroupCriterion[0];
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], ActiveOnly [" + ActiveOnly + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], ActiveOnly [" + ActiveOnly + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], ActiveOnly [" + ActiveOnly + "]", e);
		}
	}

	public String getAllBiddableAdGroupCriteria(String json) throws Exception
	{
		logger.debug("call getAllBiddableAdGroupCriteria(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		KeywordDataObject[] res = getAllBiddableAdGroupCriteria(data.get("accountID"), adGroupID, Boolean.valueOf(data.get("ActiveOnly")));
		return gson.toJson(res);
	}

	@Override
	public KeywordDataObject[] getAllBiddableAdGroupCriteria(String accountID, Long adGroupID, Boolean ActiveOnly) throws Exception
	{
		List<KeywordDataObject> result = new ArrayList<KeywordDataObject>();
		for (AdGroupCriterion criterion : getAllAdGroupCriteria(accountID, adGroupID, ActiveOnly))
		{
			if (criterion instanceof BiddableAdGroupCriterion)
			{
				BiddableAdGroupCriterion res = (BiddableAdGroupCriterion) criterion;
				KeywordDataObject bidRes = new KeywordDataObject();
				if (res.getQualityInfo() != null)
				{
					bidRes.setQualityScore(res.getQualityInfo().getQualityScore());
				}
				if (res.getFirstPageCpc() != null)
				{
					bidRes.setFirstPageCpc(res.getFirstPageCpc().getAmount().getMicroAmount());
				}
				bidRes.setBidID(res.getCriterion().getId());
				if (res.getSystemServingStatus() != null)
				{
					if (res.getSystemServingStatus().getValue().equalsIgnoreCase("Eligible"))
					{
						bidRes.setIsEligibleForShowing(true);
					}
					else
					{
						bidRes.setIsEligibleForShowing(false);
					}
				}
				if (res.getApprovalStatus() != null)
				{
					bidRes.setApprovalStatus(res.getApprovalStatus().getValue());
				}
				Keyword keyword = ((Keyword) criterion.getCriterion());
				bidRes.setKeyword((keyword.getText()));

				bidRes.setMatchType(keyword.getMatchType().getValue());
				if (res.getBids() != null)
				{
					bidRes.setMicroBidAmount(((ManualCPCAdGroupCriterionBids) res.getBids()).getMaxCpc().getAmount().getMicroAmount());
				}
				result.add(bidRes);
			}
		}
		return result.toArray(new KeywordDataObject[result.size()]);
	}

	public String getAllAdGroupKeywords(String json) throws Exception
	{
		logger.debug("call getAllAdGroupKeywords(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));

		String[] res = getAllAdGroupKeywords(data.get("accountID"), adGroupID, Boolean.valueOf(data.get("ActiveOnly")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public String[] getAllAdGroupKeywords(String accountID, Long adGroupID, Boolean ActiveOnly) throws Exception
	{
		List<String> keywords = new ArrayList<String>();
		for (KeywordDataObject criterion : getAllBiddableAdGroupCriteria(accountID, adGroupID, ActiveOnly))
		{
			keywords.add(criterion.getKeyword());
		}
		return keywords.toArray(new String[keywords.size()]);
	}
	
	public Map<String, Long> getAllRegularKeywordsToCriterionIdMap(String accountID, Long adGroupID, Boolean ActiveOnly, KeywordMatchType matchType) throws Exception
	{
		final Map<String, Long> getAllRegularKeywordsToCriterionIdMap = new HashMap<String, Long>();
		final AdGroupCriterion[] biddableAdGroupCriteria = getAllAdGroupCriteria(accountID, adGroupID, ActiveOnly); 
		for (final AdGroupCriterion adGroupCriterion : biddableAdGroupCriteria)
		{
			if (adGroupCriterion instanceof BiddableAdGroupCriterion)
			{
				final BiddableAdGroupCriterion biddableAdGroupCriterion = (BiddableAdGroupCriterion)adGroupCriterion;
				final Criterion criterion = biddableAdGroupCriterion.getCriterion();
				if (criterion instanceof Keyword)
				{
					final Keyword keyword = (Keyword)criterion;
					final KeywordMatchType keywordMatchType = keyword.getMatchType();
					if (keywordMatchType == matchType)
					{
						final Long criterionId = keyword.getId();
						final String criterionKeyword = keyword.getText();
						getAllRegularKeywordsToCriterionIdMap.put(criterionKeyword, criterionId);
					}
				}
			}					
		}
		return getAllRegularKeywordsToCriterionIdMap;
	}

	@Override
	public AdGroupAd[] getAdsByAdGroupId(String accountID, long adGroupId) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			AdGroupAdServiceInterface adGroupAdService = user.getService(AdWordsService.V201109.ADGROUP_AD_SERVICE);
			Selector selector = new Selector();
			selector.setFields(new String[] {"Id", "AdGroupId", "Status"});
			selector.setOrdering(new OrderBy[]{ new OrderBy("AdGroupId", SortOrder.ASCENDING) });
			Predicate adGroupIdPredicate = new Predicate("AdGroupId", PredicateOperator.IN, new String[]{ String.valueOf(adGroupId) });			
			selector.setPredicates(new Predicate[]{ adGroupIdPredicate, adGroupIdPredicate });
			
			AdGroupAdPage page = adGroupAdService.get(selector);
			if (page.getEntries() != null && page.getEntries().length > 0)
			{
				return page.getEntries();
			}
			else
			{
				return new AdGroupAd[0];
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupId + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupId + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem getting All AdGroupCriteria for AccountID [" + accountID + "], AdGroupID [" + adGroupId + "]", e);
		}
	}

	public String deleteAds(String json) throws Exception
	{
		logger.debug("call deleteAds(String json) [" + json + "]");
		final Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		final String accountID = data.get("accountID");
		final Long adGroupID = Long.parseLong(data.get("adGroupID"));
		final String adIdsString = data.get("adIds");
		final List<Long> adIds = gson.fromJson(adIdsString, SemplestUtils.TYPE_LIST_OF_LONGS);
		final List<Long> deletedAdIds = deleteAds(accountID, adGroupID, adIds);
		return gson.toJson(deletedAdIds);
	}
	
	public AdGroupAdOperation getRemoveAdOperation(Long adGroupID, Long AdID)
	{
		final Ad ad = new Ad();
		ad.setId(AdID.longValue());
		final AdGroupAd adGroupAd = new AdGroupAd();
		adGroupAd.setAdGroupId(adGroupID.longValue());
		adGroupAd.setAd(ad);
		final AdGroupAdOperation operation = new AdGroupAdOperation();
		operation.setOperand(adGroupAd);
		operation.setOperator(Operator.REMOVE);
		return operation;
	}
	
	public List<AdGroupAdOperation> getRemoveAdGroupAdOperations(Long adGroupID, List<Long> adIds)
	{
		final List<AdGroupAdOperation> operations = new ArrayList<AdGroupAdOperation>();
		for (final Long adId : adIds)
		{
			final AdGroupAdOperation operation = getRemoveAdOperation(adGroupID, adId);
			operations.add(operation);
		}
		return operations;
	}

	@Override
	public List<Long> deleteAds(String accountID, Long adGroupID, List<Long> adIds) throws Exception
	{
		logger.info("Will try to delete ads in Goole Adwords for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], AdIds [" + adIds + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final AdGroupAdServiceInterface adGroupAdService = user.getService(AdWordsService.V201109.ADGROUP_AD_SERVICE);
			final List<AdGroupAdOperation> operationList = getRemoveAdGroupAdOperations(adGroupID, adIds);
			final AdGroupAdOperation[] operations = operationList.toArray(new AdGroupAdOperation[operationList.size()]);			
			final AdGroupAdRetriableGoogleOperation retriableOperation = new AdGroupAdRetriableGoogleOperation(adGroupAdService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);			
			final AdGroupAdReturnValue result = retriableOperation.performOperation();
			final List<Long> deletedAdIds = new ArrayList<Long>();
			if (result != null && result.getValue() != null)
			{
				final AdGroupAd[] adGroupAdArray = result.getValue();
				for (final AdGroupAd adGroupAdReturned : adGroupAdArray)
				{
					final Ad returnedAd = adGroupAdReturned.getAd();
					final Long returnedAdID = returnedAd.getId();
					deletedAdIds.add(returnedAdID);
				}				
			}
			if (deletedAdIds.size() == adIds.size())
			{
				logger.info("Deleted all " + deletedAdIds.size() + " Ads as expected");	
			}				
			else
			{				
				Collections.sort(adIds);
				Collections.sort(deletedAdIds);
				logger.warn("Deleted " + deletedAdIds.size() + " Ads, though expected to delete " + adIds.size() + " Ads:\nExpected to delete AdIDs: [" + adIds + "]\nActually deleted AdIds: [" + deletedAdIds + "]");
			}
			return deletedAdIds;
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem deleting Google AdWords Ad for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], AdIds [" + adIds + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem deleting Google AdWords Ad for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], AdIds [" + adIds + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem deleting Google AdWords Ad for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], AdIds [" + adIds + "]", e);
		}
	}

	public String deleteAdGroup(String json) throws Exception
	{
		logger.debug("call deleteAdGroup(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		Boolean del = deleteAdGroup(data.get("accountID"), adGroupID);
		return gson.toJson(del);
	}

	@Override
	public Boolean deleteAdGroup(String accountID, Long adGroupID) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the AdGroupService.
			AdGroupServiceInterface adGroupService = user.getService(AdWordsService.V201109.ADGROUP_SERVICE);
			// Create ad group with DELETED status.
			AdGroup adGroup = new AdGroup();
			adGroup.setId(adGroupID.longValue());
			adGroup.setStatus(AdGroupStatus.DELETED);
			// Create operations.
			AdGroupOperation operation = new AdGroupOperation();
			operation.setOperand(adGroup);
			operation.setOperator(Operator.SET);
			AdGroupOperation[] operations = new AdGroupOperation[]
			{ operation };
			// Delete ad group.
			AdGroupReturnValue result = adGroupService.mutate(operations);
			// Display ad groups.
			if (result != null && result.getValue() != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	public String updateAds(String json) throws Exception
	{
		logger.debug("call updateAds(String json)" + json);
		final Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		final String requestString = data.get("request");
		final UpdateAdsRequestObj request = gson.fromJson(requestString, UpdateAdsRequestObj.class);
		final Map<UpdateAdRequest, Long> response = updateAds(request);
		return gson.toJson(response);
	}
	
	public static List<AdGroupAdOperation> getAddAdGroupAdOperations(final Long adGroupID, final List<UpdateAdRequest> updateRequests)
	{
		final List<AdGroupAdOperation> operations = new ArrayList<AdGroupAdOperation>();
		for (final UpdateAdRequest updateRequest : updateRequests)
		{
			final String newHeadline = updateRequest.getNewHeadline();
			final String newDescription1 = updateRequest.getNewDescription1();
			final String newDescription2 = updateRequest.getNewDescription2();
			final String newDisplayURL = updateRequest.getNewDisplayURL();
			final String newURL = updateRequest.getNewUrl();
			final AdGroupAdOperation addNewAdOperation = getAddAdOperation(adGroupID, newHeadline, newDescription1, newDescription2, newDisplayURL, newURL);
			operations.add(addNewAdOperation);
		}
		return operations;
	}
	
	public List<Long> getAdIds(final List<UpdateAdRequest> updateRequests)
	{
		final List<Long> adIds = new ArrayList<Long>();
		for (final UpdateAdRequest updateRequest : updateRequests)
		{
			final Long adId = updateRequest.getAdId();
			adIds.add(adId);
		}
		return adIds;
	}
	
	public UpdateAdRequest findMatchingRequest(final List<UpdateAdRequest> updateRequests, final TextAd textAd)
	{
		final String adDescription1 = textAd.getDescription1();
		final String adDescription2 = textAd.getDescription2();
		final String adHeadline = textAd.getHeadline();
		//final String displayUrl = textAd.getDisplayUrl();
		final String url = textAd.getUrl();
		for (final UpdateAdRequest updateRequest : updateRequests)
		{
			final String requestedNewDescription1 = updateRequest.getNewDescription1();
			final String requestedNewDescription2 = updateRequest.getNewDescription2();
			final String requestedHeadline = updateRequest.getNewHeadline();
			//final String requestedDisplayUrl = updateRequest.getNewDisplayURL();
			final String requestedUrl = updateRequest.getNewUrl();			
			if (adDescription1.equals(requestedNewDescription1) && 
				adDescription2.equals(requestedNewDescription2) && 
				adHeadline.equals(requestedHeadline) && 
				//displayUrl.equals(requestedDisplayUrl) && 
				url.equals(requestedUrl))
			{
				return updateRequest;
			}
		}
		return null;
	}

	/**
	 * Only status of an ad can be updated.  So in order to accommodate changes to other fields, like headline/description/etc, we add the new ad with new params and delete the old ad.
	 */
	@Override
	public Map<UpdateAdRequest, Long> updateAds(final UpdateAdsRequestObj request) throws Exception
	{			
		try
		{
			final List<UpdateAdRequest> updateRequests = request.getUpdateRequests();
			logger.info("Will try to update ads in Goole Adwords for request [" + request + "] which contains " + updateRequests.size() + " update requests");
			final Map<UpdateAdRequest, Long> requestToNewAdIdMap = new HashMap<UpdateAdRequest, Long>();
			final String accountID = request.getAccountID();
			final Long adGroupID = request.getAdGroupID();
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final AdGroupAdServiceInterface adGroupAdService = user.getService(AdWordsService.V201109.ADGROUP_AD_SERVICE);			
			final List<AdGroupAdOperation> addOperations = getAddAdGroupAdOperations(adGroupID, updateRequests);
			final List<Long> adIds = getAdIds(updateRequests);
			final List<AdGroupAdOperation> removeOperations = getRemoveAdGroupAdOperations(adGroupID, adIds);
			final List<AdGroupAdOperation> addAndRemoveOperations = new ArrayList<AdGroupAdOperation>();
			addAndRemoveOperations.addAll(addOperations);
			addAndRemoveOperations.addAll(removeOperations);
			final AdGroupAdOperation[] operations = addAndRemoveOperations.toArray(new AdGroupAdOperation[addAndRemoveOperations.size()]);			
			final AdGroupAdRetriableGoogleOperation retriableOperation = new AdGroupAdRetriableGoogleOperation(adGroupAdService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);			
			final AdGroupAdReturnValue result = retriableOperation.performOperation();			
			if (result != null && result.getValue() != null)
			{
				final AdGroupAd[] adGroupAdArray = result.getValue();
				for (final AdGroupAd adGroupAd : adGroupAdArray)
				{					
					final Ad returnedAd = adGroupAd.getAd();
					final AdGroupAdStatus returnedAdStatus = adGroupAd.getStatus();					
					if (returnedAd instanceof TextAd && returnedAdStatus == AdGroupAdStatus.ENABLED)
					{
						final Long newAdID = returnedAd.getId();
						final TextAd textAd = (TextAd)returnedAd;
						final UpdateAdRequest matchingRequest = findMatchingRequest(updateRequests, textAd);
						if (matchingRequest == null)
						{							
							logger.warn("Could not find matching request for Google Text Ad [" + (textAd == null ? "null" : SemplestUtils.getGoogleTextAdString(textAd) + ", status=[" + returnedAdStatus + "]"));
						}
						else
						{
							requestToNewAdIdMap.put(matchingRequest, newAdID);
						}
					}							
				}			
				logger.info("Generated " + requestToNewAdIdMap.size() + " mappings of Original Request <-> New Ad ID");
				return requestToNewAdIdMap;
			}
			else
			{
				throw new Exception("The result returned from Google is empty, which means the UpdateAds operations weren't executed");
			}	
		}
		catch (Exception e)
		{
			throw new Exception("Problem Updating Ads in Google for request [" + request + "]", e);
		}
	}
	
	public static Map<String, Long> getKeywordToIdMap(final List<String> keywords, final AdGroupCriterion[] adGroupCriterions)
	{
		final Map<String, Long> keywordToIdMap = new HashMap<String, Long>();
		for (final AdGroupCriterion adGroupCriterion : adGroupCriterions)
		{
			final Criterion criterion = adGroupCriterion.getCriterion();
			if (criterion.getType() == CriterionType.KEYWORD)
			{
				final Keyword keywordCriterion = (Keyword)criterion;
				final String keywordCriterionText = keywordCriterion.getText();
				if (keywords.contains(keywordCriterionText))
				{
					final Long keywordID = keywordCriterion.getId();
					keywordToIdMap.put(keywordCriterionText, keywordID);
				}
			}
		}
		return keywordToIdMap;
	}
	
	public static List<AdGroupCriterionOperation> getRemoveKeywordOperations(final Long adGroupID, final Map<String, Long> keywordToIdMap)
	{
		final List<AdGroupCriterionOperation> operations = new ArrayList<AdGroupCriterionOperation>();
		final Set<Entry<String, Long>> entrySet = keywordToIdMap.entrySet();
		for (final Entry<String, Long> entry : entrySet)
		{
			final String keyword = entry.getKey();
			final Long keywordID = entry.getValue();
			final Keyword keywrd = new Keyword();
			keywrd.setText(keyword);
			keywrd.setId(keywordID);
			final BiddableAdGroupCriterion keywordBiddableAdGroupCriterion = new BiddableAdGroupCriterion();
			keywordBiddableAdGroupCriterion.setAdGroupId(adGroupID);
			keywordBiddableAdGroupCriterion.setCriterion(keywrd);
			final AdGroupCriterionOperation keywordAdGroupCriterionOperation = new AdGroupCriterionOperation();
			keywordAdGroupCriterionOperation.setOperand(keywordBiddableAdGroupCriterion);
			keywordAdGroupCriterionOperation.setOperator(Operator.REMOVE);
			operations.add(keywordAdGroupCriterionOperation);
		}
		return operations;
	}
	
	@Override
	public Boolean deleteNegativeKeywords(String accountID, Long campaignID, List<String> keywords, KeywordMatchType matchType) throws Exception
	{
		logger.info("Will try to Delete these " + keywords.size() + " Negative Keywords for AccountID [" + accountID + "], CampaignID [" + campaignID + "], MatchType [" + matchType + "]: [" + keywords + "]");
		try
		{			
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final Map<String, Long> negativeKeywordsToCriterionIdMap = getAllNegativeKeywordsToCriterionIdMap(accountID, campaignID, matchType);
			final Set<Entry<String, Long>> negativeKeywordToCriterionIdEntrySet = negativeKeywordsToCriterionIdMap.entrySet();
			final List<CampaignCriterionOperation> operations = new ArrayList<CampaignCriterionOperation>();
			for (final Entry<String, Long> entry : negativeKeywordToCriterionIdEntrySet)
			{
				final String criterionKeyword = entry.getKey();
				if (keywords.contains(criterionKeyword))
				{
					final Long criterionId = entry.getValue();				
					final CampaignCriterionOperation operation = getNegativeKeywordRemoveOperation(campaignID, criterionId);
					operations.add(operation);
				}				
			}
			logger.info("Generated " + operations.size() + " Operations to Delete Negative Keywords");
			if (operations.isEmpty())
			{
				logger.warn("Nothing to delete");
				return false;
			}
			final CampaignCriterionOperation[] negativeKeywordOperations = operations.toArray(new CampaignCriterionOperation[operations.size()]);
			final CampaignCriterionServiceInterface campaignCriterionService = user.getService(AdWordsService.V201109.CAMPAIGN_CRITERION_SERVICE);			
			final CampaignCriterionRetriableGoogleOperation retriableGoogleOperation = new CampaignCriterionRetriableGoogleOperation(campaignCriterionService, negativeKeywordOperations, SemplestUtils.DEFAULT_RETRY_COUNT);
			final CampaignCriterionReturnValue result = retriableGoogleOperation.performOperation();
			if (result != null && result.getValue() != null)
			{
				final CampaignCriterion[] campaignCriterions = result.getValue();
				final int numNegativeKeywordResults = campaignCriterions.length;
				if (numNegativeKeywordResults != operations.size())
				{
					logger.warn("# of NegativeKeyword Results [" + numNegativeKeywordResults + "] is NOT equal to the # we expected [" + operations.size() + "]");
				}
				else
				{
					logger.warn("As expected, # of NegativeKeyword Results [" + numNegativeKeywordResults + "] is equal to the # we expected [" + operations.size() + "]");					
				}
				return true;
			}
			else
			{
				logger.error("No results returned from Google when executing Delete NegativeKeyword operations.  This is NOT expected.");
				return false;
			}
		}
		catch (ServiceException e)
		{
			final String errMsg = "Problem Deleting Negative Keywords for AccountID [" + accountID + "], CampaignID [" + campaignID + "], MatchType [" + matchType + "], and Keywords [" + keywords + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem Deleting Negative Keywords for AccountID [" + accountID + "], CampaignID [" + campaignID + "], MatchType [" + matchType + "], and Keywords [" + keywords + "]: " + e.dumpToString();
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem Deleting Negative Keywords for AccountID [" + accountID + "], CampaignID [" + campaignID + "], MatchType [" + matchType + "], and Keywords [" + keywords + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
	}
	
	@Override
	public Boolean deleteKeyWords(String accountID, Long adGroupID, List<String> keywords) throws Exception
	{
		logger.info("Will try to delete these " + keywords.size() + " Keywords for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "]: [" + keywords + "]");
		try
		{			
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);			
			final AdGroupCriterion[] adGroupCriterions = getAllAdGroupCriteria(accountID, adGroupID, false);
			if (adGroupCriterions == null)
			{
				logger.info("Problem deleting Keywords for Account [" + accountID + "] and AdGroup [" + adGroupID + "] because found no AdGroupCriterions for that AdGroup");
				return false;
			}
			logger.info("Will look through " + adGroupCriterions.length + " AdGroupCriterions for the Ids of the Keywords which we're trying to delete");
			final Map<String, Long> keywordToIdMap = getKeywordToIdMap(keywords, adGroupCriterions);
			logger.info("Out of " + keywords.size() + " keywords that we're trying to delete, found " + keywordToIdMap.size() + " in Google");
			if (keywordToIdMap.isEmpty())
			{
				logger.info("Found no keywords in google do delete as per the request for AccountID [" + accountID + "], AdGroupID [" + adGroupID + "], Keywords [" + keywords + "]");
				return true;
			}
			final List<AdGroupCriterionOperation> removeKeywordOperations = getRemoveKeywordOperations(adGroupID, keywordToIdMap);
			final AdGroupCriterionOperation[] operations = removeKeywordOperations.toArray(new AdGroupCriterionOperation[removeKeywordOperations.size()]);
			final AdGroupCriterionRetriableGoogleOperation retriableOperation = new AdGroupCriterionRetriableGoogleOperation(adGroupCriterionService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);
			final AdGroupCriterionReturnValue result = retriableOperation.performOperation();
			if (result != null && result.getValue() != null)
			{
				final AdGroupCriterion[] returnedAdGroupCriterions = result.getValue();
				final List<Long> returnedKeywordIds = new ArrayList<Long>();
				for (final AdGroupCriterion returnedAdGroupCriterion : returnedAdGroupCriterions)
				{
					if (returnedAdGroupCriterion instanceof BiddableAdGroupCriterion)
					{
						final Criterion returnedCriterion = returnedAdGroupCriterion.getCriterion();
						if (returnedCriterion instanceof Keyword)
						{
							final Keyword returnedKeyword = (Keyword)returnedCriterion;
							final Long returnedKeywordID = returnedKeyword.getId();
							returnedKeywordIds.add(returnedKeywordID);
						}
					}
				}
				logger.info("Deleted " + returnedKeywordIds.size() + " keywords out of the " + keywordToIdMap.size() + " that we could delete (total requested to be deleted originally is " + keywords.size() + ")");
				return true;
			}
			else
			{
				logger.info("Problem deleting Keywords for AccountID [" + accountID + "] and AdGroupID [" + adGroupID + "] because the operations returned no results");
				return false;
			}
		}
		catch (ServiceException e)
		{
			final String errMsg = "Problem deleting Keywords for AdGroupID [" + adGroupID + "] and AccountID [" + accountID + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem deleting Keywords for AdGroupID [" + adGroupID + "] and AccountID [" + accountID + "]: " + e.dumpToString();
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem deleting Keywords for AdGroupID [" + adGroupID + "] and AccountID [" + accountID + "]";
			logger.error(errMsg, e);
			throw new Exception(errMsg, e);
		}
	}

	public String addKeyWordToAdGroup(String json) throws Exception
	{
		logger.debug("call addKeyWordToAdGroup" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		Long microBidAmount = Long.parseLong(data.get("microBidAmount"));
		KeywordDataObject res = addKeyWordToAdGroup(data.get("accountID"), adGroupID, data.get("keyword"), KeywordMatchType.fromString(data.get("matchType")), microBidAmount);
		return gson.toJson(res);
	}
	
	public List<AdGroupCriterionOperation> getAddKeywordOperations(final Long adGroupId, final List<GoogleAddKeywordRequest> requests)
	{
		final List<AdGroupCriterionOperation> addKeywordOperations = new ArrayList<AdGroupCriterionOperation>();
		for (final GoogleAddKeywordRequest request : requests)
		{
			final String keywordString = request.getKeyword();
			final KeywordMatchType matchType = request.getMatchType();
			final Long microBidAmount = request.getMicroBidAmount();
			final AdGroupCriterionOperation addKeywordOperation = getRegularKeywordAddOperation(adGroupId, keywordString, matchType, microBidAmount);
			addKeywordOperations.add(addKeywordOperation);
		}
		return addKeywordOperations;
	}
	
	public GoogleAddKeywordRequest getGoogleAddKeywordRequest(final List<GoogleAddKeywordRequest> requests, final String keywordText, final KeywordMatchType keywordMatchType)
	{
		for (final GoogleAddKeywordRequest request: requests)
		{
			final String requestKeywordText = request.getKeyword();
			final KeywordMatchType requestKeywordMatchType = request.getMatchType();
			if (requestKeywordMatchType == keywordMatchType && requestKeywordText.equals(keywordText))
			{
				return request;
			}
		}
		return null;
	}
	
	@Override
	public Map<GoogleAddKeywordRequest, Long> addKeywords(final String accountId, final Long adGroupId, final List<GoogleAddKeywordRequest> requests) throws Exception
	{
		logger.info("Will try to Add Keywords for AccountID [" + accountId + "], AdGroupID [" + adGroupId + "], " + requests.size() + " GoogleAddKeywordRequests [<potentially to many to practically print>]");
		final Map<GoogleAddKeywordRequest, Long> requestToIdMap = new HashMap<GoogleAddKeywordRequest, Long>();
		final List<AdGroupCriterionOperation> addKeywordOperations = getAddKeywordOperations(adGroupId, requests);		
		final AdWordsUser user = new AdWordsUser(email, password, accountId, userAgent, developerToken, useSandbox);
		final AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
		final AdGroupCriterionOperation[] addKeywordOperationsArray = addKeywordOperations.toArray(new AdGroupCriterionOperation[addKeywordOperations.size()]);
		final AdGroupCriterionRetriableGoogleOperation retriableOperation = new AdGroupCriterionRetriableGoogleOperation(adGroupCriterionService, addKeywordOperationsArray, SemplestUtils.DEFAULT_RETRY_COUNT);
		final AdGroupCriterionReturnValue regularKeywordResult = retriableOperation.performOperation();
		if (regularKeywordResult != null && regularKeywordResult.getValue() != null)
		{
			final AdGroupCriterion[] adGroupCriterions = regularKeywordResult.getValue();
			for (final AdGroupCriterion adGroupCriterion : adGroupCriterions)
			{
				if (adGroupCriterion instanceof BiddableAdGroupCriterion)
				{
					final Criterion criterion = adGroupCriterion.getCriterion();
					if (criterion instanceof Keyword)
					{
						final Keyword keyword = (Keyword)criterion;
						final Long googleKeywordId = keyword.getId();
						final String keywordText = keyword.getText();
						final KeywordMatchType keywordMatchType = keyword.getMatchType();
						final GoogleAddKeywordRequest originalRequest = getGoogleAddKeywordRequest(requests, keywordText, keywordMatchType);
						if (originalRequest == null)
						{
							logger.warn("Could not find original GoogleAddKeywordRequest for KeywordText [" + keywordText + "] and KeywordMatchType [" + keywordMatchType + "] from within the Keyword Criterions returned from Google");
						}
						else
						{
							requestToIdMap.put(originalRequest, googleKeywordId);
						}
					}
				}
			}
			if (requestToIdMap.size() != requests.size())
			{
				logger.warn("# of GoogleAddKeywordRequest<->GoogleKeywordId mappings [" + requestToIdMap.size() + "] is NOT equal to the # of original GoogleAddKeywordRequests [" + requests.size() + "]");						
			}
			else
			{
				logger.warn("As expected, # of GoogleAddKeywordRequest<->GoogleKeywordId mappings [" + requestToIdMap.size() + "] is equal to the # of original GoogleAddKeywordRequests [" + requests.size() + "]");
			}
		}
		else
		{
			final String errMsg = "No results returned from Google when executing RegularKeyword Add operations.  This is NOT expected.";
			logger.error(errMsg);
			throw new Exception(errMsg);
		}
		return requestToIdMap;
	}
	
	@Override
	public KeywordDataObject addKeyWordToAdGroup(String accountID, Long adGroupID, String keyword, KeywordMatchType matchType, Long microBidAmount)
			throws Exception
	{
		try
		{
			// AdWordsServiceLogger.log(); //SOAP XML Logger

			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the AdGroupCriterionService.
			AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
			// Create keyword.
			Keyword keywrd = new Keyword();
			keywrd.setText(keyword);
			keywrd.setMatchType(matchType);
			// Create biddable ad group criterion.
			BiddableAdGroupCriterion keywordBiddableAdGroupCriterion = new BiddableAdGroupCriterion();
			keywordBiddableAdGroupCriterion.setAdGroupId(adGroupID);
			keywordBiddableAdGroupCriterion.setCriterion(keywrd);
			// add bid amount
			ManualCPCAdGroupCriterionBids bid = new ManualCPCAdGroupCriterionBids();
			Money money = new Money();
			money.setMicroAmount(microBidAmount);
			bid.setMaxCpc(new Bid(money));

			keywordBiddableAdGroupCriterion.setBids(bid);
			// Create operations.
			AdGroupCriterionOperation keywordAdGroupCriterionOperation = new AdGroupCriterionOperation();
			keywordAdGroupCriterionOperation.setOperand(keywordBiddableAdGroupCriterion);
			keywordAdGroupCriterionOperation.setOperator(Operator.ADD);
			AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[]
			{ keywordAdGroupCriterionOperation };
			// Add ad group criteria.
			AdGroupCriterionReturnValue result = adGroupCriterionService.mutate(operations);
			// Display ad group criteria.
			if (result != null && result.getValue() != null && (result.getValue(0) instanceof BiddableAdGroupCriterion))
			{
				BiddableAdGroupCriterion res = (BiddableAdGroupCriterion) result.getValue(0);
				KeywordDataObject bidRes = new KeywordDataObject();
				if (res.getQualityInfo() != null)
				{
					bidRes.setQualityScore(res.getQualityInfo().getQualityScore());
				}
				if (res.getFirstPageCpc() != null)
				{
					bidRes.setFirstPageCpc(res.getFirstPageCpc().getAmount().getMicroAmount());
				}
				bidRes.setBidID(res.getCriterion().getId());
				bidRes.setApprovalStatus(res.getApprovalStatus().getValue());
				bidRes.setKeyword(keyword);
				bidRes.setMatchType(matchType.getValue());
				bidRes.setMicroBidAmount(((ManualCPCAdGroupCriterionBids) res.getBids()).getMaxCpc().getAmount().getMicroAmount());
				return bidRes;
			}
			else
			{
				return new KeywordDataObject();
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}
	
	public ArrayList<KeywordToolStats> getGoogleVolumeCompetition(String[] keywords, KeywordMatchType matchType) throws Exception
	{
	
		ArrayList<KeywordToolStats> returnData = new ArrayList<KeywordToolStats>();
		TargetingIdeaPage page;
		TargetingIdeaServiceInterface targetingIdeaService;
		
		if (keywords == null)
		{
			throw new Exception("Empty keyword list!");
		}
		
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// Get the TargetingIdeaService
			targetingIdeaService = user.getService(AdWordsService.V201109.TARGETING_IDEA_SERVICE);
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}


		// Create selector.
		TargetingIdeaSelector selector = new TargetingIdeaSelector();
		selector.setRequestType(RequestType.STATS);
		selector.setIdeaType(IdeaType.KEYWORD);
		selector.setRequestedAttributeTypes(new AttributeType[]
		{ AttributeType.CRITERION, AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES, AttributeType.COMPETITION }); // AttributeType.APPROX_CONTENT_IMPRESSIONS_PER_DAY,//
																													// });

		selector.setLocaleCode("US");
		selector.setCurrencyCode("USD");


		// Set selector paging (required for targeting idea service).
		Paging paging = new Paging();
		paging.setStartIndex(0);
		if (matchType == null)
		{
			paging.setNumberResults(3*keywords.length);
		}
		else
		{
			paging.setNumberResults(keywords.length);
		}
		selector.setPaging(paging);


		ArrayList<SearchParameter> searchParamList = new ArrayList<SearchParameter>();


		
		
		ArrayList<Keyword> wordList = new ArrayList<Keyword>();
		
		if (matchType == null)
		{
			for (String word : keywords)
			{
		
				Keyword kw = new Keyword();
				kw.setText(word);
				kw.setMatchType(KeywordMatchType.EXACT);
				wordList.add(kw);
				
				kw = new Keyword();
				kw.setText(word);
				kw.setMatchType(KeywordMatchType.PHRASE);
				wordList.add(kw);
				
				kw = new Keyword();
				kw.setText(word);
				kw.setMatchType(KeywordMatchType.BROAD);
				wordList.add(kw);
			}
		}
		else
		{
			for (String word : keywords)
			{

				Keyword kw = new Keyword();
				kw.setText(word);
				kw.setMatchType(matchType);
				wordList.add(kw);

			}
		}
		

		// Create related to keyword search parameter.
		RelatedToKeywordSearchParameter relatedToKeywordSearchParameter = new RelatedToKeywordSearchParameter();
		relatedToKeywordSearchParameter.setKeywords(wordList.toArray(new Keyword[wordList.size()]));
		//relatedToKeywordSearchParameter.setKeywords(new Keyword[] {keywrd});
		searchParamList.add(relatedToKeywordSearchParameter);




		// Location search parameter		
		Location loc = new Location();
		loc.setId(new Long(2840)); // United States
		LocationSearchParameter locationSearchParameter = new LocationSearchParameter();
		locationSearchParameter.setLocations(new Location[]
		{ loc });
		searchParamList.add(locationSearchParameter);


		// set the selector
		selector.setSearchParameters(searchParamList.toArray(new SearchParameter[searchParamList.size()]));

		try
		{
			// Get related placements.
			page = targetingIdeaService.get(selector);
			//page = targetingIdeaService.getBulkKeywordIdeas(selector);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}


		//System.out.println(page.getTotalNumEntries());

		if (page != null && page.getEntries() != null)
		{

			for (TargetingIdea targetingIdea : page.getEntries())
			{
				Map<AttributeType, Attribute> data = MapUtils.toMap(targetingIdea.getData());
				Keyword kw = (Keyword) ((CriterionAttribute) data.get(AttributeType.CRITERION)).getValue();
				Long averageMonthlySearches = ((LongAttribute) data.get(AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES)).getValue();
				Double comp = ((DoubleAttribute) data.get(AttributeType.COMPETITION)).getValue();
				// logger.info(kw.getText()+","+
				// kw.getMatchType().getValue()+","+ averageMonthlySearches+","+
				// comp);
				//System.out.println(kw.getText()+": "+averageMonthlySearches);
				//System.out.println(kw.getText());
				KeywordMatchingType kwMatchType = null;
				if (kw.getMatchType() == KeywordMatchType.EXACT)
				{
					kwMatchType = KeywordMatchingType.EXACT;
				}
				else if (kw.getMatchType() == KeywordMatchType.PHRASE)
				{
					kwMatchType = KeywordMatchingType.PHRASE;
				}
				else if (kw.getMatchType() == KeywordMatchType.BROAD)
				{
					kwMatchType = KeywordMatchingType.BROAD;
				}
				returnData.add(new KeywordToolStats(kw.getText(),kwMatchType,averageMonthlySearches,comp));
			}

		}


		logger.info("Total number of words received from Google: "+returnData.size());
		return returnData;

	}
	
	public ArrayList<KeywordToolStats> getGoogleKeywordIdeas(String[] keywords, int numberResults) throws Exception
	{
	
		

		if (numberResults > 1000)
		{
			throw new Exception("numberResults must be <= 1000. Google doesn't return more than that!! The feature"
					+ " is given so that you may use a smaller number to save API cost in case a small number of keywords needed.");
		}
		
		ArrayList<KeywordToolStats> returnData = new ArrayList<KeywordToolStats>();
		TargetingIdeaPage page;
		TargetingIdeaServiceInterface targetingIdeaService;

		HashSet<Keyword> stopWordSet = new HashSet<Keyword>();
		
		int iterations=1;
		int numResIter = numberResults;
		if(numberResults>800) {
			iterations=2;
			numResIter=numberResults-800;
		}

		/*
		for (String word : keywords)
		{
			Keyword kw = new Keyword();
			kw.setText(word);
			kw.setMatchType(KeywordMatchType.EXACT);
			stopWordSet.add(kw);
		}

		if (exclude_keywords != null)
		{
			if (exclude_keywords.length + keywords.length > 200)
			{
				throw new Exception("Total number of keywords and exclude keywords must be less than equal to 200. It's a Google policy");
			}
			for (String word : exclude_keywords)
			{
				Keyword kw = new Keyword();
				kw.setText(word);
				kw.setMatchType(KeywordMatchType.EXACT);
				stopWordSet.add(kw);
			}
		}
		*/

		try
		{
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// AdWordsUser user = new AdWordsUser("adwords@semplest.com","ic0system",accountID,"Icosystem","2H8l6aUm6K_Q44vDvxs3Og");
			// Get the TargetingIdeaService
			targetingIdeaService = user.getService(AdWordsService.V201109.TARGETING_IDEA_SERVICE);
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}


		//for (String word : keywords){


		// Create selector.
		TargetingIdeaSelector selector = new TargetingIdeaSelector();
		selector.setRequestType(RequestType.IDEAS);
		selector.setIdeaType(IdeaType.KEYWORD);
		selector.setRequestedAttributeTypes(new AttributeType[]
		{ AttributeType.CRITERION, AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES, AttributeType.COMPETITION }); // AttributeType.APPROX_CONTENT_IMPRESSIONS_PER_DAY,//
																													//});
		
		// Set selector paging (required for targeting idea service).
		Paging paging = new Paging();
		paging.setStartIndex(0);
		paging.setNumberResults(numResIter);
		selector.setPaging(paging);

		ArrayList<SearchParameter> searchParamList = new ArrayList<SearchParameter>();

		// Keyword

		ArrayList<Keyword> kwList = new ArrayList<Keyword>();
		for (String s : keywords)
		{
			Keyword keywrd = new Keyword();
			keywrd.setText(s);
			keywrd.setMatchType(KeywordMatchType.EXACT);
			kwList.add(keywrd);
		} 
		/*
		 * Keyword keywrd = new Keyword(); keywrd.setText(word);
		 * keywrd.setMatchType(KeywordMatchType.EXACT);
		 */
		// Create related to keyword search parameter.
		RelatedToKeywordSearchParameter relatedToKeywordSearchParameter = new RelatedToKeywordSearchParameter();
		relatedToKeywordSearchParameter.setKeywords(kwList.toArray(new Keyword[kwList.size()]));
		//relatedToKeywordSearchParameter.setKeywords(new Keyword[] {keywrd});
		searchParamList.add(relatedToKeywordSearchParameter);


		/*
		
		// Exclude search parameter
		ExcludedKeywordSearchParameter excludeKeywordSearchParameter = new ExcludedKeywordSearchParameter();
		excludeKeywordSearchParameter.setKeywords(stopWordSet.toArray(new Keyword[stopWordSet.size()]));
		searchParamList.add(excludeKeywordSearchParameter);
		*/

		
		// Average monthly search  
		AverageTargetedMonthlySearchesSearchParameter avgMonthlySearchParameter = new AverageTargetedMonthlySearchesSearchParameter();
		LongComparisonOperation range = new LongComparisonOperation();
		range.setMinimum(100L); // may dynamically change in future -- TBD
		range.setMaximum(100000000000000000L);
		avgMonthlySearchParameter.setOperation(range);
		searchParamList.add(avgMonthlySearchParameter);
		
		
		/*

		// Create related to URL search parameter.
		RelatedToUrlSearchParameter relatedToUrlSearchParameter = new RelatedToUrlSearchParameter();
		relatedToUrlSearchParameter.setUrls(new String[] { url });
		//relatedToUrlSearchParameter.setIncludeSubUrls(true);
		searchParamList.add(relatedToUrlSearchParameter);
		 */

		// matchType parameter
		KeywordMatchTypeSearchParameter matchTypeParameter = new KeywordMatchTypeSearchParameter();
		matchTypeParameter.setKeywordMatchTypes(new KeywordMatchType[]
		{ KeywordMatchType.EXACT });
		searchParamList.add(matchTypeParameter);


		/*
		// Category ID parameter
		CategoryProductsAndServicesSearchParameter categoryProductServiceSearchParameter = new CategoryProductsAndServicesSearchParameter ();
		if (categoryId != null)
		{
			categoryProductServiceSearchParameter.setCategoryId(categoryId);
			searchParamList.add(categoryProductServiceSearchParameter);
		}
		*/


		// Location search parameter		
		Location loc = new Location();
		loc.setId(new Long(2840)); // United States
		LocationSearchParameter locationSearchParameter = new LocationSearchParameter();
		locationSearchParameter.setLocations(new Location[]
		{ loc });
		searchParamList.add(locationSearchParameter);


		// set the selector
		selector.setSearchParameters(searchParamList.toArray(new SearchParameter[searchParamList.size()]));

		
		for (int iter = 1; iter <= iterations; iter++){ 
			try {
				// Get related placements.
				page = targetingIdeaService.get(selector);
			} catch (ApiException e) {
				throw new Exception(e.dumpToString());
			} catch (RemoteException e) {
				throw new Exception(e);
			}


			if (page != null && page.getEntries() != null) {

				for (TargetingIdea targetingIdea : page.getEntries()) {
					
					Map<AttributeType, Attribute> data = MapUtils.toMap(targetingIdea.getData());
					Keyword kw = (Keyword) ((CriterionAttribute) data.get(AttributeType.CRITERION)).getValue();
					Long averageMonthlySearches = ((LongAttribute) data.get(AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES)).getValue();
					Double comp = ((DoubleAttribute) data.get(AttributeType.COMPETITION)).getValue();
					// logger.info(kw.getText()+" "+
					// kw.getMatchType().getValue()+" "+ averageMonthlySearches+" "+
					// comp);
					//logger.info(kw.getText()+": "+averageMonthlySearches);
					//logger.info(kw.getText());

					KeywordMatchingType kwMatchType = null;
					if (kw.getMatchType() == KeywordMatchType.EXACT)
					{
						kwMatchType = KeywordMatchingType.EXACT;
					}
					else if (kw.getMatchType() == KeywordMatchType.PHRASE)
					{
						kwMatchType = KeywordMatchingType.PHRASE;
					}
					else if (kw.getMatchType() == KeywordMatchType.BROAD)
					{
						kwMatchType = KeywordMatchingType.BROAD;
					}

					if(kw.getText().split("\\s+").length>1){
					returnData.add(new KeywordToolStats(kw.getText(),kwMatchType,averageMonthlySearches,comp));
					}
					if (stopWordSet.contains(kw))
					{
						logger.info("Google is fooling us... returned a keyword from the stop list: "+kw.getText());
					}
					else
					{
						if (stopWordSet.size() < 200)
						{
							stopWordSet.add(kw);
						}
					}
				}

			}


			// Exclude search parameter
			ExcludedKeywordSearchParameter excludeKeywordSearchParameter = new ExcludedKeywordSearchParameter();
			excludeKeywordSearchParameter.setKeywords(stopWordSet.toArray(new Keyword[stopWordSet.size()]));
			searchParamList.add(excludeKeywordSearchParameter);

			selector.setSearchParameters(searchParamList.toArray(new SearchParameter[searchParamList.size()]));
			numResIter=800;
			// Set selector paging (required for targeting idea service).
			paging = new Paging();
			paging.setStartIndex(0);
			paging.setNumberResults(numResIter);
			selector.setPaging(paging);
		
		}
		

		//logger.info("Keyword "+word+" is done.");
		//} //for (String word : keywords)


		logger.info("Total number of words received from Google: "+returnData.size());
		
		
		// sort according to volume before returning
		Collections.sort(returnData, new Comparator<KeywordToolStats>(){
			  public int compare(KeywordToolStats s1, KeywordToolStats s2) {
			    return s2.getAverageMonthlySearches().compareTo(s1.getAverageMonthlySearches());
			  }
			});
		
		logger.info("Words suggested by Google keyword tool are:");
		for(KeywordToolStats w : returnData){
			logger.info(w.getKeyword()+","+w.getMatchType()+","+w.getCompetition()+","+w.getAverageMonthlySearches());
		}
		
		return returnData;

	}

	public String setBidForKeyWord(String json) throws Exception
	{
		logger.debug("call setBidForKeyWord" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		Long keywordID = Long.parseLong(data.get("keywordID"));
		Long microBidAmount = Long.parseLong(data.get("microBidAmount"));
		KeywordDataObject res = setBidForKeyWord(data.get("accountID"), keywordID, adGroupID, microBidAmount);
		// convert result to Json String
		return gson.toJson(res);
	}
	
	@Override
	public List<KeywordDataObject> setBidForKeyWords(final String accountID, final List<GoogleSetBidForKeywordRequest> requests) throws Exception
	{
		logger.info("Will try to Set Bids for " + requests.size() + " Keywords for AccountID [" + accountID + "]");
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
		final List<AdGroupCriterionOperation> operations = new ArrayList<AdGroupCriterionOperation>();
		for (final GoogleSetBidForKeywordRequest request : requests)
		{
			final Long keywordID = request.getKeywordID();
			final Long adGroupID = request.getAdGroupID();
			final Long microBidAmount = request.getMicroBidAmount();
			final AdGroupCriterionOperation operation = getAdGroupCriterionOperationToSetBid(keywordID, adGroupID, microBidAmount);
			operations.add(operation);
		}
		final int batchSize = 500;
		final List<List<AdGroupCriterionOperation>> operationBatches = SemplestUtils.getBatches(operations, batchSize);
		logger.info("Out of " + operations.size() + " Operations generated " + operationBatches.size() + " batches of " + batchSize);
		final List<KeywordDataObject> responses = new ArrayList<KeywordDataObject>();
		int counter = 0;
		for (final List<AdGroupCriterionOperation> operationBatch : operationBatches)
		{
			logger.info("Processing Batch #" + ++counter + " of " + operationBatch.size() + " Operations");
			final AdGroupCriterionOperation[] operationArray = operationBatch.toArray(new AdGroupCriterionOperation[operationBatch.size()]);
			final AdGroupCriterionReturnValue result = adGroupCriterionService.mutate(operationArray);
			if (result != null && result.getValue() != null)
			{
				final AdGroupCriterion[] adGroupCriterions = result.getValue();
				for (final AdGroupCriterion adGroupCriterion : adGroupCriterions)
				{
					if (adGroupCriterion instanceof BiddableAdGroupCriterion)
					{
						final BiddableAdGroupCriterion res = (BiddableAdGroupCriterion)adGroupCriterion;
						final Criterion criterion = res.getCriterion();
						if (criterion instanceof Keyword)
						{
							final Keyword keyword = (Keyword)criterion;
							final KeywordDataObject bidResponse = new KeywordDataObject();
							if (res.getQualityInfo() != null)
							{
								final QualityInfo qualityInfo = res.getQualityInfo();
								bidResponse.setQualityScore(qualityInfo.getQualityScore());
							}
							if (res.getFirstPageCpc() != null)
							{
								final Bid bid = res.getFirstPageCpc();
								final Money bidAmount = bid.getAmount();
								bidResponse.setFirstPageCpc(bidAmount.getMicroAmount());
							}
							bidResponse.setBidID(keyword.getId());
							final ApprovalStatus approvalStatus = res.getApprovalStatus();
							bidResponse.setApprovalStatus(approvalStatus.getValue());
							final ManualCPCAdGroupCriterionBids bids = (ManualCPCAdGroupCriterionBids)res.getBids();
							final Bid bid = bids.getMaxCpc();
							final Money bidAmount = bid.getAmount();
							bidResponse.setMicroBidAmount(bidAmount.getMicroAmount());
							bidResponse.setKeyword(keyword.getText());
							bidResponse.setMatchType(keyword.getMatchType().getValue());
							bidResponse.setNegative(false);
							responses.add(bidResponse);
						}						
					}					
				}
			}
			try
			{
				Thread.sleep(3000);
			}
			catch(InterruptedException e)
			{
				logger.warn("Got interrupted while trying to sleep, but will just log and ignore");
			}
		}
		return responses;
	}
	
	public static AdGroupCriterionOperation getAdGroupCriterionOperationToSetBid(Long keywordID, Long adGroupID, Long microBidAmount)
	{
		final Criterion criterion = new Criterion();
		criterion.setId(keywordID);
		final BiddableAdGroupCriterion biddableAdGroupCriterion = new BiddableAdGroupCriterion();
		biddableAdGroupCriterion.setAdGroupId(adGroupID);
		biddableAdGroupCriterion.setCriterion(criterion);
		final ManualCPCAdGroupCriterionBids bids = new ManualCPCAdGroupCriterionBids();
		final Money money = new Money();
		money.setMicroAmount(microBidAmount);
		bids.setMaxCpc(new Bid(money));
		biddableAdGroupCriterion.setBids(bids);
		final AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
		operation.setOperand(biddableAdGroupCriterion);
		operation.setOperator(Operator.SET);
		return operation;
	}

	@Override
	public KeywordDataObject setBidForKeyWord(String accountID, Long keywordID, Long adGroupID, Long microBidAmount) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the AdGroupCriterionService.
			AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
			// Create ad group criterion with updated bid.
			Criterion criterion = new Criterion();
			criterion.setId(keywordID);
			BiddableAdGroupCriterion biddableAdGroupCriterion = new BiddableAdGroupCriterion();
			biddableAdGroupCriterion.setAdGroupId(adGroupID);
			biddableAdGroupCriterion.setCriterion(criterion);
			// Create bids.
			ManualCPCAdGroupCriterionBids bids = new ManualCPCAdGroupCriterionBids();
			Money money = new Money();
			money.setMicroAmount(microBidAmount);
			bids.setMaxCpc(new Bid(money));

			biddableAdGroupCriterion.setBids(bids);
			// Create operations.
			AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
			operation.setOperand(biddableAdGroupCriterion);
			operation.setOperator(Operator.SET);
			AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[]
			{ operation };
			// Update ad group criteria.
			AdGroupCriterionReturnValue result = adGroupCriterionService.mutate(operations);
			// Display ad group criteria.
			if (result != null && result.getValue() != null && (result.getValue(0) instanceof BiddableAdGroupCriterion))
			{
				BiddableAdGroupCriterion res = (BiddableAdGroupCriterion) result.getValue(0);
				Keyword keyword = ((Keyword) res.getCriterion());
				KeywordDataObject bidRes = new KeywordDataObject();
				if (res.getQualityInfo() != null)
				{
					bidRes.setQualityScore(res.getQualityInfo().getQualityScore());
				}
				if (res.getFirstPageCpc() != null)
				{
					bidRes.setFirstPageCpc(res.getFirstPageCpc().getAmount().getMicroAmount());
				}
				bidRes.setBidID(res.getCriterion().getId());
				bidRes.setApprovalStatus(res.getApprovalStatus().getValue());
				bidRes.setMicroBidAmount(((ManualCPCAdGroupCriterionBids) res.getBids()).getMaxCpc().getAmount().getMicroAmount());
				bidRes.setKeyword(keyword.getText());
				bidRes.setMatchType(keyword.getMatchType().getValue());
				bidRes.setNegative(false);
				return bidRes;
			}
			else
			{
				return new KeywordDataObject();
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}
	
	public String addNegativeKeyWordToAdGroup(String json) throws Exception
	{
		logger.debug("call addKeyWordToAdGroup" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		KeywordDataObject res = addNegativeKeyWordToAdGroup(data.get("accountID"), adGroupID, data.get("keyword"), KeywordMatchType.fromString(data.get("matchType")));
		return gson.toJson(res);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	@Override
	public KeywordDataObject addNegativeKeyWordToAdGroup(String accountID, Long campaignID, String keyword, KeywordMatchType matchType)
			throws Exception
	{
		logger.info("Will try to Add Negative Keyword for AccountID [" + accountID + "], CampaignID [" + campaignID + "], Keyword [" + keyword + "], KeywordMatchType [" + matchType + "]");
		try
		{
			// Log SOAP XML request and response. AdWordsServiceLogger.log();
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final Keyword keywrd = new Keyword();        
			keywrd.setText(keyword);        
			keywrd.setMatchType(matchType);          
			final NegativeCampaignCriterion negativeCampaignCriterion = new NegativeCampaignCriterion();        
			negativeCampaignCriterion.setCampaignId(campaignID);        
			negativeCampaignCriterion.setCriterion(keywrd);                 
			final CampaignCriterionOperation operation = new CampaignCriterionOperation();        
			operation.setOperand(negativeCampaignCriterion);        
			operation.setOperator(Operator.ADD);
			final CampaignCriterionOperation[] operations = {operation};
			final CampaignCriterionServiceInterface campaignCriterionService = user.getService(AdWordsService.V201109.CAMPAIGN_CRITERION_SERVICE);
			final CampaignCriterionRetriableGoogleOperation retriableOperation = new CampaignCriterionRetriableGoogleOperation(campaignCriterionService, operations, 10);			
			final CampaignCriterionReturnValue results = retriableOperation.performOperation();			
			final KeywordDataObject bidRes = new KeywordDataObject();
			if (results != null && results.getValue() != null && (results.getValue(0) instanceof CampaignCriterion))
			{
				CampaignCriterion res = (CampaignCriterion )results.getValue(0);
				Keyword akeyword = ((Keyword)res.getCriterion());				
				bidRes.setBidID(res.getCriterion().getId());
				bidRes.setMatchType(akeyword.getMatchType().getValue());
				bidRes.setKeyword(akeyword.getText());
				bidRes.setNegative(true);
				return bidRes;
			}
			return bidRes;			
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem Adding Negative Keyword for AccountID [" + accountID + "], CampaignID [" + campaignID + "], Keyword [" + keyword + "], KeywordMatchType [" + matchType + "]", e);
		}		
		catch (RemoteException e)
		{
			throw new Exception("Problem Adding Negative Keyword for AccountID [" + accountID + "], CampaignID [" + campaignID + "], Keyword [" + keyword + "], KeywordMatchType [" + matchType + "]", e);
		}
		catch(Exception e)
		{
			throw new Exception("Problem Adding Negative Keyword for AccountID [" + accountID + "], CampaignID [" + campaignID + "], Keyword [" + keyword + "], KeywordMatchType [" + matchType + "]", e);
		}
	}

	public String CreateOneCampaignForAccount(String json) throws Exception
	{
		logger.debug("call CreateOneAccountService(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Campaign campaign = CreateOneCampaignForAccount(data.get("accountID"), data.get("campaignName"), CampaignStatus.fromString(data.get("campaignStatus")), BudgetBudgetPeriod.fromString(data.get("period")), new Long(data.get("microBudgetAmount")));
		return gson.toJson(campaign);
	}

	@Override
	public Campaign CreateOneCampaignForAccount(String accountID, String campaignName, CampaignStatus campaignStatus, BudgetBudgetPeriod period,
			Long microBudgetAmount) throws Exception
	{
		logger.info("Will try to Create Campaign for AccountID [" + accountID + "], CampaignName [" + campaignName + "], CampaignStatus [" + campaignStatus + "], BudgetPeriod [" + period + "], MicroBudgetAmount [" + microBudgetAmount + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);
			final Campaign campaign = new Campaign();
			final String campaignNameLegalLength = SemplestUtils.getLegalUserName(campaignName);			
			campaign.setName(campaignNameLegalLength);
			if (campaignStatus == null)
			{
				campaignStatus = CampaignStatus.PAUSED;
			}
			campaign.setStatus(campaignStatus);
			campaign.setBiddingStrategy(new ManualCPC());
			final Budget budget = new Budget();
			if (period == null)
			{
				period = BudgetBudgetPeriod.MONTHLY;
			}
			/*
			 * Default network is Google Search ONLY
			 */
			final NetworkSetting network = new NetworkSetting();
			network.setTargetGoogleSearch(true);
			network.setTargetPartnerSearchNetwork(false);
			network.setTargetContentNetwork(false);
			network.setTargetSearchNetwork(false);
			campaign.setNetworkSetting(network);
			budget.setPeriod(period);
			final Money money = new Money();
			money.setMicroAmount(microBudgetAmount);
			budget.setAmount(money);
			budget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);
			campaign.setBudget(budget);
			final CampaignOperation operation = new CampaignOperation();
			operation.setOperand(campaign);
			operation.setOperator(Operator.ADD);
			final CampaignOperation[] operations = new CampaignOperation[]{ operation };
			final CampaignRetriableGoogleOperation retriableOperation = new CampaignRetriableGoogleOperation(campaignService, operations, SemplestUtils.DEFAULT_RETRY_COUNT); 
			final CampaignReturnValue result = retriableOperation.performOperation();
			if (result != null && result.getValue() != null)
			{
				return result.getValue()[0];
			}
			else
			{
				System.out.println("No campaigns were added.");
				return null;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem Creating Campaign for AccountID [" + accountID + "], CampaignName [" + campaignName + "], CampaignStatus [" + campaignStatus + "], BudgetPeriod [" + period + "], MicroBudgetAmount [" + microBudgetAmount + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem Creating Campaign for AccountID [" + accountID + "], CampaignName [" + campaignName + "], CampaignStatus [" + campaignStatus + "], BudgetPeriod [" + period + "], MicroBudgetAmount [" + microBudgetAmount + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem Creating Campaign for AccountID [" + accountID + "], CampaignName [" + campaignName + "], CampaignStatus [" + campaignStatus + "], BudgetPeriod [" + period + "], MicroBudgetAmount [" + microBudgetAmount + "]", e);
		}
	}

	public String getAdGroupsByCampaignId(String json) throws Exception
	{
		logger.debug("call getCampaignsByAccountId" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long campaignID = Long.parseLong(data.get("campaignID"));
		GoogleAdGroupObject[] res = getAdGroupsByCampaignId(data.get("accountID"), campaignID, Boolean.valueOf(data.get("includeDeleted")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public GoogleAdGroupObject[] getAdGroupsByCampaignId(String accountID, Long campaignID, Boolean includeDeleted) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the AdGroupService.
			AdGroupServiceInterface adGroupService = user.getService(AdWordsService.V201109.ADGROUP_SERVICE);
			// Create selector.
			Selector selector = new Selector();
			selector.setFields(new String[]
			{ "Id", "Name" });
			selector.setOrdering(new OrderBy[]
			{ new OrderBy("Name", SortOrder.ASCENDING) });
			// Create predicates.
			Predicate campaignIdPredicate = new Predicate("CampaignId", PredicateOperator.IN, new String[]
			{ campaignID.toString() });
			selector.setPredicates(new Predicate[]
			{ campaignIdPredicate });
			// Get all ad groups.
			AdGroupPage page = adGroupService.get(selector);
			AdGroup[] pages = page.getEntries();
			GoogleAdGroupObject[] res =
			{ new GoogleAdGroupObject() };
			if (pages.length > 0)
			{
				res = new GoogleAdGroupObject[pages.length];
				for (int i = 0; i < pages.length; i++)
				{
					GoogleAdGroupObject obj = new GoogleAdGroupObject();
					obj.setAdGroupID(pages[i].getId());
					obj.setAdGroupName(pages[i].getName());
					obj.setCampaignId(pages[i].getCampaignId());
					obj.setCampaignName(pages[i].getCampaignName());
					/*
					 * adGroupStats s = obj.new adGroupStats(); if
					 * (pages[i].getStats().getAverageCpc() != null) {
					 * s.setAverageCpc
					 * (pages[i].getStats().getAverageCpc().getMicroAmount()); }
					 * if (pages[i].getStats().getAverageCpm() != null) {
					 * s.setAverageCpm
					 * (pages[i].getStats().getAverageCpm().getMicroAmount()); }
					 * s.setAveragePosition
					 * (pages[i].getStats().getAveragePosition());
					 * s.setAvgCallDurationSecs
					 * (pages[i].getStats().getAvgCallDurationSecs());
					 * s.setClicks(pages[i].getStats().getClicks());
					 * s.setConversionRate
					 * (pages[i].getStats().getConversionRate()); if
					 * (pages[i].getStats().getCost() != null) {
					 * s.setCost(pages[
					 * i].getStats().getCost().getMicroAmount()); } if
					 * (pages[i].getStats().getCostPerConversion() != null) {
					 * s.setCostPerConversion
					 * (pages[i].getStats().getCostPerConversion
					 * ().getMicroAmount()); }
					 * s.setCtr(pages[i].getStats().getCtr()); obj.setStats(s);
					 */
					obj.setStatus(pages[i].getStatus());
					res[i] = obj;
				}
			}

			return res;
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	public String deleteCampaign(String json) throws Exception
	{
		logger.debug("call deleteCampaign" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long campaignID = Long.parseLong(data.get("campaignID"));
		Boolean res = deleteCampaign(data.get("accountID"), campaignID);
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public Boolean deleteCampaign(String accountID, Long campaignID) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the CampaignService.
			CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);

			// Create campaign with DELETED status.
			Campaign campaign = new Campaign();
			campaign.setId(campaignID.longValue());
			campaign.setStatus(CampaignStatus.DELETED);
			// Create operations.
			CampaignOperation operation = new CampaignOperation();
			operation.setOperand(campaign);
			operation.setOperator(Operator.SET);
			CampaignOperation[] operations = new CampaignOperation[]
			{ operation };
			// Delete campaign.
			CampaignReturnValue result = campaignService.mutate(operations);
			// Display campaigns.
			if (result != null && result.getValue() != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}
	
	@Override
	public Boolean ChangeCampaignStartDate(String accountID, Long campaignID, java.util.Date newStartDate) throws Exception
	{
		logger.info("Will try to change the Campaign StartDate to [" + newStartDate + "] for AccountID [" + accountID + "] and CampaignID [" + campaignID + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);			
			final Campaign campaign = new Campaign();
			campaign.setId(campaignID);
			final String startDateString = SemplestUtils.DATE_FORMAT_YYYYMMDD.format(newStartDate);
			campaign.setStartDate(startDateString);
			final CampaignOperation operation = new CampaignOperation();
			operation.setOperand(campaign);
			operation.setOperator(Operator.SET);
			final CampaignOperation[] operations = new CampaignOperation[]{operation};			
			final CampaignRetriableGoogleOperation retriableOperation = new CampaignRetriableGoogleOperation(campaignService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);			
			final CampaignReturnValue ret = retriableOperation.performOperation();
			if (ret != null && ret.getValue() != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			final String errMsg = "Problem changing the StartDate of Google campaign [" + campaignID + "] to StartDate [" + newStartDate + "] for Google Account ID [" + accountID + "]";
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem changing the StartDate of Google campaign [" + campaignID + "] to StartDate [" + newStartDate + "] for Google Account ID [" + accountID + "]: " + e.dumpToString();
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem changing the StartDate of Google campaign [" + campaignID + "] to StartDate [" + newStartDate + "] for Google Account ID [" + accountID + "]";
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
	}

	public String changeCampaignStatus(String json) throws Exception
	{
		logger.debug("call changeCampaignStatus" + json);
		final Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		final String campaignIdsString = data.get("campaignIds");
		final List<Long> campaignIds = gson.fromJson(campaignIdsString, SemplestUtils.TYPE_LIST_OF_LONGS);
		final String accountID = data.get("accountID");
		final CampaignStatus status = CampaignStatus.fromString(data.get("status"));
		final Boolean res = changeCampaignsStatus(accountID, campaignIds, status);
		return gson.toJson(res);
	}
	
	public List<CampaignOperation> getCampaignOperations(List<Long> campaignIds, CampaignStatus status)
	{
		final List<CampaignOperation> operations = new ArrayList<CampaignOperation>();
		for (final Long campaignId : campaignIds)
		{
			final Campaign campaign = new Campaign();
			campaign.setId(campaignId);
			campaign.setStatus(status);
			final CampaignOperation operation = new CampaignOperation();
			operation.setOperand(campaign);
			operation.setOperator(Operator.SET);
			operations.add(operation);
		}
		return operations;
	}

	@Override
	public Boolean changeCampaignsStatus(String accountID, List<Long> campaignIds, CampaignStatus status) throws Exception
	{
		logger.info("Will try to change the CampaignStatus to [" + status + "] for AccountID [" + accountID + "] and CampaignIds [" + campaignIds + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);			
			final List<CampaignOperation> campaignOperations = getCampaignOperations(campaignIds, status);
			final CampaignOperation[] operations = campaignOperations.toArray(new CampaignOperation[campaignOperations.size()]);			
			final CampaignRetriableGoogleOperation retriableOperation = new CampaignRetriableGoogleOperation(campaignService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);			
			final CampaignReturnValue ret = retriableOperation.performOperation();
			if (ret != null && ret.getValue() != null)
			{				
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			final String errMsg = "Problem changing the status of Google campaign [" + campaignIds + "] to Status [" + status + "] for Google Account ID [" + accountID + "]";
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (ApiException e)
		{
			final String errMsg = "Problem changing the status of Google campaign [" + campaignIds + "] to Status [" + status + "] for Google Account ID [" + accountID + "]: " + e.dumpToString();
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
		catch (RemoteException e)
		{
			final String errMsg = "Problem changing the status of Google campaign [" + campaignIds + "] to Status [" + status + "] for Google Account ID [" + accountID + "]";
			logger.info(errMsg, e);
			throw new Exception(errMsg, e);
		}
	}
	
	public static CampaignCriterionOperation getNegativeKeywordUpdateOperation(Long campaignID, String keywordString, KeywordMatchType matchType, Long criterionId)
	{
		final Keyword keyword = new Keyword();
		keyword.setId(criterionId);
		keyword.setText(keywordString);        
		keyword.setMatchType(matchType);
		final NegativeCampaignCriterion negativeCampaignCriterion = new NegativeCampaignCriterion();
		negativeCampaignCriterion.setCampaignId(campaignID);        
		negativeCampaignCriterion.setCriterion(keyword);          		
		final CampaignCriterionOperation operation = new CampaignCriterionOperation();        
		operation.setOperand(negativeCampaignCriterion);        
		operation.setOperator(Operator.SET);
		return operation;
	}
	
	public static CampaignCriterionOperation getNegativeKeywordRemoveOperation(Long campaignID, Long criterionId)
	{
		final Keyword keyword = new Keyword();        
		keyword.setId(criterionId);                       
		final NegativeCampaignCriterion negativeCampaignCriterion = new NegativeCampaignCriterion();        
		negativeCampaignCriterion.setCampaignId(campaignID);
		negativeCampaignCriterion.setCriterion(keyword);
		final CampaignCriterionOperation operation = new CampaignCriterionOperation();        
		operation.setOperand(negativeCampaignCriterion);        
		operation.setOperator(Operator.REMOVE);
		return operation;
	}
	
	public static CampaignCriterionOperation getNegativeKeywordAddOperation(Long campaignID, String keywordString, KeywordMatchType matchType)
	{
		final Keyword keyword = new Keyword();        
		keyword.setText(keywordString);        
		keyword.setMatchType(matchType);
		final NegativeCampaignCriterion negativeCampaignCriterion = new NegativeCampaignCriterion();        
		negativeCampaignCriterion.setCampaignId(campaignID);        
		negativeCampaignCriterion.setCriterion(keyword);          		
		final CampaignCriterionOperation operation = new CampaignCriterionOperation();        
		operation.setOperand(negativeCampaignCriterion);        
		operation.setOperator(Operator.ADD);
		return operation;
	}
	
	public static AdGroupCriterionOperation getRegularKeywordRemoveOperation(Long adGroupID, Long criterionId)
	{
		final Keyword keyword = new Keyword();
		keyword.setId(criterionId);
		final BiddableAdGroupCriterion keywordBiddableAdGroupCriterion = new BiddableAdGroupCriterion();
		keywordBiddableAdGroupCriterion.setCriterion(keyword);
		final ManualCPCAdGroupCriterionBids bid = new ManualCPCAdGroupCriterionBids();
		keywordBiddableAdGroupCriterion.setBids(bid);
		keywordBiddableAdGroupCriterion.setAdGroupId(adGroupID);
		final AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
		operation.setOperand(keywordBiddableAdGroupCriterion);
		operation.setOperator(Operator.REMOVE);
		return operation;
	}
	
	public static AdGroupCriterionOperation getRegularKeywordUpdateOperation(Long adGroupID, String keywordString, KeywordMatchType matchType, Long microBidAmount, Long criterionId)
	{
		final Keyword keyword = new Keyword();
		keyword.setText(keywordString);
		keyword.setId(criterionId);
		keyword.setMatchType(matchType);
		final BiddableAdGroupCriterion keywordBiddableAdGroupCriterion = new BiddableAdGroupCriterion();
		keywordBiddableAdGroupCriterion.setAdGroupId(adGroupID);
		keywordBiddableAdGroupCriterion.setCriterion(keyword);
		final ManualCPCAdGroupCriterionBids bid = new ManualCPCAdGroupCriterionBids();
		final Money money = new Money();
		money.setMicroAmount(microBidAmount);
		final Bid moneyBid = new Bid(money);
		bid.setMaxCpc(moneyBid);
		keywordBiddableAdGroupCriterion.setBids(bid);
		final AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
		operation.setOperand(keywordBiddableAdGroupCriterion);
		operation.setOperator(Operator.ADD);
		return operation;
	}
	
	public static AdGroupCriterionOperation getRegularKeywordAddOperation(Long adGroupID, String keywordString, KeywordMatchType matchType, Long microBidAmount)
	{
		final Keyword keyword = new Keyword();
		keyword.setText(keywordString);
		keyword.setMatchType(matchType);
		final BiddableAdGroupCriterion keywordBiddableAdGroupCriterion = new BiddableAdGroupCriterion();
		keywordBiddableAdGroupCriterion.setAdGroupId(adGroupID);
		keywordBiddableAdGroupCriterion.setCriterion(keyword);
		final ManualCPCAdGroupCriterionBids bid = new ManualCPCAdGroupCriterionBids();
		final Money money = new Money();
		money.setMicroAmount(microBidAmount);
		final Bid moneyBid = new Bid(money);
		bid.setMaxCpc(moneyBid);
		keywordBiddableAdGroupCriterion.setBids(bid);
		final AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
		operation.setOperand(keywordBiddableAdGroupCriterion);
		operation.setOperator(Operator.ADD);
		return operation;
	}
	
	public void populateKeywordOperations(Long campaignID, Long adGroupID, Map<KeywordProbabilityObject, Boolean> keywordProbabilityToRemoveOppositeMap, KeywordMatchType matchType, Long microBidAmount, 
			List<AdGroupCriterionOperation> regularKeywordOperations, List<CampaignCriterionOperation> negativeKeywordOperations, 
			Map<String, Long> existingRegularKeywordsToCriterionIdMap, Map<String, Long> existingNegativeKeywordsToCriterionIdMap)
	{
		final Set<Entry<KeywordProbabilityObject, Boolean>> entrySet = keywordProbabilityToRemoveOppositeMap.entrySet();
		for (final Entry<KeywordProbabilityObject, Boolean> entry : entrySet)
		{
			final KeywordProbabilityObject keywordProbability = entry.getKey();
			final Boolean isNegativeKeyword = keywordProbability.getIsNegative();
			final String keywordString = keywordProbability.getKeyword();
			final Boolean removeOpposite = entry.getValue();
			if (isNegativeKeyword)
			{				
				final CampaignCriterionOperation negativeKeywordAddUpdateOperation;
				if (existingNegativeKeywordsToCriterionIdMap.containsKey(keywordString))
				{
					final Long criterionId = existingNegativeKeywordsToCriterionIdMap.get(keywordString);
					if (criterionId == null)
					{
						throw new RuntimeException("CriterionID is null for Keyword [" + keywordString + "]");						
					}
					negativeKeywordAddUpdateOperation = getNegativeKeywordUpdateOperation(campaignID, keywordString, matchType, criterionId);
				}
				else
				{
					negativeKeywordAddUpdateOperation = getNegativeKeywordAddOperation(campaignID, keywordString, matchType);
				}				
				negativeKeywordOperations.add(negativeKeywordAddUpdateOperation);
				if (removeOpposite)
				{
					final Long criterionId = existingRegularKeywordsToCriterionIdMap.get(keywordString);
					if (criterionId == null)
					{
						throw new RuntimeException("CriterionID is null for Keyword [" + keywordString + "]");						
					}
					final AdGroupCriterionOperation regularKeywordRemoveOperation = getRegularKeywordRemoveOperation(adGroupID, criterionId);
					regularKeywordOperations.add(regularKeywordRemoveOperation);
				}				
			}	
			else
			{
				final AdGroupCriterionOperation regularKeywordAddUpdateOperation;
				if (existingRegularKeywordsToCriterionIdMap.containsKey(keywordString))
				{
					final Long criterionId = existingRegularKeywordsToCriterionIdMap.get(keywordString);
					if (criterionId == null)
					{
						throw new RuntimeException("CriterionID is null for Keyword [" + keywordString + "]");						
					}
					regularKeywordAddUpdateOperation = getRegularKeywordUpdateOperation(adGroupID, keywordString, matchType, microBidAmount, criterionId);
				}
				else
				{
					regularKeywordAddUpdateOperation = getRegularKeywordAddOperation(adGroupID, keywordString, matchType, microBidAmount);
				}				
				regularKeywordOperations.add(regularKeywordAddUpdateOperation);				
				if (removeOpposite)
				{
					final Long criterionId = existingNegativeKeywordsToCriterionIdMap.get(keywordString);
					if (criterionId == null)
					{
						throw new RuntimeException("CriterionID is null for Keyword [" + keywordString + "]");						
					}
					final CampaignCriterionOperation negativeKeywordRemoveOperation = getNegativeKeywordRemoveOperation(campaignID, criterionId);
					negativeKeywordOperations.add(negativeKeywordRemoveOperation);
				}
			}
		}		
	}
		
	public CampaignCriterion[] getAllCampaignCriterions(String accountID, Long campaignId) throws Exception
	{
		logger.info("Will try find All Campaign Criterions for AccountID [" + accountID + "], CampaignID [" + campaignId + "]");
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final CampaignCriterionServiceInterface campaignCriterionService = user.getService(AdWordsService.V201109.CAMPAIGN_CRITERION_SERVICE);
			final Selector selector = new Selector();
			selector.setFields(new String[]{"Id", "KeywordText", "KeywordMatchType", "GeoPoint", "RadiusInUnits", "Address"});
			final List<Predicate> predicates = new ArrayList<Predicate>();
			final Predicate adGroupIdPredicate = new Predicate("CampaignId", PredicateOperator.IN, new String[]{campaignId.toString()});
			predicates.add(adGroupIdPredicate);
			final Predicate[] predicateArray = predicates.toArray(new Predicate[predicates.size()]); 
			selector.setPredicates(predicateArray);
			final CampaignCriterionPage page = campaignCriterionService.get(selector);
			if (page.getEntries() != null && page.getEntries().length > 0)
			{
				return page.getEntries();
			}
			else
			{
				return new CampaignCriterion[0];
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem getting All Campaign Criterions for AccountID [" + accountID + "], CampaignID [" + campaignId + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem getting All Campaign Criterions for AccountID [" + accountID + "], CampaignID [" + campaignId + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem getting All Campaign Criterions for AccountID [" + accountID + "], CampaignID [" + campaignId + "]", e);
		}
	}
	
	public Map<String, Long> getAllNegativeKeywordsToCriterionIdMap(String accountID, Long campaignId, KeywordMatchType matchType) throws Exception
	{
		final Map<String, Long> negativeKeywordsToCriterionIdMap = new HashMap<String, Long>();
		final CampaignCriterion[] campaignCriterions = getAllCampaignCriterions(accountID, campaignId);		
		for (final CampaignCriterion campaignCriterion : campaignCriterions)
		{
			if (campaignCriterion instanceof NegativeCampaignCriterion)
			{
				final NegativeCampaignCriterion negativeCampaignCriterion = (NegativeCampaignCriterion)campaignCriterion;
				final Criterion criterion = negativeCampaignCriterion.getCriterion();
				if (criterion instanceof Keyword)
				{
					final Keyword negativeKeyword = (Keyword)criterion;
					final KeywordMatchType negativeKeywordMatchType = negativeKeyword.getMatchType();
					if (negativeKeywordMatchType == matchType)
					{
						final Long criterionId = negativeKeyword.getId();
						final String negativeKeywordText = negativeKeyword.getText();
						negativeKeywordsToCriterionIdMap.put(negativeKeywordText, criterionId);
					}
				}
			}
		}
		logger.info("Out of " + campaignCriterions.length + " CampaignCriterions in Google for AccountID [" + accountID + "] and CampaignID [" + campaignId + "], found " + negativeKeywordsToCriterionIdMap.size() + " Negative Keywords");
		return negativeKeywordsToCriterionIdMap;
	}
	
	@Override
	public Boolean addUpdateKeywords(String accountID, Long campaignID, Long adGroupID, Map<KeywordProbabilityObject, Boolean> keywordProbabilityToRemoveOppositeMap, KeywordMatchType matchType, Long microBidAmount) throws Exception
	{
		logger.info("Will try to Add/Update Keywords for AccountID [" + accountID + "], AgGroupID [" + adGroupID + "], KeywordMatchType [" + matchType + "], MicroBidAmount [" + microBidAmount + "], " + keywordProbabilityToRemoveOppositeMap.size() + "Map of KeywordProbabilities<->RemoveOpposite:\n" + SemplestUtils.getEasilyReadableString(keywordProbabilityToRemoveOppositeMap));
		try
		{
			final Map<String, Long> existingRegularKeywordsToCriterionIdMap = getAllRegularKeywordsToCriterionIdMap(accountID, adGroupID, true, matchType);
			final Map<String, Long> existingNegativeKeywordsToCriterionIdMap = getAllNegativeKeywordsToCriterionIdMap(accountID, campaignID, matchType);
			logger.info("Found " + existingRegularKeywordsToCriterionIdMap.size() + " existing Active RegularKeywords and " + existingNegativeKeywordsToCriterionIdMap.size() + " Active NegativeKeywords for AccountID [" + accountID + "], AgGroupID [" + adGroupID + "], KeywordMatchType [" + matchType + "]");
			final List<AdGroupCriterionOperation> regularKeywordOperationList = new ArrayList<AdGroupCriterionOperation>();
			final List<CampaignCriterionOperation> negativeKeywordOperationList = new ArrayList<CampaignCriterionOperation>();
			populateKeywordOperations(campaignID, adGroupID, keywordProbabilityToRemoveOppositeMap, matchType, microBidAmount, regularKeywordOperationList, negativeKeywordOperationList, existingRegularKeywordsToCriterionIdMap, existingNegativeKeywordsToCriterionIdMap);
			logger.info("Out of " + keywordProbabilityToRemoveOppositeMap.size() + " KeywordProbabilities<->RemoveOpposite Mappings, generated " + regularKeywordOperationList.size() + " RegularKeyword Operations and " + negativeKeywordOperationList.size() + " NegativeKeyword Operations");
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			if (regularKeywordOperationList.isEmpty())
			{
				logger.info("No RegularKeywords to work with");
			}
			else
			{
				logger.info("Will try to execute " + regularKeywordOperationList.size() + " RegularKeywords operations");
				int numRegularKeywordResults = 0;
				final AdGroupCriterionServiceInterface adGroupCriterionService = user.getService(AdWordsService.V201109.ADGROUP_CRITERION_SERVICE);
				final AdGroupCriterionOperation[] regularKeywordOperations = regularKeywordOperationList.toArray(new AdGroupCriterionOperation[regularKeywordOperationList.size()]);
				final AdGroupCriterionRetriableGoogleOperation retriableOperation = new AdGroupCriterionRetriableGoogleOperation(adGroupCriterionService, regularKeywordOperations, SemplestUtils.DEFAULT_RETRY_COUNT);
				final AdGroupCriterionReturnValue regularKeywordResult = retriableOperation.performOperation();			
				if (regularKeywordResult != null && regularKeywordResult.getValue() != null)
				{
					final AdGroupCriterion[] adGroupCriterions = regularKeywordResult.getValue();
					for (final AdGroupCriterion adGroupCriterion : adGroupCriterions)
					{
						if (adGroupCriterion instanceof BiddableAdGroupCriterion)
						{
							++numRegularKeywordResults;
						}
					}
					if (regularKeywordOperationList.size() != numRegularKeywordResults)
					{
						logger.warn("# of RegularKeyword Results [" + numRegularKeywordResults + "] is NOT equal to the # we expected [" + regularKeywordOperationList.size() + "]");						
					}
					else
					{
						logger.warn("As expected, # of RegularKeyword Results [" + numRegularKeywordResults + "] is equal to the # we expected [" + regularKeywordOperationList.size() + "]");
					}
				}
				else
				{
					logger.error("No results returned from Google when executing RegularKeyword operations.  This is NOT expected.");
					return false;
				}
			}			
			if (negativeKeywordOperationList.isEmpty())
			{
				logger.info("No NegativeKeywords to work with");
			}
			else
			{
				final CampaignCriterionServiceInterface campaignCriterionService = user.getService(AdWordsService.V201109.CAMPAIGN_CRITERION_SERVICE);
				final CampaignCriterionOperation[] negativeKeywordOperations = negativeKeywordOperationList.toArray(new CampaignCriterionOperation[negativeKeywordOperationList.size()]);
				final CampaignCriterionReturnValue result = campaignCriterionService.mutate(negativeKeywordOperations);
				if (result != null && result.getValue() != null)
				{
					final CampaignCriterion[] campaignCriterions = result.getValue();
					final int numNegativeKeywordResults = campaignCriterions.length;
					if (numNegativeKeywordResults != negativeKeywordOperationList.size())
					{
						logger.warn("# of NegativeKeyword Results [" + numNegativeKeywordResults + "] is NOT equal to the # we expected [" + negativeKeywordOperationList.size() + "]");
					}
					else
					{
						logger.warn("As expected, # of NegativeKeyword Results [" + numNegativeKeywordResults + "] is equal to the # we expected [" + negativeKeywordOperationList.size() + "]");
					}
				}
				else
				{
					logger.error("No results returned from Google when executing NegativeKeyword operations.  This is NOT expected.");
					return false;
				}
			}
			return true;
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem doing Add/Update Keywords for AccountID [" + accountID + "], AgGroupID [" + adGroupID + "], KeywordMatchType [" + matchType + "], MicroBidAmount [" + microBidAmount + "], " + keywordProbabilityToRemoveOppositeMap.size() + " KeywordProbabilities<->RemoveOpposite mappings (for details look in log)", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem doing Add/Update Keywords for AccountID [" + accountID + "], AgGroupID [" + adGroupID + "], KeywordMatchType [" + matchType + "], MicroBidAmount [" + microBidAmount + "], " + keywordProbabilityToRemoveOppositeMap.size() + " KeywordProbabilities<->RemoveOpposite mappings (for details look in log)" + ": " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem doing Add/Update Keywords for AccountID [" + accountID + "], AgGroupID [" + adGroupID + "], KeywordMatchType [" + matchType + "], MicroBidAmount [" + microBidAmount + "], " + keywordProbabilityToRemoveOppositeMap.size() + " KeywordProbabilities<->RemoveOpposite mappings (for details look in log)", e);
		}
	}

	public String changeCampaignBudget(String json) throws Exception
	{
		logger.debug("call changeCampaignStatus(String json): [" + json + "]");
		final Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		final String accountID = data.get("accountID");
		final Long campaignID = Long.parseLong(data.get("campaignID"));
		final Long microBudgetAmount = Long.valueOf(data.get("microBudgetAmount"));
		final Boolean res = changeCampaignBudget(accountID, campaignID, microBudgetAmount);
		return gson.toJson(res);
	}

	@Override
	public Boolean changeCampaignBudget(String accountID, Long campaignID, Long newMicroBudgetAmount) throws Exception
	{
		logger.info("Will try to update Google Campaign for ID [" + campaignID + "] with new Micro Budget Amount [" + newMicroBudgetAmount + "] for Google Account [" + accountID + "]");		
		try
		{
			final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			final CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);
			final Budget budget = new Budget();
			budget.setPeriod(BudgetBudgetPeriod.DAILY);
			final Money money = new Money();
			money.setMicroAmount(newMicroBudgetAmount);
			budget.setAmount(money);
			budget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);
			final Campaign campaign = new Campaign();
			campaign.setId(campaignID);
			campaign.setBudget(budget);
			final CampaignOperation operation = new CampaignOperation();
			operation.setOperand(campaign);
			operation.setOperator(Operator.SET);
			final CampaignOperation[] operations = new CampaignOperation[]{operation};			
			final CampaignRetriableGoogleOperation retriableOperation = new CampaignRetriableGoogleOperation(campaignService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);			
			final CampaignReturnValue ret = retriableOperation.performOperation();
			if (ret != null && ret.getValue() != null)
			{
				final Campaign[] affectedCampaigns = ret.getValue();
				final Campaign affectedCampaign = affectedCampaigns[0];
				final Long affectedCampaignID = affectedCampaign.getId();
				final Budget affectedCampaignBudget = affectedCampaign.getBudget();
				final Money affectedCampaignBudgetAmountMoney = affectedCampaignBudget.getAmount();
				final Long affectedCampaignBudgetAmount = affectedCampaignBudgetAmountMoney.getMicroAmount();
				if (!campaignID.equals(affectedCampaignID))
				{
					final String errMsg = "The ID of the Google campaign that was changed [" + affectedCampaignID + "] is not the same as the ID of the campaign that we intended to change [" + campaignID + "].  Perhaps the wrong campaign was changed.";
					logger.error(errMsg);
					throw new Exception(errMsg);
				}
				if (!newMicroBudgetAmount.equals(affectedCampaignBudgetAmount))
				{
					final String errMsg = "The resulting $-amount of the Google campaign that was changed [" + affectedCampaignBudgetAmount + "] is not the same as the amount that we intended [" + newMicroBudgetAmount + "].";
					logger.error(errMsg);
					throw new Exception(errMsg);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception("Problem updating Google Campaign for ID [" + campaignID + "] with new Micro Budget Amount [" + newMicroBudgetAmount + "]", e);
		}
		catch (ApiException e)
		{
			throw new Exception("Problem updating Google Campaign for ID [" + campaignID + "] with new Micro Budget Amount [" + newMicroBudgetAmount + "]: " + e.dumpToString(), e);
		}
		catch (RemoteException e)
		{
			throw new Exception("Problem updating Google Campaign for ID [" + campaignID + "] with new Micro Budget Amount [" + newMicroBudgetAmount + "]", e);
		}
	}

	public String getCampaignsByAccountId(String json) throws Exception
	{
		logger.debug("call getCampaignsByAccountId" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		ArrayList<HashMap<String, String>> res = getCampaignsByAccountId(data.get("accountID"), Boolean.valueOf(data.get("includeDeleted")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public ArrayList<HashMap<String, String>> getCampaignsByAccountId(String accountID, Boolean includeDeleted) throws Exception
	{
		try
		{

			// AdWordsServiceLogger.log(); //SOAP XML Logger

			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);

			// Create selector.
			Selector selectActiveAndPausedCampaigns = new Selector();
			selectActiveAndPausedCampaigns.setFields(new String[]
			{ "Id", "Name", "Status", "Amount", "StartDate" });
			// TODO should only be returning Id, Name and Status from this
			// method
			// not Campaign[]

			if (!includeDeleted)
			{
				selectActiveAndPausedCampaigns.setPredicates(new Predicate[]
				{ new Predicate("Status", PredicateOperator.IN, new String[]
				{ CampaignStatus.ACTIVE.getValue(), CampaignStatus.PAUSED.getValue() }) });
			}
			CampaignPage page = campaignService.get(selectActiveAndPausedCampaigns);
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			if (page.getEntries() != null)
			{
				for (Campaign campaign : page.getEntries())
				{
					HashMap<String, String> row = new HashMap<String, String>();
					row.put("Id", String.valueOf(campaign.getId()));
					row.put("Name", campaign.getName());
					row.put("Status", campaign.getStatus().getValue());
					row.put("Amount", String.valueOf(campaign.getBudget().getAmount().getMicroAmount()));
					list.add(row);
				}

			}
			return list;
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	public String UpdateCampaignName(String json) throws Exception
	{
		logger.debug("call UpdateCampaignName" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long campaignID = Long.parseLong(data.get("campaignID"));
		Boolean res = UpdateCampaignName(data.get("accountID"), campaignID, data.get("newName"));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public Boolean UpdateCampaignName(String accountID, Long campaignID, String newName) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			CampaignServiceInterface campaignService = user.getService(AdWordsService.V201109.CAMPAIGN_SERVICE);
			CampaignOperation[] operations = getCampaignOp(campaignID, Operator.SET);
			operations[0].getOperand().setName(newName);
			CampaignReturnValue ret = campaignService.mutate(operations);
			if (ret != null && ret.getValue() != null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	private CampaignOperation[] getCampaignOp(Long campaignID, Operator op)
	{
		final Campaign campaign = new Campaign();
		campaign.setId(campaignID);
		final CampaignOperation operation = new CampaignOperation();
		operation.setOperand(campaign);
		operation.setOperator(op);
		return new CampaignOperation[]{operation};
	}

	/*
	 * Keywords
	 */
	public String GetRelatedKeywords(String json) throws Exception
	{
		logger.debug("call GetRelatedKeywords" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		GoogleRelatedKeywordObject res = GetRelatedKeywords(data.get("keyword"), KeywordMatchType.fromString(data.get("matchType")), Integer.parseInt(data.get("numberResults")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public GoogleRelatedKeywordObject GetRelatedKeywords(String keyword, KeywordMatchType matchType, int numberResults) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// Get the TargetingIdeaService.
			TargetingIdeaServiceInterface targetingIdeaService = user.getService(AdWordsService.V201109.TARGETING_IDEA_SERVICE);
			// Create seed keyword.
			Keyword keywrd = new Keyword();
			keywrd.setText(keyword);
			keywrd.setMatchType(matchType);
			// Create selector.
			TargetingIdeaSelector selector = new TargetingIdeaSelector();
			selector.setRequestType(RequestType.IDEAS);
			selector.setIdeaType(IdeaType.KEYWORD);
			selector.setRequestedAttributeTypes(new AttributeType[]
			{ AttributeType.CRITERION, AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES, AttributeType.COMPETITION });
			// Set selector paging (required for targeting idea service).
			Paging paging = new Paging();
			paging.setStartIndex(0);
			paging.setNumberResults(numberResults);
			selector.setPaging(paging);
			// Create related to keyword search parameter.
			RelatedToKeywordSearchParameter relatedToKeywordSearchParameter = new RelatedToKeywordSearchParameter();
			relatedToKeywordSearchParameter.setKeywords(new Keyword[]
			{ keywrd });
			// Create keyword match type search parameter to ensure unique
			// results.
			KeywordMatchTypeSearchParameter keywordMatchTypeSearchParameter = new KeywordMatchTypeSearchParameter();
			keywordMatchTypeSearchParameter.setKeywordMatchTypes(new KeywordMatchType[]
			{ matchType });
			selector.setSearchParameters(new SearchParameter[]
			{ relatedToKeywordSearchParameter, keywordMatchTypeSearchParameter });
			// Get related keywords.
			TargetingIdeaPage page = targetingIdeaService.get(selector);
			if (page != null && page.getEntries() != null)
			{
				GoogleRelatedKeywordObject keyObj = new GoogleRelatedKeywordObject(keyword, null);

				for (TargetingIdea targetingIdea : page.getEntries())
				{
					Map<AttributeType, Attribute> data = MapUtils.toMap(targetingIdea.getData());
					Keyword kw = (Keyword) ((CriterionAttribute) data.get(AttributeType.CRITERION)).getValue();
					Long averageMonthlySearches = ((LongAttribute) data.get(AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES)).getValue();
					Double comp = ((DoubleAttribute) data.get(AttributeType.COMPETITION)).getValue();
					keyObj.addKeywordData(kw.getText(), kw.getMatchType().getValue(), averageMonthlySearches, comp);
				}
				return keyObj;
			}
			else
			{
				return new GoogleRelatedKeywordObject(keyword, null);
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}

	}

	public String GetRelatedKeywordsForURL(String json) throws Exception
	{
		logger.debug("call GetRelatedKeywords" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		GoogleRelatedKeywordObject res = GetRelatedKeywordsForURL(data.get("url"), data.get("keyword"),KeywordMatchType.fromString(data.get("matchType")), Integer.parseInt(data.get("numberResults")));
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public GoogleRelatedKeywordObject GetRelatedKeywordsForURL(String url, String keyword, KeywordMatchType matchType, int numberResults)
			throws Exception
	{
		try
		{
			// Specifically you would use the RelatedToUrlSearchParameter,
			// ideaType
			// of KEYWORD, and requestType of IDEAS
			// TODO Auto-generated method stub
			AdWordsUser user = new AdWordsUser(email, password, null, userAgent, developerToken, useSandbox);
			// Get the TargetingIdeaService.
			TargetingIdeaServiceInterface targetingIdeaService = user.getService(AdWordsService.V201109.TARGETING_IDEA_SERVICE);
			// Create selector.
			TargetingIdeaSelector selector = new TargetingIdeaSelector();
			selector.setRequestType(RequestType.IDEAS);
			selector.setIdeaType(IdeaType.KEYWORD);
			selector.setRequestedAttributeTypes(new AttributeType[]
			{ AttributeType.CRITERION, AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES, AttributeType.COMPETITION }); // AttributeType.APPROX_CONTENT_IMPRESSIONS_PER_DAY,
			// });
			// Set selector paging (required for targeting idea service).
			Paging paging = new Paging();
			paging.setStartIndex(0);
			paging.setNumberResults(numberResults);
			selector.setPaging(paging);
			Keyword keywrd = new Keyword();
			keywrd.setText(keyword);
			keywrd.setMatchType(matchType);
			// Create related to keyword search parameter.
			RelatedToKeywordSearchParameter relatedToKeywordSearchParameter = new RelatedToKeywordSearchParameter();
			relatedToKeywordSearchParameter.setKeywords(new Keyword[]
			{ keywrd });
			// Create related to URL search parameter.
			RelatedToUrlSearchParameter relatedToUrlSearchParameter = new RelatedToUrlSearchParameter();
			relatedToUrlSearchParameter.setUrls(new String[]
			{ url });
			// relatedToUrlSearchParameter.setIncludeSubUrls(true);
			selector.setSearchParameters(new SearchParameter[]
			{ relatedToKeywordSearchParameter, relatedToUrlSearchParameter }); // ,
			// Get related placements.
			TargetingIdeaPage page = targetingIdeaService.get(selector);
			if (page != null && page.getEntries() != null)
			{
				GoogleRelatedKeywordObject keyObj = new GoogleRelatedKeywordObject(keyword, null);

				for (TargetingIdea targetingIdea : page.getEntries())
				{
					Map<AttributeType, Attribute> data = MapUtils.toMap(targetingIdea.getData());
					Keyword kw = (Keyword) ((CriterionAttribute) data.get(AttributeType.CRITERION)).getValue();
					Long averageMonthlySearches = ((LongAttribute) data.get(AttributeType.AVERAGE_TARGETED_MONTHLY_SEARCHES)).getValue();
					Double comp = ((DoubleAttribute) data.get(AttributeType.COMPETITION)).getValue();
					keyObj.addKeywordData(kw.getText(), kw.getMatchType().getValue(), averageMonthlySearches, comp);
				}
				return keyObj;
			}
			else
			{
				return new GoogleRelatedKeywordObject(keyword, null);
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	public String getTrafficEstimationForOneKeyword(String json) throws Exception
	{
		logger.debug("call getTrafficEstimationForOneKeyword" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		HashMap<String, Long> KeywordWithBid = gson.fromJson(data.get("KeywordWithBid"), HashMap.class);
		Long campaignID = Long.parseLong(data.get("campaignID"));
		// String accountID, Long campaignID, KeywordMatchType matchType,
		// HashMap<String, ArrayList<Double>> KeywordWithBid
		TrafficEstimatorObject res = getTrafficEstimationForKeywords(data.get("accountID"), campaignID,
				KeywordMatchType.fromString(data.get("matchType")), KeywordWithBid);
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public TrafficEstimatorObject getTrafficEstimationForKeywords(String accountID, Long campaignID, KeywordMatchType matchType,
			HashMap<String, Long> newKeywordWithBid) throws Exception
	{
		try
		{
			//AdWordsServiceLogger.log();
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			List<AdGroupEstimateRequest> adGroupEstimateRequests = new ArrayList<AdGroupEstimateRequest>();
			ArrayList<KeywordEstimateRequest> keywordEstimateRequests = new ArrayList<KeywordEstimateRequest>();

			// Get the TrafficEstimatorService.
			TrafficEstimatorServiceInterface trafficEstimatorService = user.getService(AdWordsService.V201109.TRAFFIC_ESTIMATOR_SERVICE);

			// for each keyword
			String[] keywords = newKeywordWithBid.keySet().toArray(new String[]
			{});

			for (int i = 0; i < keywords.length; i++)
			{

				String keyword = keywords[i];
				// Use same keyword
				Keyword keywrd = new Keyword();
				keywrd.setText(keyword);
				keywrd.setMatchType(matchType);
				// Sets up one Campaign EStimate for each Keyword/bid - Campaign
				// budget
				// is unlimited
				Long microBidamount = newKeywordWithBid.get(keyword);

				if (microBidamount != null)
				{
					// one keyword estimate request
					KeywordEstimateRequest keywordEstimateRequest = new KeywordEstimateRequest();
					keywordEstimateRequest.setKeyword(keywrd);

					Money money = new Money();
					money.setMicroAmount(microBidamount);
					keywordEstimateRequest.setMaxCpc(money);
					keywordEstimateRequests.add(keywordEstimateRequest);
				}
			}
			AdGroupEstimateRequest adGroupEstimateRequest = new AdGroupEstimateRequest();
			adGroupEstimateRequest.setKeywordEstimateRequests(keywordEstimateRequests.toArray(new KeywordEstimateRequest[]
			{}));

			adGroupEstimateRequests.add(adGroupEstimateRequest);

			CampaignEstimateRequest campaignEstimateRequest = new CampaignEstimateRequest();
			campaignEstimateRequest.setAdGroupEstimateRequests(adGroupEstimateRequests.toArray(new AdGroupEstimateRequest[]
			{}));
			campaignEstimateRequest.setCampaignId(campaignID);
			Language english = new Language();
			english.setId(1000L);
			campaignEstimateRequest.setCriteria(new Criterion[]
			{ english });
			// Create selector.
			TrafficEstimatorSelector selector = new TrafficEstimatorSelector();
			selector.setCampaignEstimateRequests(new CampaignEstimateRequest[]
			{ campaignEstimateRequest });
			// Get traffic estimates.

			TrafficEstimatorResult result = trafficEstimatorService.get(selector);
			// Display traffic estimates.
			if (result != null && result.getCampaignEstimates() != null)
			{
				TrafficEstimatorObject estimatorObj = new TrafficEstimatorObject();
				KeywordEstimate[] keywordEstimates = result.getCampaignEstimates()[0].getAdGroupEstimates()[0].getKeywordEstimates();

				for (int i = 0; i < keywordEstimates.length; i++)
				{

					StatsEstimate min = keywordEstimates[i].getMin();
					StatsEstimate max = keywordEstimates[i].getMax();
					estimatorObj.setBidData(adGroupEstimateRequests.get(0).getKeywordEstimateRequests()[i].getKeyword().getText(),
							adGroupEstimateRequests.get(0).getKeywordEstimateRequests()[i].getMaxCpc().getMicroAmount(),
							adGroupEstimateRequests.get(0).getKeywordEstimateRequests()[i].getKeyword().getMatchType().toString(), min
									.getAverageCpc().getMicroAmount(), max.getAverageCpc().getMicroAmount(), min.getAveragePosition(), max
									.getAveragePosition(), min.getClicksPerDay(), max.getClicksPerDay(), min.getTotalCost().getMicroAmount(), max
									.getTotalCost().getMicroAmount());
				}

				return estimatorObj;
			}
			else
			{

				return new TrafficEstimatorObject();
			}
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}

	}

	public String getBidLandscapeForKeyword(String json) throws Exception
	{
		logger.debug("call  getBidLandscapeForKeyword" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		Long keywordID = Long.parseLong(data.get("keywordID"));
		BidSimulatorObject[] res = getBidLandscapeForKeyword(data.get("accountID"), adGroupID, keywordID);
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public BidSimulatorObject[] getBidLandscapeForKeyword(String accountID, Long adGroupID, Long keywordID) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the DataService.
			DataServiceInterface dataService = user.getService(AdWordsService.V201109.DATA_SERVICE);

			// Create selector.
			Selector selector = new Selector();
			selector.setFields(new String[]
			{ "AdGroupId", "CriterionId", "StartDate", "EndDate", "Bid", "LocalClicks", "LocalCost", "MarginalCpc", "LocalImpressions" });
			// Create predicates.
			Predicate adGroupIdPredicate = new Predicate("AdGroupId", PredicateOperator.IN, new String[]
			{ adGroupID.toString() });

			Predicate criterionIdPredicate = new Predicate("CriterionId", PredicateOperator.IN, new String[]
			{ keywordID.toString() });
			selector.setPredicates(new Predicate[]
			{ adGroupIdPredicate, criterionIdPredicate });

			// Get bid landscape for ad group criteria.
			CriterionBidLandscapePage page = dataService.getCriterionBidLandscape(selector);
			// Display bid landscapes.
			BidSimulatorObject[] res;
			if (page.getEntries() != null && page.getEntries().length > 0)
			{
				res = new BidSimulatorObject[page.getEntries().length];
				int i = 0;
				for (CriterionBidLandscape criterionBidLandscape : page.getEntries())
				{
					BidSimulatorObject obj = new BidSimulatorObject();
					obj.setAdGroupId(criterionBidLandscape.getAdGroupId());
					obj.setCriterionId(criterionBidLandscape.getCriterionId());
					obj.setEndDate(criterionBidLandscape.getEndDate());
					obj.setStartDate(criterionBidLandscape.getStartDate());

					for (BidLandscapeLandscapePoint bidLanscapePoint : criterionBidLandscape.getLandscapePoints())
					{
						obj.addBidPoint(bidLanscapePoint.getBid().getMicroAmount(), bidLanscapePoint.getClicks(), bidLanscapePoint.getCost()
								.getMicroAmount(), bidLanscapePoint.getMarginalCpc().getMicroAmount(), bidLanscapePoint.getImpressions());
					}
					res[i] = obj;
					i++;
				}
			}
			else
			{
				res = new BidSimulatorObject[0];
				logger.info("No criterion bid landscapes were found.");
			}
			return res;
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}

	}

	public String getBidLandscapeForAdgroup(String json) throws Exception
	{
		logger.debug("call  getBidLandscapeForAdgroup" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		BidSimulatorObject[] res = getBidLandscapeForAdgroup(data.get("accountID"), adGroupID);
		// convert result to Json String
		return gson.toJson(res);
	}

	@Override
	public BidSimulatorObject[] getBidLandscapeForAdgroup(String accountID, Long adGroupID) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the DataService.
			DataServiceInterface dataService = user.getService(AdWordsService.V201109.DATA_SERVICE);

			// Create selector.
			Selector selector = new Selector();
			selector.setFields(new String[]
			{ "AdGroupId", "LandscapeType", "LandscapeCurrent", "StartDate", "EndDate", "Bid", "LocalClicks", "LocalCost", "MarginalCpc",
					"LocalImpressions" });
			// Create predicates.
			Predicate adGroupIdPredicate = new Predicate("AdGroupId", PredicateOperator.IN, new String[]
			{ adGroupID.toString() });
			selector.setPredicates(new Predicate[]
			{ adGroupIdPredicate });
			// Get bid landscape for ad group criteria.
			AdGroupBidLandscapePage page = dataService.getAdGroupBidLandscape(selector);
			BidSimulatorObject[] res;
			if (page.getEntries() != null && page.getEntries().length > 0)
			{
				res = new BidSimulatorObject[page.getEntries().length];
				int i = 0;
				for (AdGroupBidLandscape adGroupBidLandscape : page.getEntries())
				{
					BidSimulatorObject obj = new BidSimulatorObject();
					obj.setAdGroupId(adGroupBidLandscape.getAdGroupId());
					obj.setEndDate(adGroupBidLandscape.getEndDate());
					obj.setStartDate(adGroupBidLandscape.getStartDate());

					for (BidLandscapeLandscapePoint bidLanscapePoint : adGroupBidLandscape.getLandscapePoints())
					{
						obj.addBidPoint(bidLanscapePoint.getBid().getMicroAmount(), bidLanscapePoint.getClicks(), bidLanscapePoint.getCost()
								.getMicroAmount(), bidLanscapePoint.getMarginalCpc().getMicroAmount(), bidLanscapePoint.getImpressions());
					}
					res[i] = obj;
					i++;
				}
			}
			else
			{
				res = new BidSimulatorObject[0];
				logger.info("No Adgroup bid landscapes were found.");
			}
			return res;
		}
		catch (ServiceException e)
		{
			throw new Exception(e);
		}
		catch (ApiException e)
		{
			throw new Exception(e.dumpToString());
		}
		catch (RemoteException e)
		{
			throw new Exception(e);
		}
	}

	public String getReportForAccount(String json) throws Exception
	{
		logger.debug("call  getReportForAccount" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);		
		ReportObject[] res = getReportForAccount(data.get("accountID"), data.get("startDate"), data.get("endDate"));
		// convert result to Json String
		return gson.toJson(res);
	}

	private static final String DEFINITION = "<reportDefinition><selector><fields>Date</fields>"
			+ "<fields>CampaignId</fields><fields>Id</fields><fields>HourOfDay</fields>"
			+ "<fields>Impressions</fields><fields>Clicks</fields><fields>Cost</fields>"
			+ "</selector><reportName>Custom ADGROUP_PERFORMANCE_REPORT for testing</reportName>"
			+ "<reportType>ADGROUP_PERFORMANCE_REPORT</reportType>"
			+ "<dateRangeType>LAST_7_DAYS</dateRangeType><downloadFormat>CSV</downloadFormat>" + "</reportDefinition>";

	private static final String KEYWORD_DEFINITION = "<reportDefinition><selector><fields>Date</fields>"
			+ "<fields>AdGroupId</fields><fields>Id</fields><fields>KeywordText</fields><fields>KeywordMatchType</fields>"
			+ "<fields>Impressions</fields><fields>Clicks</fields><fields>Cost</fields><fields>QualityScore</fields>"
			+ "<fields>AverageCpc</fields><fields>AveragePosition</fields><fields>CampaignId</fields><fields>Ctr</fields><fields>FirstPageCpc</fields><fields>MaxCpc</fields>"
			+ "<fields>ApprovalStatus</fields><fields>CampaignId</fields>" + "</selector><reportName>KEYWORDS_PERFORMANCE_REPORT</reportName>"
			+ "<reportType>KEYWORDS_PERFORMANCE_REPORT</reportType>"
			+ "<dateRangeType>LAST_7_DAYS</dateRangeType><downloadFormat>CSV</downloadFormat>" + "</reportDefinition>";

	private static final String SEARCH_QUERY_DEFINITION = "<reportDefinition><selector><fields>Date</fields>"
			+ "<fields>AdGroupId</fields><fields>Query</fields><fields>MatchType</fields>"
			+ "<fields>Impressions</fields><fields>Clicks</fields><fields>Cost</fields>"
			+ "<fields>AverageCpc</fields><fields>AveragePosition</fields><fields>CampaignId</fields><fields>Ctr</fields>"
			+ "</selector><reportName>SEARCH_QUERY_PERFORMANCE_REPORT</reportName>" + "<reportType>SEARCH_QUERY_PERFORMANCE_REPORT</reportType>"
			+ "<dateRangeType>ALL_TIME</dateRangeType><downloadFormat>CSV</downloadFormat>" + "</reportDefinition>";

	public ReportObject[] getReportForAccount(String accountID, String startDate, String endDate) throws Exception
	{
		// DON'T CHANGE THE SELECOTR FIELDS! OR THE FORMAT OF REPORT WILL BE
		// DIFFERENT. AND THE DOWNLOADER WON'T TRANSLATE IT CORRECTLY!
		String REPORT_DEFINITION = "<reportDefinition><selector><fields>Date</fields>"
				+ "<fields>AdGroupId</fields><fields>Id</fields><fields>KeywordText</fields><fields>KeywordMatchType</fields>"
				+ "<fields>Impressions</fields><fields>Clicks</fields><fields>Cost</fields><fields>QualityScore</fields>"
				+ "<fields>AverageCpc</fields><fields>AveragePosition</fields><fields>CampaignId</fields><fields>Ctr</fields><fields>FirstPageCpc</fields><fields>MaxCpc</fields>"
				+ "<fields>ApprovalStatus</fields><fields>CampaignId</fields>" + "<dateRange> <min>" + startDate + "</min> <max>" + endDate
				+ "</max> </dateRange>" + "</selector><reportName>KEYWORDS_PERFORMANCE_REPORT</reportName>"
				+ "<reportType>KEYWORDS_PERFORMANCE_REPORT</reportType>"
				+ "<dateRangeType>CUSTOM_DATE</dateRangeType><downloadFormat>CSV</downloadFormat>" + "</reportDefinition>";
		
		GoogleReportDownloader report = new GoogleReportDownloader(REPORT_DEFINITION, new Long(accountID));//

		// File reportFile = report.downloadReport(new AuthToken(email,
		// password).getAuthToken(), developerToken);

		ArrayList<ReportObject> reportObj = report.getReportObject(new AuthToken(email, password).getAuthToken(), developerToken);
		if (reportObj.size() == 0)
		{
			return null;
		}
		else
		{
			ReportObject[] ret = new ReportObject[reportObj.size()];
			reportObj.toArray(ret);
			return ret;
		}

	}

	public ReportObject[] getSearchQueryReportForAccount(SemplestString accountID) throws Exception
	{
		GoogleReportDownloader report = new GoogleReportDownloader(SEARCH_QUERY_DEFINITION, new Long(accountID.getSemplestString()));//

		// File reportFile = report.downloadReport(new AuthToken(email,
		// password).getAuthToken(), developerToken);

		ArrayList<ReportObject> reportObj = report.getSearchQueryReportObject(new AuthToken(email, password).getAuthToken(), developerToken);
		if (reportObj.size() == 0)
		{
			return null;
		}
		else
		{
			ReportObject[] ret = new ReportObject[reportObj.size()];
			reportObj.toArray(ret);
			return ret;
		}

	}

	/*
	 * GeoTargeting
	 */
	public String setGeoTarget(String json) throws Exception
	{
		logger.debug("call CreateOneAccountService(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long campaignId = Long.parseLong(data.get("campaignId"));
		Double latitude = Double.parseDouble(data.get("latitude"));
		Double longitude = Double.parseDouble(data.get("longitude"));
		Double radius = Double.parseDouble(data.get("radius"));
		Boolean retval = setGeoTarget(data.get("accountId"), campaignId, latitude, longitude, radius, data.get("addr"), data.get("city"),
				data.get("state"), data.get("zip"));
		return gson.toJson(retval);
	}
	
	@Override
	public Boolean setGeoTarget(String accountId, Long campaignId, Double latitude, Double longitude, Double radius, String addr, String city,
			String state, String zip) throws Exception
	{
		AdWordsUser user = new AdWordsUser(email, password, accountId, userAgent, developerToken, useSandbox);
		semplest.service.google.adwords.Campaign c = new semplest.service.google.adwords.Campaign(accountId, campaignId, user);
		long res = 0;
		if (radius <= 0)
			res = c.setGeoLoc(state);
		else
			res = c.setGeoLoc(radius, latitude, longitude); 
		if (res == 0)
			return false;
		return true;
	}
	
	@Override
	public Boolean updateGeoTargets(final String accountId, final Long campaignId, final List<GeoTargetObject> geoTargets) throws Exception
	{
		final AdWordsUser user = new AdWordsUser(email, password, accountId, userAgent, developerToken, useSandbox);
		final semplest.service.google.adwords.Campaign semplestCampaign = new semplest.service.google.adwords.Campaign(accountId, campaignId, user);
		semplestCampaign.removeAllGeoLoc(campaignId);
		semplestCampaign.addGeoLoc(geoTargets);
		return true;
	}

	public void updateDefaultBid(String json) throws Exception
	{
		logger.debug("call updateDefaultBid(String json)" + json);
		Map<String, String> data = gson.fromJson(json, SemplestUtils.TYPE_MAP_OF_STRING_TO_STRING);
		Long adGroupID = Long.parseLong(data.get("adGroupID"));
		Long microBid = Long.parseLong(data.get("microBid"));
		updateDefaultBid(data.get("accountID"), adGroupID, microBid);
		// convert result to Json String
	}

	@Override
	public void updateDefaultBid(String accountID, Long adGroupID, Long microBid) throws Exception
	{
		try
		{
			AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
			// Get the AdGroupCriterionService.
			AdGroupServiceInterface adGroupService = user.getService(AdWordsService.V201109.ADGROUP_SERVICE);

			// long adGroupId = Long.parseLong(a;

			// Create ad group with updated status.
			AdGroup adGroup = new AdGroup();
			adGroup.setId(adGroupID);
			// adGroup.setStatus(AdGroupStatus.ENABLED);

			// Update ad group bid.
			ManualCPCAdGroupBids adGroupBids = new ManualCPCAdGroupBids();
			Money money = new Money();
			money.setMicroAmount(microBid);
			adGroupBids.setKeywordMaxCpc(new Bid(money));
			adGroup.setBids(adGroupBids);

			// Create operations.
			AdGroupOperation operation = new AdGroupOperation();
			operation.setOperand(adGroup);
			operation.setOperator(Operator.SET);

			AdGroupOperation[] operations = new AdGroupOperation[]
			{ operation };
			AdGroupReturnValue result = adGroupService.mutate(operations);

		}
		catch (Exception e)
		{
			logger.error("Problem", e);
			throw new Exception("Problem", e);
		}

	}
	
	/*
	 * initialBudget null means unlimited amount
	 */
	public Boolean setAccountBudget(String accountID, String billingAccountID, Long initialBudgetAmount) throws Exception
	{
		//AdWordsServiceLogger.log();
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final BudgetOrderServiceInterface budgetOrderService = user.getService(AdWordsService.V201109_1.BUDGET_ORDER_SERVICE);
		final BudgetOrder budgetOrder = new BudgetOrder();
		budgetOrder.setBillingAccountId(billingAccountID);
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 10);
		budgetOrder.setStartDateTime(SemplestUtils.DATE_FORMAT_YYYYMMDD_HHmmss.format(cal.getTime()));
		//assume end-date 15 years in future
		cal.add(Calendar.YEAR, 15);
		budgetOrder.setEndDateTime(SemplestUtils.DATE_FORMAT_YYYYMMDD_HHmmss.format(cal.getTime()));
		//setUnlimited Budget
		if (initialBudgetAmount == null)
		{
			initialBudgetAmount = -1L;
		}
		final com.google.api.adwords.v201109_1.cm.Money initialBudgetAmountMicro = new com.google.api.adwords.v201109_1.cm.Money();
		initialBudgetAmountMicro.setMicroAmount(initialBudgetAmount);
		budgetOrder.setSpendingLimit(initialBudgetAmountMicro);
		final BudgetOrderOperation budgetOrderOperation =  new BudgetOrderOperation();
		budgetOrderOperation.setOperator(com.google.api.adwords.v201109_1.cm.Operator.ADD);
		budgetOrderOperation.setOperand(budgetOrder);		
		final BudgetOrderOperation[] operations = new BudgetOrderOperation[]{budgetOrderOperation};
		final BudgetOrderRetriableGoogleOperation retriableOperation = new BudgetOrderRetriableGoogleOperation(budgetOrderService, operations, SemplestUtils.DEFAULT_RETRY_COUNT); 
		final BudgetOrderReturnValue result = retriableOperation.performOperation();		
		final BudgetOrder[] orders = result.getValue();
		if (orders.length > 0)
		{
			logger.info("Setup Account budget for " + accountID + " On BillingAccountID=" + orders[0].getBillingAccountId() + " For "
					+ orders[0].getSpendingLimit().getMicroAmount());
			return true;
		}
		else
		{
			logger.warn("Unable to setup Account budget for " + accountID + " On BillingAccountID=" +  billingAccountID );
			return false;
		}		
	}

	@Override
	public void initializeService(String input) throws Exception
	{
		/*
		 * Read in the Config Data from DB into HashMap<key, Object>
		 * SemplestConfiguation.configData
		 */
		Object object = new Object();
		SemplestConfiguration configDB = new SemplestConfiguration(object);
		Thread configThread = new Thread(configDB);
		configThread.start();
		synchronized (object)
		{
			object.wait();
		}

	}
	
	public List<SitelinksExtension> GetSitelinkExtensions(String accountID, Long campaignID, CampaignAdExtensionStatus status) throws Exception
	{
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final CampaignAdExtensionServiceInterface campaignAdExtensionService = user.getService(AdWordsService.V201109_1.CAMPAIGN_AD_EXTENSION_SERVICE);
		final com.google.api.adwords.v201109_1.cm.Selector selector = new com.google.api.adwords.v201109_1.cm.Selector();
		selector.setFields(new String[]{ "AdExtensionId", "CampaignId" });
		selector.setOrdering(new com.google.api.adwords.v201109_1.cm.OrderBy[]{new com.google.api.adwords.v201109_1.cm.OrderBy("AdExtensionId", com.google.api.adwords.v201109_1.cm.SortOrder.ASCENDING)});
		final com.google.api.adwords.v201109_1.cm.Predicate campaignIdPredicate = new com.google.api.adwords.v201109_1.cm.Predicate("CampaignId", com.google.api.adwords.v201109_1.cm.PredicateOperator.IN, new String[]{campaignID.toString()});
		final com.google.api.adwords.v201109_1.cm.Predicate adExtensionStatusPredicate = new com.google.api.adwords.v201109_1.cm.Predicate("Status", com.google.api.adwords.v201109_1.cm.PredicateOperator.EQUALS, new String[]{status.toString()});
		selector.setPredicates(new com.google.api.adwords.v201109_1.cm.Predicate[]{campaignIdPredicate, adExtensionStatusPredicate});
		final CampaignAdExtensionPage page = campaignAdExtensionService.get(selector);
		final CampaignAdExtension[] campaignAdExtensions = page.getEntries();
		final List<SitelinksExtension> sitelinksExtensions = new ArrayList<SitelinksExtension>();
		if (campaignAdExtensions != null && campaignAdExtensions.length > 0)
		{
			for (final CampaignAdExtension campaignAdExtension : campaignAdExtensions)
			{
				final AdExtension adExtension = campaignAdExtension.getAdExtension();
				final Boolean isSitelinksExtension = adExtension.getAdExtensionType().equalsIgnoreCase("SitelinksExtension");
				final CampaignAdExtensionStatus campaignAdExtensionStatus = campaignAdExtension.getStatus();
				//final Boolean isRequiredStatus = status.equals(campaignAdExtensionStatus);
				//if (isSitelinksExtension && isRequiredStatus)
			    if (isSitelinksExtension)
				{
					final SitelinksExtension siteLinkExtension = (SitelinksExtension)adExtension; 
					logger.debug("Found SiteLinkExtension with campaign id [" + campaignAdExtension.getCampaignId() + "], ad extension [" + siteLinkExtension.getId() + "], and type [" + siteLinkExtension.getAdExtensionType() + "] for GoogleAccountID [" + accountID + "]");
					sitelinksExtensions.add(siteLinkExtension);
				}
			}
		}
		if (sitelinksExtensions.isEmpty())
		{
			logger.debug("No SitelinksExtensions were found for GoogleAccountID [" + accountID + "] and CampaignID [" + campaignID + "] and CampaignAdExtensionStatus [" + status + "].");
		}
		return sitelinksExtensions;
	}


	private Long GetActiveSitelinkExtension(String accountID, Long campaignID) throws Exception
	{
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final CampaignAdExtensionServiceInterface campaignAdExtensionService = user.getService(AdWordsService.V201109_1.CAMPAIGN_AD_EXTENSION_SERVICE);
		final com.google.api.adwords.v201109_1.cm.Selector selector = new com.google.api.adwords.v201109_1.cm.Selector();
		selector.setFields(new String[]{ "AdExtensionId", "CampaignId" });
		selector.setOrdering(new com.google.api.adwords.v201109_1.cm.OrderBy[]{ new com.google.api.adwords.v201109_1.cm.OrderBy("AdExtensionId", com.google.api.adwords.v201109_1.cm.SortOrder.ASCENDING) });
		final com.google.api.adwords.v201109_1.cm.Predicate campaignIdPredicate = new com.google.api.adwords.v201109_1.cm.Predicate("CampaignId", com.google.api.adwords.v201109_1.cm.PredicateOperator.IN, new String[]{ campaignID.toString() });
		selector.setPredicates(new com.google.api.adwords.v201109_1.cm.Predicate[]{ campaignIdPredicate });
		final CampaignAdExtensionPage page = campaignAdExtensionService.get(selector);
		if (page.getEntries() != null && page.getEntries().length > 0)
		{
			for (CampaignAdExtension campaignAdExtension : page.getEntries())
			{
				if (campaignAdExtension.getAdExtension().getAdExtensionType().equalsIgnoreCase("SitelinksExtension") &&
					campaignAdExtension.getStatus() == CampaignAdExtensionStatus.ACTIVE)
				{
					logger.debug("Campaign ad extension with campaign id \"" + campaignAdExtension.getCampaignId() + "\", ad extension id \""
							+ campaignAdExtension.getAdExtension().getId() + "\", and type \""
							+ campaignAdExtension.getAdExtension().getAdExtensionType() + "\" was found.");
					return campaignAdExtension.getAdExtension().getId();
				}
			}
		}
		else
		{
			logger.debug("No campaign ad extensions were found.");
		}
		return null;
	}
	
	public List<CampaignAdExtensionOperation> getRemoveSiteLinksOperations(Long campaignID, List<Long> siteLinkExtIds)
	{
		final List<CampaignAdExtensionOperation> operationList = new ArrayList<CampaignAdExtensionOperation>(); 
		for (final Long siteLinkExtID : siteLinkExtIds)
		{
			final CampaignAdExtension campaignAdExtension = new CampaignAdExtension();
			campaignAdExtension.setCampaignId(campaignID);
			final AdExtension adExt = new AdExtension();
			adExt.setId(siteLinkExtID);
			campaignAdExtension.setAdExtension(adExt);
			final CampaignAdExtensionOperation operation = new CampaignAdExtensionOperation();
			operation.setOperand(campaignAdExtension);
			operation.setOperator(com.google.api.adwords.v201109_1.cm.Operator.REMOVE);
			operationList.add(operation);
		}	
		return operationList;
	}

	public void RemoveActiveSitelinkExtensions(String accountID, Long campaignID, List<Long> siteLinkExtIds) throws Exception
	{
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final CampaignAdExtensionServiceInterface campaignAdExtensionService = user.getService(AdWordsService.V201109_1.CAMPAIGN_AD_EXTENSION_SERVICE);
		final List<CampaignAdExtensionOperation> operationList = getRemoveSiteLinksOperations(campaignID, siteLinkExtIds);
		final CampaignAdExtensionOperation[] operations = operationList.toArray(new CampaignAdExtensionOperation[operationList.size()]);
		final CampaignAdExtensionReturnValue result = campaignAdExtensionService.mutate(operations);
		final CampaignAdExtension[] resultCampaignAdExtensions = result.getValue();
		if (resultCampaignAdExtensions.length != siteLinkExtIds.size())
		{
			logger.warn("# of SitelinkExtensions deleted [" + resultCampaignAdExtensions.length + "] is not as we expected [" + siteLinkExtIds.size() + "].\n\tSitelinkExtension deleted: []\n\tSitelinkExtensions intended to delete: []");
		}
	}
	
	public static List<SitelinksExtension> getMatchingSitelinks(final List<SitelinksExtension> sitelinksExtensions, final List<GoogleSiteLink> siteLinks)
	{
		final List<SitelinksExtension> matchingSitelinksExtensions = new ArrayList<SitelinksExtension>();
		if (sitelinksExtensions == null || siteLinks == null)
		{
			return matchingSitelinksExtensions;
		}		
		for (final SitelinksExtension sitelinksExtension : sitelinksExtensions)
		{
			final Sitelink[] sitelinks = sitelinksExtension.getSitelinks();
			if (sitelinks != null)
			{
				for (int i = 0; i < sitelinks.length; ++i)
				{
					final Sitelink sitelink = sitelinks[i];
					final String text = sitelink.getDisplayText();
					final String url = sitelink.getDestinationUrl();
					for (final GoogleSiteLink semplestSiteLink : siteLinks)
					{
						final Boolean textMatches = semplestSiteLink.getLinkText().equals(text);
						final Boolean urlMatches = semplestSiteLink.getLinkURL().equals(url);
						if (textMatches && urlMatches)
						{
							matchingSitelinksExtensions.add(sitelinksExtension);
						}
					}
				}
			}
		}
		return matchingSitelinksExtensions;
	}
	
	public static List<Long> getIds(final List<SitelinksExtension> sitelinksExtensions)
	{		
		final List<Long> ids = new ArrayList<Long>();
		if (sitelinksExtensions == null)
		{
			return ids;
		}
		for (final SitelinksExtension sitelinksExtension : sitelinksExtensions)
		{
			ids.add(sitelinksExtension.getId());
		}
		return ids;
	}
	
	public CampaignAdExtensionOperation getAddSiteLinksOperation(Long campaignID, List<GoogleSiteLink> siteLinks)
	{
		if (siteLinks == null || siteLinks.isEmpty())
		{
			return null;
		}
		final List<Sitelink> googleSiteLinks = new ArrayList<Sitelink>();
		for (final GoogleSiteLink semplestSiteLink : siteLinks)
		{
			final String text = semplestSiteLink.getLinkText();
			final String URL = semplestSiteLink.getLinkURL();
			final Sitelink googleSitelink = new Sitelink(text, URL);
			googleSiteLinks.add(googleSitelink);
		}
		final SitelinksExtension siteLinksExtension = new SitelinksExtension();
		siteLinksExtension.setSitelinks(googleSiteLinks.toArray(new Sitelink[googleSiteLinks.size()]));
		final CampaignAdExtension campaignAdExtension = new CampaignAdExtension();
		campaignAdExtension.setCampaignId(campaignID);
		campaignAdExtension.setAdExtension(siteLinksExtension);
		final CampaignAdExtensionOperation operation = new CampaignAdExtensionOperation();
		operation.setOperand(campaignAdExtension);
		operation.setOperator(com.google.api.adwords.v201109_1.cm.Operator.ADD);
		return operation;
	}

	@Override
	public Boolean refreshSiteLinks(final GoogleRefreshSiteLinksRequest request) throws Exception
	{
		logger.info("Will try to Refresh SiteLinks for " + request.toStringPretty());
		final String accountID = request.getAccountID();
		final Long campaignID = request.getCampaignID();
		final List<SitelinksExtension> activeSitelinksExtensions = GetSitelinkExtensions(accountID, campaignID, CampaignAdExtensionStatus.ACTIVE);
		final List<Long> existingActiveSitelinkIds = getIds(activeSitelinksExtensions);
		logger.info("Existing Active SiteLink Ids for AccountID [" + accountID + "] and CampaignID [" + campaignID + "] which we'll remove from Google as part of the refresh: [" + existingActiveSitelinkIds + "]");		
		final List<CampaignAdExtensionOperation> removeOperationList = getRemoveSiteLinksOperations(campaignID, existingActiveSitelinkIds);		
		final List<GoogleSiteLink> siteLinks = request.getSiteLinks();
		final CampaignAdExtensionOperation addOperation = getAddSiteLinksOperation(campaignID, siteLinks);
		final List<CampaignAdExtensionOperation> combinedOperations = new ArrayList<CampaignAdExtensionOperation>();
		if (removeOperationList.isEmpty())
		{
			logger.info("No active SiteLinks found in Google for AccountID [" + accountID + "] and CampaignID [" + campaignID + "], so won't remove any old SiteLinks");
		}
		else
		{
			combinedOperations.addAll(removeOperationList);
		}
		if (addOperation == null)
		{
			logger.info("No new SiteLinks to add, so will only remove old ones (if any exist) as part of the refresh");
		}
		else
		{
			combinedOperations.add(addOperation);
		}
		if (combinedOperations.isEmpty())
		{
			logger.info("No remove or add operations to perform because found no existing active siteLinks and no new ones were specified");
			return true;
		}		
		final AdWordsUser user = new AdWordsUser(email, password, accountID, userAgent, developerToken, useSandbox);
		final CampaignAdExtensionServiceInterface campaignAdExtensionService = user.getService(AdWordsService.V201109_1.CAMPAIGN_AD_EXTENSION_SERVICE);
		final CampaignAdExtensionOperation[] operations = combinedOperations.toArray(new CampaignAdExtensionOperation[combinedOperations.size()]);		
		final CampaignAdExtensionRetriableGoogleOperation retriableOperation = new CampaignAdExtensionRetriableGoogleOperation(campaignAdExtensionService, operations, SemplestUtils.DEFAULT_RETRY_COUNT);		
		final CampaignAdExtensionReturnValue result = retriableOperation.performOperation();
		if (result != null && result.getValue() != null)
		{
			for (CampaignAdExtension campaignAdExtensionResult : result.getValue())
			{
				final Long campaignAdExtensionCampaignId = campaignAdExtensionResult.getCampaignId();
				final CampaignAdExtensionStatus campaignAdExtensionStatus = campaignAdExtensionResult.getStatus();
				final AdExtension adExtention = campaignAdExtensionResult.getAdExtension();
				final Long adExtentionId = adExtention.getId();
				final String adExtensionType = adExtention.getAdExtensionType();
				logger.info("After the operation, current status for SiteLink Extension for CampaignID [" + campaignAdExtensionCampaignId + "], AdExtensionID [" + adExtentionId + "], AdExtensionType [" + adExtensionType + "]: [" + campaignAdExtensionStatus + "]");
			}
		}
		else
		{
			final String errMsg = "Problem refreshing SiteLinks for request [" + request + "]";
			logger.error(errMsg);
			throw new Exception(errMsg);
		}
		return true;
	}

	

}
