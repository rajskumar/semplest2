package semplest.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.axis.types.UnsignedByte;

import semplest.server.encryption.AESBouncyCastle;
import semplest.server.protocol.CustomerHierarchy;
import semplest.server.protocol.CustomerType;
import semplest.server.protocol.EmailTemplate;
import semplest.server.protocol.KeywordIdRemoveOppositePair;
import semplest.server.protocol.RegistrationLinkDecryptedInfo;
import semplest.server.protocol.ProtocolEnum.AdEngine;
import semplest.server.protocol.chaseorbitalgateway.CustomerObject;
import semplest.server.protocol.google.GoogleViolation;

import com.google.api.adwords.v201109.cm.ApiError;
import com.google.api.adwords.v201109.cm.ApiException;
import com.google.api.adwords.v201109.cm.PolicyViolationError;
import com.google.api.adwords.v201109.cm.PolicyViolationKey;
import com.google.api.adwords.v201109.cm.TextAd;
import com.google.gson.reflect.TypeToken;
import com.microsoft.adcenter.api.customermanagement.SignupCustomerResponse;
import com.microsoft.adcenter.api.customermanagement.Entities.Account;
import com.microsoft.adcenter.api.customermanagement.Entities.AccountFinancialStatus;
import com.microsoft.adcenter.api.customermanagement.Entities.AccountLifeCycleStatus;
import com.microsoft.adcenter.api.customermanagement.Entities.AccountType;
import com.microsoft.adcenter.api.customermanagement.Entities.Address;
import com.microsoft.adcenter.api.customermanagement.Entities.ApplicationType;
import com.microsoft.adcenter.api.customermanagement.Entities.ContactInfo;
import com.microsoft.adcenter.api.customermanagement.Entities.CurrencyType;
import com.microsoft.adcenter.api.customermanagement.Entities.Customer;
import com.microsoft.adcenter.api.customermanagement.Entities.CustomerFinancialStatus;
import com.microsoft.adcenter.api.customermanagement.Entities.CustomerLifeCycleStatus;
import com.microsoft.adcenter.api.customermanagement.Entities.EmailFormat;
import com.microsoft.adcenter.api.customermanagement.Entities.Industry;
import com.microsoft.adcenter.api.customermanagement.Entities.LCID;
import com.microsoft.adcenter.api.customermanagement.Entities.LanguageType;
import com.microsoft.adcenter.api.customermanagement.Entities.PaymentMethodType;
import com.microsoft.adcenter.api.customermanagement.Entities.PersonName;
import com.microsoft.adcenter.api.customermanagement.Entities.ServiceLevel;
import com.microsoft.adcenter.api.customermanagement.Entities.TimeZoneType;
import com.microsoft.adcenter.api.customermanagement.Entities.User;
import com.microsoft.adcenter.api.customermanagement.Entities.UserLifeCycleStatus;
import com.microsoft.adcenter.v8.AdGroup;
import com.microsoft.adcenter.v8.AdGroupStatus;
import com.microsoft.adcenter.v8.AddTargetsToLibraryRequest;
import com.microsoft.adcenter.v8.AgeRange;
import com.microsoft.adcenter.v8.AgeTarget;
import com.microsoft.adcenter.v8.AgeTargetBid;
import com.microsoft.adcenter.v8.Bid;
import com.microsoft.adcenter.v8.BiddingModel;
import com.microsoft.adcenter.v8.BudgetLimitType;
import com.microsoft.adcenter.v8.BusinessTarget;
import com.microsoft.adcenter.v8.BusinessTargetBid;
import com.microsoft.adcenter.v8.Campaign;
import com.microsoft.adcenter.v8.CampaignStatus;
import com.microsoft.adcenter.v8.CityTarget;
import com.microsoft.adcenter.v8.CityTargetBid;
import com.microsoft.adcenter.v8.CountryTarget;
import com.microsoft.adcenter.v8.CountryTargetBid;
import com.microsoft.adcenter.v8.Day;
import com.microsoft.adcenter.v8.DayTarget;
import com.microsoft.adcenter.v8.DayTargetBid;
import com.microsoft.adcenter.v8.DeviceTarget;
import com.microsoft.adcenter.v8.DeviceType;
import com.microsoft.adcenter.v8.GenderTarget;
import com.microsoft.adcenter.v8.GenderTargetBid;
import com.microsoft.adcenter.v8.GenderType;
import com.microsoft.adcenter.v8.HourRange;
import com.microsoft.adcenter.v8.HourTarget;
import com.microsoft.adcenter.v8.HourTargetBid;
import com.microsoft.adcenter.v8.IncrementalBidPercentage;
import com.microsoft.adcenter.v8.LocationTarget;
import com.microsoft.adcenter.v8.MetroAreaTarget;
import com.microsoft.adcenter.v8.MetroAreaTargetBid;
import com.microsoft.adcenter.v8.Network;
import com.microsoft.adcenter.v8.PricingModel;
import com.microsoft.adcenter.v8.PublisherCountry;
import com.microsoft.adcenter.v8.RadiusTarget;
import com.microsoft.adcenter.v8.RadiusTargetBid;
import com.microsoft.adcenter.v8.StateTarget;
import com.microsoft.adcenter.v8.StateTargetBid;
import com.microsoft.adcenter.v8.Target;

public final class SemplestUtils
{
	public static final DateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DATE_FORMAT_YYYYMMDD_HHmmss = new SimpleDateFormat("yyyyMMdd HHmmss");
	public static final DateFormat DATE_FORMAT_YYYYMMDD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	public static final Long GOOGLE_MONEY_UNIT = 1000000L;
	public static final Double MICRO_AMOUNT_FACTOR = 1000000d;
	public static final Long DOUBLE_TO_LONG_MICRO_DIVISOR_HELPER = 10000L;
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final Type TYPE_LIST_OF_STRINGS = new TypeToken<List<String>>(){}.getType();
	public static final Type TYPE_LIST_OF_INTEGERS = new TypeToken<List<Integer>>(){}.getType();
	public static final Type TYPE_LIST_OF_LONGS = new TypeToken<List<Long>>(){}.getType();
	public static final Type TYPE_LIST_OF_CUSTOMER_OBJECTS = new TypeToken<List<CustomerObject>>(){}.getType();
	public static final Type TYPE_MAP_OF_STRING_TO_STRING = new TypeToken<Map<String, String>>(){}.getType();
	public static final Type TYPE_LIST_OF_GOOGLE_VIOLATIONS = new TypeToken<List<GoogleViolation>>(){}.getType();
	public static final Type TYPE_MAP_OF_ADENGINE_TO_DOUBLE = new TypeToken<Map<AdEngine, Double>>(){}.getType();
	public static final Type TYPE_LIST_OF_KEYWORD_ID_REMOVE_OPPOSITE_PAIRS = new TypeToken<List<KeywordIdRemoveOppositePair>>(){}.getType();	
	public static final List<Character> ALLOWED_CHARS = Arrays.asList('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','-');
	public static final Character SUBSTITUTION_CHARACTER = '-';
	public static final Integer USER_NAME_MIN_LENGTH = 6;
	public static final Integer USER_NAME_MAX_LENGTH = 20;
	public static final Double MSN_DEFAULT_BID_AMOUNT = 0.05;
	public static final Long MSN_DEFAULT_BID_MIRCOAMOUNT = 50000L ;
	public static final Double GOOGLE_DEFAULT_BID_AMOUNT = null;
	public static final Random RANDOM = new Random();
	public static final Integer SECOND = 1000;
	public static final Integer MINUTE = 60 * SECOND;
	public static final Integer HOUR = 60 * MINUTE;
	public static final Integer DAY = 24 * HOUR;
	public static final Integer DEFAULT_API_SLEEP_SECS = 30;
	public static final Integer DEFAULT_RETRY_COUNT = 10;
	public static final String STATUS_GOOD = "STATUS GOOD";
	public static final String VALUE_DELIMITER = "=";
	public static final String TOKEN_DELIMITER = "|";	
	public static final String USER_ID = "USER_ID";
	public static final String DATE_TIME = "DATE_TIME";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASSWORD = "PASSWORD";
	
	public static Integer getDiffInDays(final java.util.Date date1, java.util.Date date2)
	{
		final long date1Millis = date1.getTime();
		final long date2Millis = date2.getTime();
		final long differenceMillis = date2Millis - date1Millis;
		final Long daysDifference = differenceMillis / DAY;
		return daysDifference.intValue();
	}
	
	public static CustomerType getCustomerType(final CustomerHierarchy hierarchy) throws Exception
	{		
		final Integer customerID = hierarchy.getCustsomerID();
		if (customerID == null)
		{
			throw new Exception("The given CUstomer Hierarchy has a null CustomerID, which is illegal: " + hierarchy);
		}
		final Integer parentID = hierarchy.getParentCustomerID();
		if (customerID.equals(parentID))
		{
			return CustomerType.SINGLE_USER;
		}
		if (parentID == null || parentID == 0)
		{
			return CustomerType.PARENT;
		}
		return CustomerType.CHILD;
	}
	
	public static EmailTemplate getEmailTemplateForCustomer(final Integer customerID, final List<EmailTemplate> emailTemplates)
	{
		for (final EmailTemplate emailTemplate : emailTemplates)
		{
			final Integer emailTemplateaCustomerID = emailTemplate.getCustomerFK();
			if (emailTemplateaCustomerID != null && emailTemplateaCustomerID.equals(customerID))
			{
				return emailTemplate;
			}			
		}
		return null;
	}
	
	public static EmailTemplate getEmailTemplateForNoCustomer(final List<EmailTemplate> emailTemplates)
	{
		for (final EmailTemplate emailTemplate : emailTemplates)
		{
			final Integer emailTemplateCustomerID = emailTemplate.getCustomerFK();
			if (emailTemplateCustomerID == null)
			{
				return emailTemplate;
			}
		}
		return null;
	}
	
	public static EmailTemplate getEmailTemplateForUser(final semplest.server.protocol.User user, final List<EmailTemplate> emailTemplates)
	{
		final Integer customerFK = user.getCustomerFk();
		final EmailTemplate emailTemplateForCustomer = getEmailTemplateForCustomer(customerFK, emailTemplates);
		if (emailTemplateForCustomer != null)
		{
			return emailTemplateForCustomer;
		}
		final EmailTemplate emailTemplate = getEmailTemplateForNoCustomer(emailTemplates);		
		return emailTemplate;
	}
	
	public static RegistrationLinkDecryptedInfo getDecryptedInfo(final AESBouncyCastle aes, final String encryptedToken) throws Exception
	{
		final String decryptedToken;
		try
		{
			decryptedToken = aes.decrypt(encryptedToken);			
		}
		catch (Exception e)
		{
			throw new Exception("Could not decrypt token [" + encryptedToken + "]");
		}
		try
		{
			final RegistrationLinkDecryptedInfo decryptedInfo = getDecryptedInfoFromDecryptedString(decryptedToken);
			return decryptedInfo;
		}
		catch (Exception e)
		{
			throw new Exception("Could not decrypt token [" + encryptedToken + "]");
		}
	}
	
	public static String generateEncryptedLink(final AESBouncyCastle aes, final String reminderEmailUrlPrefix, final Integer userID, final java.util.Date dateTime, final String username, final String password)
	{
		final String encryptedToken = generateEncryptedToken(aes, userID, dateTime, username, password);
		final String encryptedUrl = reminderEmailUrlPrefix + encryptedToken;
		return encryptedUrl;
	}
	
	public static String generateEncryptedToken(final AESBouncyCastle aes, final Integer userID, final java.util.Date dateTime, final String username, final String password)
	{
		final String unencryptedToken = getConcatenatedRegistrationString(userID, dateTime, username, password);
		final String encryptedToken = aes.encrypt(unencryptedToken);
		return encryptedToken;
	}
	
	public static RegistrationLinkDecryptedInfo getDecryptedInfoFromDecryptedString(final String s) throws Exception
	{		
		final String[] tokens = s.split("\\" + SemplestUtils.TOKEN_DELIMITER);
		if (tokens.length != 4)
		{
			throw new Exception("Expecting 4 tokens but got " + tokens.length + " within String [" + s + "] separated by [" + SemplestUtils.TOKEN_DELIMITER + "]: " + SemplestUtils.USER_ID + ", " + SemplestUtils.DATE_TIME + ", " + SemplestUtils.USER_NAME + ", " + SemplestUtils.PASSWORD);
		}
		final String userIdToken = tokens[0];
		final String dateTimeToken = tokens[1];
		final String usernameToken = tokens[2];
		final String passwordToken = tokens[3];
		final String[] userIdNameValue = userIdToken.split(SemplestUtils.VALUE_DELIMITER);
		if (userIdNameValue.length != 2)
		{
			throw new Exception("Expecting 2 items within User String [" + userIdNameValue + "] separated by [" + SemplestUtils.VALUE_DELIMITER + "]");
		}
		final String[] dateTimeNameValue = dateTimeToken.split(SemplestUtils.VALUE_DELIMITER);
		if (dateTimeNameValue.length != 2)
		{
			throw new Exception("Expecting 2 items within DateTime String [" + dateTimeNameValue + "] separated by [" + SemplestUtils.VALUE_DELIMITER + "]");
		}
		final String[] userNameNameValue = usernameToken.split(SemplestUtils.VALUE_DELIMITER);
		if (userNameNameValue.length != 2)
		{
			throw new Exception("Expecting 2 items within Username String [" + userNameNameValue + "] separated by [" + SemplestUtils.VALUE_DELIMITER + "]");
		}
		final String[] passwordNameValue = passwordToken.split(SemplestUtils.VALUE_DELIMITER);
		if (passwordNameValue.length != 2)
		{
			throw new Exception("Expecting 2 items within Password String [" + passwordNameValue + "] separated by [" + SemplestUtils.VALUE_DELIMITER + "]");
		}
		final String userIdName = userIdNameValue[0];
		final String userIdValue = userIdNameValue[1];		
		final String dateTimeName = dateTimeNameValue[0];
		final String dateTimeValue = dateTimeNameValue[1];		
		final String usernameName = userNameNameValue[0];
		final String usernameValue = userNameNameValue[1];		
		final String passwordName = passwordNameValue[0];
		final String passwordValue = passwordNameValue[1];
		if (!SemplestUtils.USER_ID.equals(userIdName))
		{
			throw new Exception("UserID token name expected is [" + SemplestUtils.USER_ID + "], but encountered [" + userIdName + "]");
		}
		if (!SemplestUtils.DATE_TIME.equals(dateTimeName))
		{
			throw new Exception("DateTime token name expected is [" + SemplestUtils.DATE_TIME + "], but encountered [" + dateTimeName + "]");
		}
		if (!SemplestUtils.USER_NAME.equals(usernameName))
		{
			throw new Exception("Username token name expected is [" + SemplestUtils.USER_NAME + "], but encountered [" + usernameName + "]");
		}
		if (!SemplestUtils.PASSWORD.equals(passwordName))
		{
			throw new Exception("Password token name expected is [" + SemplestUtils.PASSWORD + "], but encountered [" + passwordName + "]");
		}
		final Integer userID = Integer.parseInt(userIdValue);
		final java.util.Date dateTime = SemplestUtils.DATE_FORMAT_YYYYMMDD_HH_MM_SS.parse(dateTimeValue);
		final RegistrationLinkDecryptedInfo descryptedInfo = new RegistrationLinkDecryptedInfo(userID, dateTime, usernameValue, passwordValue);
		return descryptedInfo;
	}
			
	public static String getConcatenatedRegistrationString(final Integer userID, final java.util.Date dateTime, final String username, final String password)
	{
		final StringBuilder sb = new StringBuilder();
		final String dateTimeString = SemplestUtils.DATE_FORMAT_YYYYMMDD_HH_MM_SS.format(dateTime);
		sb.append(SemplestUtils.USER_ID).append(SemplestUtils.VALUE_DELIMITER).append(userID).append(SemplestUtils.TOKEN_DELIMITER);
		sb.append(SemplestUtils.DATE_TIME).append(SemplestUtils.VALUE_DELIMITER).append(dateTimeString).append(SemplestUtils.TOKEN_DELIMITER);
		sb.append(SemplestUtils.USER_NAME).append(SemplestUtils.VALUE_DELIMITER).append(username).append(SemplestUtils.TOKEN_DELIMITER);
		sb.append(SemplestUtils.PASSWORD).append(SemplestUtils.VALUE_DELIMITER).append(password);
		return sb.toString();
	}
	
	public static AESBouncyCastle getDefaultAESBouncyCastle(final String semplestEncryptionKey) throws Exception
	{		
		final AESBouncyCastle aes = AESBouncyCastle.getInstance(semplestEncryptionKey);
		return aes;
	}
		
	public static Long getLongMicroAmount(final Double d)
	{
		final Double dMicroDouble = d * SemplestUtils.MICRO_AMOUNT_FACTOR;
		return dMicroDouble.longValue() / SemplestUtils.DOUBLE_TO_LONG_MICRO_DIVISOR_HELPER * SemplestUtils.DOUBLE_TO_LONG_MICRO_DIVISOR_HELPER;
	}
	
	public static List<GoogleViolation> getGoogleViolations_v201109(ApiException e)
	{
		final List<GoogleViolation> violations = new ArrayList<GoogleViolation>();
		final ApiError[] errors = e.getErrors();
		if (errors != null)
		{
			for (ApiError error : errors)
			{
				final GoogleViolation violation;
	
				final String errorType = error.getApiErrorType();
				final String errorMessage = error.getErrorString();
				final String fieldPath = error.getFieldPath();
				final String shortFieldPath;
				if (fieldPath != null && fieldPath.contains("."))
				{
					final int lastIndexOfDot = fieldPath.lastIndexOf(".");
					shortFieldPath = fieldPath.substring(lastIndexOfDot + 1);
				}
				else
				{
					shortFieldPath = fieldPath;
				}
				if (error instanceof PolicyViolationError)
				{
					final PolicyViolationError policyError = (PolicyViolationError) error;
					final String externalPolicyName = policyError.getExternalPolicyName();
					final String policyDescription = policyError.getExternalPolicyDescription();
					final PolicyViolationKey policyViolationKey = policyError.getKey();
					final String policyViolatingText = policyViolationKey.getViolatingText();
					violation = new GoogleViolation(errorType, errorMessage, fieldPath, shortFieldPath, externalPolicyName, policyDescription, policyViolatingText);
				}
				else
				{
					violation = new GoogleViolation(errorType, errorMessage, fieldPath, shortFieldPath);
				}
				violations.add(violation);
			}
		}
		return violations;
	}
	
	public static List<GoogleViolation> getGoogleViolations_v201109_1(com.google.api.adwords.v201109_1.cm.ApiException e)
	{
		final List<GoogleViolation> violations = new ArrayList<GoogleViolation>();
		final com.google.api.adwords.v201109_1.cm.ApiError[] errors = e.getErrors();
		for (com.google.api.adwords.v201109_1.cm.ApiError error : errors)
		{
			final GoogleViolation violation;

			final String errorType = error.getApiErrorType();
			final String errorMessage = error.getErrorString();
			final String fieldPath = error.getFieldPath();
			final String shortFieldPath;
			if (fieldPath != null && fieldPath.contains("."))
			{
				final int lastIndexOfDot = fieldPath.lastIndexOf(".");
				shortFieldPath = fieldPath.substring(lastIndexOfDot + 1);
			}
			else
			{
				shortFieldPath = fieldPath;
			}
			if (error instanceof com.google.api.adwords.v201109_1.cm.PolicyViolationError)
			{
				final com.google.api.adwords.v201109_1.cm.PolicyViolationError policyError = (com.google.api.adwords.v201109_1.cm.PolicyViolationError) error;
				final String externalPolicyName = policyError.getExternalPolicyName();
				final String policyDescription = policyError.getExternalPolicyDescription();
				final com.google.api.adwords.v201109_1.cm.PolicyViolationKey policyViolationKey = policyError.getKey();
				final String policyViolatingText = policyViolationKey.getViolatingText();
				violation = new GoogleViolation(errorType, errorMessage, fieldPath, shortFieldPath, externalPolicyName, policyDescription, policyViolatingText);
			}
			else
			{
				violation = new GoogleViolation(errorType, errorMessage, fieldPath, shortFieldPath);
			}
			violations.add(violation);
		}
		return violations;
	}
	
	public static <T, S> List<Map<T, S>> getBatches(final Map<T, S> map, final int batchSize)
	{
		if (batchSize < 1)
		{
			throw new IllegalArgumentException("BatchSize [" + batchSize + "] cannot be less than 1");
		}
		final List<Map<T, S>> batches = new ArrayList<Map<T, S>>();
		if (map == null)
		{
			return batches;
		}
		Map<T, S> currentBatch = new HashMap<T, S>();
		for (final Entry<T, S> entry : map.entrySet())
		{
			currentBatch.put(entry.getKey(), entry.getValue());
			if (currentBatch.size() == batchSize)
			{
				batches.add(currentBatch);
				currentBatch = new HashMap<T, S>();
			}
		}
		if (!currentBatch.isEmpty())
		{
			batches.add(currentBatch);
		}
		return batches;
	}
	
	public static <T> List<List<T>> getBatches(final List<T> list, final int batchSize)
	{
		if (batchSize < 1)
		{
			throw new IllegalArgumentException("BatchSize [" + batchSize + "] cannot be less than 1");
		}
		final List<List<T>> batches = new ArrayList<List<T>>();
		if (list == null)
		{
			return batches;
		}
		List<T> currentBatch = new ArrayList<T>();
		for (final T o : list)
		{
			currentBatch.add(o);
			if (currentBatch.size() == batchSize)
			{
				batches.add(currentBatch);
				currentBatch = new ArrayList<T>();
			}
		}
		if (!currentBatch.isEmpty())
		{
			batches.add(currentBatch);
		}
		return batches;
	}
		
	public static String getMsnAgeRangeString(final AgeRange ageRange)
	{
		if (ageRange == null)
		{
			return null;
		}
		final String ageRangeValue = ageRange.getValue();
		return ageRangeValue;
	}
	
	public static String getMsnIncrementalBidPercentageString(final IncrementalBidPercentage incrementalBidPercentage)
	{
		if (incrementalBidPercentage == null)
		{
			return "";
		}
		final String incrementalBidPercentageValue = incrementalBidPercentage.getValue();		
		return incrementalBidPercentageValue;
	}
	
	public static String getMsnAgeTargetBidString(final AgeTargetBid ageTargetBid)
	{
		if (ageTargetBid == null)
		{
			return "";
		}
		final AgeRange ageRange = ageTargetBid.getAge();
		final IncrementalBidPercentage incrementalBidPercentage = ageTargetBid.getIncrementalBid();		
		final String ageRangeString = getMsnAgeRangeString(ageRange);
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);		
		return "AgeTargetBid[ageRange=" + ageRangeString + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnAgeTargetString(final AgeTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final AgeTargetBid[] ageTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final AgeTargetBid ageTargetBid : ageTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnAgeTargetBidString(ageTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnDayString(final Day day)
	{
		if (day == null)
		{
			return "";
		}
		final String dayValue = day.getValue();
		return dayValue;
	}
	
	public static String getMsnDayTargetBidString(final DayTargetBid dayTargetBid)
	{
		if (dayTargetBid == null)
		{
			return "";
		}
		final Day day = dayTargetBid.getDay();
		final IncrementalBidPercentage incrementalBidPercentage = dayTargetBid.getIncrementalBid();		
		final String dayString = getMsnDayString(day);
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);		
		return "DayTargetBid[day=" + dayString + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnDayTargetString(final DayTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final DayTargetBid[] dayTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final DayTargetBid dayTargetBid : dayTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnDayTargetBidString(dayTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnDeviceTargetString(final DeviceTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final DeviceType[] devices = target.getDevices();
		final StringBuilder sb = new StringBuilder();
		for (final DeviceType device : devices)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(device);
		}
		return sb.toString();
	}
	
	public static String getMsnGenderTargetBidString(final GenderTargetBid genderTargetBid)
	{
		if (genderTargetBid == null)
		{
			return "";
		}
		final GenderType gender = genderTargetBid.getGender();
		final IncrementalBidPercentage incrementalBidPercentage = genderTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "GenderTargetBid[gender=" + gender + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnGenderTargetString(final GenderTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final GenderTargetBid[] genderTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final GenderTargetBid genderTargetBid : genderTargetBids)
		{			
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnGenderTargetBidString(genderTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnHourRangeString(final HourRange hourRange)
	{
		if (hourRange == null)
		{
			return "";
		}
		final String hourRangeValue = hourRange.getValue();
		return hourRangeValue;
	}
	
	public static String getMsnHourTargetBidString(final HourTargetBid hourTargetBid)
	{
		if (hourTargetBid == null)
		{
			return "";
		}
		final HourRange hourRange = hourTargetBid.getHour();
		final IncrementalBidPercentage incrementalBidPercentage = hourTargetBid.getIncrementalBid();
		final String hourRangeString = getMsnHourRangeString(hourRange);
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "HourTargetBid[hourRange=" + hourRangeString + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnHourTargetString(final HourTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final HourTargetBid[] hourTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final HourTargetBid hourTargetBid : hourTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnHourTargetBidString(hourTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnBusinessTargetString(BusinessTargetBid businessTargetBid)
	{
		if (businessTargetBid == null)
		{
			return "";
		}
		final Long businessId = businessTargetBid.getBusinessId();
		final IncrementalBidPercentage incrementalBidPercentage = businessTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);		final int radius = businessTargetBid.getRadius();
		return "BusinessTargetBid[businessId=" + businessId + ",radius=" + radius + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnBusinessTargetString(BusinessTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final BusinessTargetBid[] businessTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final BusinessTargetBid businessTargetBid : businessTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnBusinessTargetString(businessTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnCityTargetBidString(CityTargetBid cityTargetBid)
	{
		if (cityTargetBid == null)
		{
			return "";
		}
		final String city = cityTargetBid.getCity();
		final IncrementalBidPercentage incrementalBidPercentage = cityTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "CityTargetBid[city=" + city + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnCityTargetString(CityTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final CityTargetBid[] cityTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final CityTargetBid cityTargetBid : cityTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnCityTargetBidString(cityTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnCountryTargetBidString(CountryTargetBid countryTargetBid)
	{
		if (countryTargetBid == null)
		{
			return "";
		}
		final String countryAndRegion = countryTargetBid.getCountryAndRegion();
		final IncrementalBidPercentage incrementalBidPercentage = countryTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "CountryTargetBid[countryAndRegion=" + countryAndRegion + ",incrementalBidPercentageString=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnCountryTargetString(CountryTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final CountryTargetBid[] countryTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final CountryTargetBid countryTargetBid : countryTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnCountryTargetBidString(countryTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnMetroAreaTargetBidString(final MetroAreaTargetBid metroTargetBid)
	{
		if (metroTargetBid == null)
		{
			return "";
		}
		final String metroArea = metroTargetBid.getMetroArea();
		final IncrementalBidPercentage incrementalBidPercentage = metroTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "MetroAreaTargetBid[metroArea=" + metroArea + ",incrementalBidPercentageString=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnMetroAreaTargetString(final MetroAreaTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final MetroAreaTargetBid[] metroAreaTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final MetroAreaTargetBid metroTargetBid : metroAreaTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnMetroAreaTargetBidString(metroTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnRadiusTargetBidString(final RadiusTargetBid radiusTargetBid)
	{
		if (radiusTargetBid == null)
		{
			return "";
		}
		final Long id = radiusTargetBid.getId();
		final String name = radiusTargetBid.getName();
		final Double latitudeDegrees = radiusTargetBid.getLatitudeDegrees();
		final Double longitudeDegrees = radiusTargetBid.getLongitudeDegrees();
		final Integer radius = radiusTargetBid.getRadius();		
		final IncrementalBidPercentage incrementalBidPercentage = radiusTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "RadiusTargetBid[id=" + id + ",name=" + name + ",radius=" + radius +",latitudeDegrees=" + latitudeDegrees +",longitudeDegrees=" + longitudeDegrees + ",incrementalBidPercentageString=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnRadiusTargetString(RadiusTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final RadiusTargetBid[] radiusTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final RadiusTargetBid radiusTargetBid : radiusTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnRadiusTargetBidString(radiusTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnStateTargetBidString(StateTargetBid stateTargetBid)
	{
		if (stateTargetBid == null)
		{
			return "";
		}
		final String state = stateTargetBid.getState();
		final IncrementalBidPercentage incrementalBidPercentage = stateTargetBid.getIncrementalBid();
		final String incrementalBidPercentageString = getMsnIncrementalBidPercentageString(incrementalBidPercentage);
		return "StateTargetBid[state=" + state + ",incrementalBidPercentage=" + incrementalBidPercentageString + "]";
	}
	
	public static String getMsnStateTargetString(StateTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final StateTargetBid[] stateTargetBids = target.getBids();
		final StringBuilder sb = new StringBuilder();
		for (final StateTargetBid stateTargetBid : stateTargetBids)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnStateTargetBidString(stateTargetBid));
		}
		return sb.toString();
	}
	
	public static String getMsnLocationTargetString(final LocationTarget target)
	{
		if (target == null)
		{
			return "";
		}
		final BusinessTarget businessTarget = target.getBusinessTarget();
		final CityTarget cityTarget = target.getCityTarget();
		final CountryTarget countryTarget = target.getCountryTarget();
		final MetroAreaTarget metroAreaTarget = target.getMetroAreaTarget();
		final RadiusTarget radiusTarget = target.getRadiusTarget();
		final StateTarget stateTarget = target.getStateTarget();		
		final Boolean hasPhysicalIntent = target.getHasPhysicalIntent();
		final Boolean targetAllLocations = target.getTargetAllLocations();		
		final String businessTargetString = getMsnBusinessTargetString(businessTarget);		
		final String cityTargetString = getMsnCityTargetString(cityTarget);
		final String countryTargetString = getMsnCountryTargetString(countryTarget);
		final String metroAreaTargetString = getMsnMetroAreaTargetString(metroAreaTarget);
		final String radiusTargetString = getMsnRadiusTargetString(radiusTarget);
		final String stateTargetString = getMsnStateTargetString(stateTarget);		
		return "LocationTarget[hasPhysicalIntent=" + hasPhysicalIntent + ",targetAllLocations=" + targetAllLocations + ",businessTarget=" + businessTargetString + ",cityTarget=" + cityTargetString + ",countryTarget=" + countryTargetString + ",metroAreaTarget=" + metroAreaTargetString + ",metroAreaTarget=" + metroAreaTargetString + ",radiusTarget=" + radiusTargetString + ",stateTarget=" + stateTargetString + "]";
	}
	
	public static String getMsnTargetString(final Target target)
	{
		if (target == null)
		{
			return "";
		}
		final AgeTarget ageTarget = target.getAge();		
		final DayTarget dayTarget = target.getDay();		
		final DeviceTarget deviceTarget = target.getDevice();
		final GenderTarget genderTarget = target.getGender();
		final HourTarget hourTarget = target.getHour();
		final LocationTarget locationTarget = target.getLocation();		
		final String ageTargetString = getMsnAgeTargetString(ageTarget);
		final String dayTargetString = getMsnDayTargetString(dayTarget);
		final String deviceTargetString = getMsnDeviceTargetString(deviceTarget);
		final String genderTargetString = getMsnGenderTargetString(genderTarget);
		final String hourTargetString = getMsnHourTargetString(hourTarget);
		final String locationTargetString = getMsnLocationTargetString(locationTarget);
		final Long id = target.getId();
		final Boolean isLibraryTarget = target.getIsLibraryTarget();		
		final String name = target.getName();
		return "Target[id=" + id + ",name=" + name + ",isLibraryTarget=" + isLibraryTarget + ",ageTarget=" + ageTargetString + ",dayTarget=" + dayTargetString + ",deviceTarget=" + deviceTargetString + ",genderTarget=" + genderTargetString + ",hourTarget=" + hourTargetString + ",locationTarget=" + locationTargetString + "]";
	}
	
	public static String getMsnAddTargetsToLibraryRequestString(AddTargetsToLibraryRequest request)
	{
		if (request == null)
		{
			return "";
		}
		final Target[] targets = request.getTargets();
		final StringBuilder sb = new StringBuilder();
		for (final Target target : targets)
		{
			if (sb.length() != 0)
			{
				sb.append(",");
			}
			sb.append(getMsnTargetString(target));
		}
		
		return "AddTargetsToLibraryRequest[tagets=" + targets + "]";		
	}
	
	public static String getMsnCustomerResponseString(SignupCustomerResponse signupCustomerResponse)
	{
		if (signupCustomerResponse == null)
		{
			return "";
		}
		final Long accountId = signupCustomerResponse.getAccountId();
		final String accountNumber = signupCustomerResponse.getAccountNumber();
		final Long customerId = signupCustomerResponse.getCustomerId();
		final String customerNumber = signupCustomerResponse.getCustomerNumber();
		final Long userId = signupCustomerResponse.getUserId();
		return "SignupCustomerResponse[AccountID [" + accountId + "], AccountNumber [" + accountNumber + "], CustomerID [" + customerId + "], CustomerNumber [" + customerNumber + "], UserID [" + userId + "]]";
	}
	
	public static Integer getRandomIntBetween0AndSpecifiedInt(final int max)
	{
		return RANDOM.nextInt(max);
	}
	
	public static String getRandomAllowedChars(final int lengthGap, List<Character> allowedChars)
	{
		final StringBuilder sb = new StringBuilder();
		final int numAllowedPossibleChars = allowedChars.size();
		for (int i = 0; i < lengthGap; ++i)
		{
			final int randomIndex = RANDOM.nextInt(numAllowedPossibleChars);
			final Character c = allowedChars.get(randomIndex);
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static final String getStringOfSpecifiedLength(final String s, final int minLength, final int maxLength)
	{			
		if (s.length() < minLength)
		{
			final int lengthGap = minLength - s.length();
			final String randomCharsOfLengthGap = getRandomAllowedChars(lengthGap, ALLOWED_CHARS);
			final String result = s + randomCharsOfLengthGap;
			return result;
		}
		else if (s.length() > maxLength)
		{
			final int lengthGap = s.length() - maxLength;
			final String result = s.substring(0, s.length() - lengthGap);
			return result;
		}
		else
		{
			return s;
		}
	}
	
	public static final String getLegalUserName(final String rawUserName)
	{			
		final String sanitizedRawString = sanitizeString(rawUserName);
		return getStringOfSpecifiedLength(sanitizedRawString, USER_NAME_MIN_LENGTH, USER_NAME_MAX_LENGTH);
	}
	
	public static final String sanitizeString(final String s)
	{			
		if ("".equals(getTrimmedNonNullString(s)))
		{
			return "";
		}
	    final char[] charArray = s.trim().toCharArray();
	    final StringBuilder result = new StringBuilder();
	    for (char c : charArray)
	    {
	    	if (ALLOWED_CHARS.contains(c))
	    	{
	    		result.append(c);
	    	}
	    	else
	    	{
	    		result.append(SUBSTITUTION_CHARACTER);
	    	}
	    }
	    return result.toString();
	}
	
	public static final String getMsnCampaignString(final Campaign campaign)
	{
		if (campaign == null)
		{
			return "";
		}
		final BudgetLimitType budgetType = campaign.getBudgetType();
		final Boolean conversionTrackingEnabled = campaign.getConversionTrackingEnabled();
		final Double dailyBudget = campaign.getDailyBudget();
		final Boolean daylightSaving = campaign.getDaylightSaving();
		final String description = campaign.getDescription();
		final Long campaignId = campaign.getId();
		final Double monthlyBudget = campaign.getMonthlyBudget();
		final String name = campaign.getName();
		final CampaignStatus campaignStatus = campaign.getStatus();
		final String timeZone = campaign.getTimeZone();
		return "Campaign[budgetType [" + budgetType + "], conversionTrackingEnabled [" + conversionTrackingEnabled + "], dailyBudget [" + dailyBudget + "], daylightSaving [" + daylightSaving + "], " +
				"description [" + description + "], campaignId [" + campaignId + "], monthlyBudget [" + monthlyBudget + "], name [" + name + "], campaignStatus [" + campaignStatus + "], timeZone [" + timeZone + "]]";
	}
	
	public static final String getMsnAccountString(final Account account)
	{
		if (account == null)
		{
			return "";
		}
		final AccountFinancialStatus accountFinancialStatus = account.getAccountFinancialStatus();
		final AccountLifeCycleStatus accountLifeCycleStatus = account.getAccountLifeCycleStatus();
		final AccountType accountType = account.getAccountType();
		final Long billToCustomerId = account.getBillToCustomerId();
		final String countryCode = account.getCountryCode();
		final CurrencyType currencyType = account.getCurrencyType();
		final Long id = account.getId();
		final LanguageType languageType = account.getLanguage();
		final Long lastModifiedByUserId = account.getLastModifiedByUserId();
		final Calendar lastModifiedTime = account.getLastModifiedTime();
		final String name = account.getName();
		final String number = account.getNumber();
		final UnsignedByte pausedReason = account.getPauseReason();
		final Long parentCustomerId = account.getParentCustomerId();
		final Long paymentMethodId = account.getPaymentMethodId();
		final PaymentMethodType paymentMethodType = account.getPaymentMethodType();
		final Long primaryUserId = account.getPrimaryUserId();
		
		final byte[] timestamp = account.getTimeStamp();
		final TimeZoneType timezone = account.getTimeZone();
		return "Account[id=" + id + ",accountFinancialStatus=" + accountFinancialStatus + ",accountLifeCycleStatus=" + accountLifeCycleStatus + ",accountType=" + accountType + ",billToCustomerId=" + billToCustomerId + 
				",countryCode=" + countryCode + ",currencyType=" + currencyType + ",languageType=" + languageType + ",lastModifiedByUserId=" + lastModifiedByUserId + ",pausedReason=" + pausedReason + ",lastModifiedTime=" + lastModifiedTime + 
				",name=" + name + ",number=" + number + ",parentCustomerId=" + parentCustomerId + ",paymentMethodId=" + paymentMethodId + ",paymentMethodType=" + paymentMethodType + ",primaryUserId=" + primaryUserId +",timestamp=" + timestamp + ",timezone=" + timezone + "]";
	}
	
	public static final String getMsnPersonNameString(final PersonName personName)
	{
		if (personName == null)
		{
			return "";
		}
		final String firstName = personName.getFirstName();
		final String middleName = personName.getMiddleInitial();
		final String lastName = personName.getLastName();
		return "PersonName[firstName=" + firstName + ",middleName=" + middleName + ",lastName=" + lastName + "]";
	}
	
	
	public static final String getMsnUserString(final User user)
	{
		if (user == null)
		{
			return "";
		}
		final ContactInfo contactInfo = user.getContactInfo();
		final ApplicationType applicationType = user.getCustomerAppScope();
		final Long customerId = user.getCustomerId();
		final Long id = user.getId();		
		final String jobTitle = user.getJobTitle();
		final LCID lcid = user.getLcid();
		final Long lastModifiedByUserId = user.getLastModifiedByUserId();
		final Calendar lastModifiedTime = user.getLastModifiedTime();
		final PersonName personName = user.getName();
		final String password = user.getPassword();
		final String secretQuestion = user.getSecretQuestion() == null ? "" : user.getSecretQuestion().getValue();
		final String secretAnswer = user.getSecretAnswer();		
		final UserLifeCycleStatus userLifeCycleStatus = user.getUserLifeCycleStatus();
		final String userName = user.getUserName();
		final byte[] timestamp = user.getTimeStamp();		
		final String contactInfoString = getMsnContactInfoString(contactInfo);
		final String personNameString = getMsnPersonNameString(personName);
		return "User[id=" + id + ",customerId=" + customerId + "," +
				"applicationType=" + applicationType + "," +
				"jobTitle=" + jobTitle + "," +
				"lcid=" + lcid + "," +
				"lastModifiedByUserId=" + lastModifiedByUserId + "," +
				"lastModifiedTime=" + lastModifiedTime + "," +
				"userName=" + userName + "," +
				"password=" + password + "," +
				"secretQuestion=" + secretQuestion + "," +
				"secretAnswer=" + secretAnswer + "," +
				"userLifeCycleStatus=" + userLifeCycleStatus + "," +
				"timestamp=" + timestamp + "," +
				"personName=" + personNameString + "," +
				"contactInfo=" + contactInfoString + "]";
	}
	
	public static final String getMsnContactInfoString(final ContactInfo contactInfo)
	{
		if (contactInfo == null)
		{
			return "";
		}
		final Address contactInfoAddress = contactInfo.getAddress();
		final Boolean contactByPhone = contactInfo.getContactByPhone();
		final Boolean contactByPostalMail = contactInfo.getContactByPostalMail();
		final String email = contactInfo.getEmail();
		final EmailFormat emailFormat = contactInfo.getEmailFormat();
		final String fax = contactInfo.getFax();
		final String homePhone = contactInfo.getHomePhone();
		final Long id = contactInfo.getId();
		final String mobile = contactInfo.getMobile();
		final String phone1 = contactInfo.getPhone1();
		final String phone2 = contactInfo.getPhone2();		
		final String addressString = getMsnAddressString(contactInfoAddress);
		return "ContactInfo[" + "id=" + id + ",contactByPhone=" + contactByPhone + ",contactByPostalMail=" + contactByPostalMail + ",email=" + email + ",emailFormat=" + emailFormat + ",homePhone=" + homePhone + ",mobile=" + mobile + ",phone1=" + phone1 + ",phone2=" + phone2 + ",fax=" + fax + ",address=" + addressString + "]";
	}
	
	public static final String getMsnAddressString(final Address address)
	{
		if (address == null)
		{
			return "";
		}
		final String city = address.getCity();
		final String country = address.getCountryCode();
		final Long customerAddressID = address.getId();
		final String addressLine1 = address.getLine1();
		final String addressLine2 = address.getLine2();
		final String addressLine3 = address.getLine3();
		final String addressLine4 = address.getLine4();		
		final String zip = address.getPostalCode();
		final String state = address.getStateOrProvince();
		final byte[] customerAddressTimestamp = address.getTimeStamp();
		return "Address[customerAddressID=[" + customerAddressID + "],addressLine1=[" + addressLine1 + "],addressLine2=[" + addressLine2 + "],addressLine3=[" + addressLine3 + "],addressLine4=[" + addressLine4 + "],city=[" + city + "],state=[" + state + "],zip=[" + zip + "],country=[" + country + "],customerAddressTimestamp=[" + customerAddressTimestamp + "]]";
	}
	
	public static final String getMsnPublisherCountryString(final PublisherCountry publisherCountry)
	{
		if (publisherCountry == null)
		{
			return "";
		}
		final String country = publisherCountry.getCountry();
		final Boolean isOptedIn = publisherCountry.getIsOptedIn();
		return "PublisherCountry[country=" + country + ",isOptedIn=" + isOptedIn + "]";
	}
	
	public static final String getMsnAdGroupString(final AdGroup adGroup)
	{
		if (adGroup == null)
		{
			return "";
		}
		final Bid broadMatchBid = adGroup.getBroadMatchBid();
		final Bid contentMatchBid = adGroup.getContentMatchBid();
		final Bid exactMatchBid = adGroup.getExactMatchBid();
		final Bid phraseMatchBid = adGroup.getPhraseMatchBid();
		final String[] adGroupDistributionArray = adGroup.getAdDistribution();
		final PublisherCountry[] publisherCountriesArray = adGroup.getPublisherCountries();
		
		final BiddingModel biddingModel = adGroup.getBiddingModel();		
		final List<String> adGroupDistributions = Arrays.asList(adGroupDistributionArray);				 		
		final Double broadMatchBidAmount = broadMatchBid == null ? null : broadMatchBid.getAmount();
		final Double contentMatchBidAmount = contentMatchBid == null ? null : contentMatchBid.getAmount();
		final Double exactMatchAmount = exactMatchBid == null ? null : exactMatchBid.getAmount();
		final Double phraseMatchBidAmount = phraseMatchBid == null ? null : phraseMatchBid.getAmount();		
		final com.microsoft.adcenter.v8.Date endDate = adGroup.getEndDate();
		final Long id = adGroup.getId();
		final String language = adGroup.getLanguage();
		final String name = adGroup.getName();
		final Network network = adGroup.getNetwork();
		final PricingModel pricingModel = adGroup.getPricingModel();		
		final com.microsoft.adcenter.v8.Date startDate = adGroup.getStartDate();
		final List<PublisherCountry> publisherCountries = Arrays.asList(publisherCountriesArray);
		final AdGroupStatus adGroupStatus = adGroup.getStatus();
		
		return "AdGroup [biddingModel=" + biddingModel + ", adGroupDistributions=" + adGroupDistributions + ", broadMatchBidAmount="
				+ broadMatchBidAmount + ", contentMatchBidAmount=" + contentMatchBidAmount + ", exactMatchAmount=" + exactMatchAmount
				+ ", phraseMatchBidAmount=" + phraseMatchBidAmount + ", endDate=" + endDate + ", id=" + id + ", language=" + language + ", name="
				+ name + ", network=" + network + ", pricingModel=" + pricingModel + ", startDate=" + startDate + ", adGroupStatus=" + adGroupStatus + ", publisherCountries="
				+ publisherCountries + "]";	
	}
	
	public static final String getMsnCustomerString(final Customer customer)
	{
		if (customer == null)
		{
			return "";
		}
		final Address customerAddress = customer.getCustomerAddress();
		final Long customerID = customer.getId();
		final Industry industry = customer.getIndustry();
		final Long lastModifiedByUserId = customer.getLastModifiedByUserId();
		final Calendar lastModifiedTime = customer.getLastModifiedTime();
		final String marketCountry= customer.getMarketCountry();
		final LanguageType marketLanguage = customer.getMarketLanguage();
		final String name = customer.getName();
		final String number = customer.getNumber();
		final ServiceLevel serviceLevel = customer.getServiceLevel();
		final byte[] customerTimestamp = customer.getTimeStamp();		
		final CustomerFinancialStatus customerFinancialStatus = customer.getCustomerFinancialStatus();
		final CustomerLifeCycleStatus customerLifeCycleStatus = customer.getCustomerLifeCycleStatus();
		
		final String customerAddressString = getMsnAddressString(customerAddress);
		
		return "Customer[customerID=[" + customerID + "], industry=[" + industry + "],lastModifiedByUserId=[" + lastModifiedByUserId + "],lastModifiedTime=[" + lastModifiedTime + "]," +
			   "marketCountry=[" + marketCountry + "],marketLanguage=[" + marketLanguage + "],name=[" + name + "],number=[" + number + "]," +
			   "serviceLevel=[" + serviceLevel + "],customerTimestamp=[" + customerTimestamp + "],customerFinancialStatus=[" + customerFinancialStatus + "],customerLifeCycleStatus=[" + customerLifeCycleStatus + "]," +
			   customerAddressString + "]";
	}
	
	public static final String getGoogleTextAdString(final TextAd textAd)
	{
		if (textAd == null)
		{
			return "";
		}
		final Long id = textAd.getId();
		final String desciption1 = textAd.getDescription1();
		final String desciption2 = textAd.getDescription2();
		final String headline = textAd.getHeadline();
		final String displayUrl = textAd.getDisplayUrl();
		final String url = textAd.getUrl();
		return "TextAd[id=" + id + ",desciption1=" + desciption1 + ",desciption2=" + desciption2 + ",headline=" + headline + ",displayUrl=" + displayUrl + ",url=" + url + "]";
	}
		
	public static final String getTrimmedNonNullString(final String s)
	{
		if (s == null) 
		{
			return "";
		}
		else
		{
			return s.trim();
		}
	}
	
	public static String getEasilyReadableString(final List<?> list)
	{
		if (list == null)
		{
			return "";
		}
		int counter = 0;
		final StringBuffer sb = new StringBuffer();
		for (final Object o : list)
		{
			if (sb.length() != 0)
			{
				sb.append(LINE_SEPARATOR);
			}
			sb.append(++counter).append(": ").append(o);
		}
		return sb.toString();
	}
	
	public static String getEasilyReadableString(final Map<?, ?> m)
	{
		if (m == null)
		{
			return "";
		}
		int counter = 0;
		final StringBuffer sb = new StringBuffer();
		for (final Map.Entry<?, ?> mapEntry : m.entrySet())
		{
			if (sb.length() != 0)
			{
				sb.append(LINE_SEPARATOR);
			}
			sb.append(++counter).append(": ").append(mapEntry.getKey()).append(" -> ").append(mapEntry.getValue());
		}
		return sb.toString();
	}
	

	public static String getStringForArray(final long[] array)
	{
		if (array == null)
		{
			return "";
		}
		final StringBuilder sb = new StringBuilder();
		for (final long o : array)
		{
			if (sb.length() != 0)
			{
				sb.append(", ");
			}
			sb.append(o);
		}
		return sb.toString();
	}
	
	public static String IsNullReturnBlank(String s)
	{
		if (s == null)
		{
			return "";
		}
		else
		{
			return s;
		}
	}
}
