--roles 
insert into dbo.roles (rolename) values ('Customer (child)')
insert into dbo.roles (rolename) values ('Customer (parent)')
insert into dbo.roles (rolename) values ('Account Representative (Rep)')
insert into dbo.roles (rolename) values ('Salesperson')
insert into dbo.roles (rolename) values ('Finance User')
insert into dbo.roles (rolename) values ('Super User (Admin)')
insert into dbo.roles (rolename) values ('System')

--scheduler 
insert into Frequency(Frequency) values ('Now')
insert into Frequency(Frequency) values ('Daily')
insert into Frequency(Frequency) values ('Weekly')
insert into Frequency(Frequency) values ('Monthly')
insert into Frequency(Frequency) values ('TenMinutes')
--Ad Engines
Insert into AdvertisingEngine(AdvertisingEngine, LogoURL) VALUES ('MSN','MSN Logo.png')
Insert into AdvertisingEngine(AdvertisingEngine, LogoURL) VALUES ('Google','Google Logo.png')
--Keyword Match Type
insert into BidType(BidType) values ('Broad')
insert into BidType(BidType) values ('Exact')
insert into BidType(BidType) values ('Phrase')
--Budget cycle
insert into BudgetCycle(BudgetCycle) values ('Daily')
insert into BudgetCycle(BudgetCycle) values ('Weekly')
insert into BudgetCycle(BudgetCycle) values ('Monthly')
insert into BudgetCycle(BudgetCycle) values ('Yearly')
--Billing Types
insert into BillType(BillType) values ('No Bill')
insert into BillType(BillType) values ('Credit Card')
insert into BillType(BillType) values ('Invoice')

--states

/****** Object:  Table [dbo].[StateCode]    Script Date: 05/08/2012 18:04:10 ******/
SET IDENTITY_INSERT [dbo].[StateCode] ON
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (1, N'AL')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (2, N'AK')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (3, N'AZ')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (4, N'AR')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (5, N'CA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (6, N'CO')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (7, N'CT')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (8, N'DE')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (9, N'FL')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (10, N'GA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (11, N'HI')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (12, N'ID')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (13, N'IL')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (14, N'IN')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (15, N'IA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (16, N'KS')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (17, N'KY')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (18, N'LA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (19, N'ME')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (20, N'MD')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (21, N'MA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (22, N'MI')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (23, N'MN')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (24, N'MS')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (25, N'MO')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (26, N'MT')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (27, N'NE')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (28, N'NV')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (29, N'NH')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (30, N'NJ')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (31, N'NM')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (32, N'NY')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (33, N'NC')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (34, N'ND')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (35, N'OH')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (36, N'OK')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (37, N'OR')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (38, N'PA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (39, N'RI')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (40, N'SC')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (41, N'SD')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (42, N'TN')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (43, N'TX')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (44, N'UT')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (45, N'VT')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (46, N'VA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (47, N'WA')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (48, N'WV')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (49, N'WI')
INSERT [dbo].[StateCode] ([StateAbbrPK], [StateAbbr]) VALUES (50, N'WY')
SET IDENTITY_INSERT [dbo].[StateCode] OFF
/****** Object:  Table [dbo].[PhoneType]    Script Date: 05/08/2012 18:04:10 ******/
INSERT [dbo].[PhoneType] ([PhoneTypePK], [PhoneType]) VALUES (1, N'Business')
GO


insert into [AddressType] (AddressType) values ('H')
GO
---ProductGroupCycleType
INSERT INTO ProductGroupCycleType (ProductGroupCycleType, CycleInDays) VALUES ('Product Group Cycle 30' ,30)
INSERT INTO ProductGroupCycleType (ProductGroupCycleType, CycleInDays) VALUES ('Product Group Cycle 7' ,7)
INSERT INTO ProductGroupCycleType (ProductGroupCycleType, CycleInDays) VALUES ('Product Group Cycle 365' ,365)

--Config
INSERT INTO [semplest].[dbo].[Configuration]
           ([CustomerMinOrderAmount],
           [CustomerDefaultMonthlyFlatFeeAmount],
           [CustomerDefaultPerCampaignFlatFeeAmount],
           [CustomerDefaultPerAdGroupFlatFeeAmount],
           [DefaultMediaComissionPercentage],
           [DefaultSalesPersonCommissionPercentage],
           [MinSalespersonCommissionPercentage])
     VALUES
           (25,75,17.5,12.75,4.13,0,0)

update Configuration 
					set MSNCampaignURL = 'https://adcenterapi.microsoft.com/Api/Advertiser/v8/CampaignManagement/CampaignManagementService.svc?wsdl' ,
					MSNCampagnNamespace = 'https://adcenter.microsoft.com/v8',
					MSNCustomerURL ='https://sharedservices.adcenterapi.microsoft.com/Api/CustomerManagement/v8/CustomerManagementService.svc?wsdl',
					MSNCustomerNamespace = 'https://adcenter.microsoft.com/api/customermanagement',
					MSNReportingURL = 'https://adcenterapi.microsoft.com/Api/Advertiser/v8/Reporting/ReportingService.svc?wsdl',
					MSNReportingNamespace = 'https://adcenter.microsoft.com/v8',
					MSNAdIntelligenceURL = 'https://adcenterapi.microsoft.com/Api/Advertiser/v8/CampaignManagement/AdIntelligenceService.svc?wsdl',
					MSNAdIntelligenceNamespace = 'https://adcenter.microsoft.com/v8',
					MSNParentCustomerID = 694122,
					MSNApiUsername = 'API_SEMplest',
					MSNApiPassword = '1s3mpl3st',
					MSNUserAccessKey = '6LTW1JCMEKIUX3',
					
					AdwordsEmail = 'adwords@semplest.com',
					AdwordsPassword = '7ylZJTlhuCG4loTC6Qllrw==', 
					AdwordsUserAgent= 'Icosystem',
					AdwordsDeveloperToken = '2H8l6aUm6K_Q44vDvxs3Og',
					AdwordsUseSandbox = 1, 
					AdwordsBillingAccount = '8490069727',
					
					OrbitalGatewaySalemPlatform = '000001',
					OrbitalGatewayMerchantID = '041756',
					OrbitalGatewayUsername = 'TSEMPLEST01',
					OrbitalGatewayPassword = 'ZiSssEltPoU19aKz5kgZUg==',
					
					SemplestClientAdwordsTimeoutMS = 20000,
					SemplestClientKeywordTimeoutMS = 400000,
					SemplestClientMSNTimeoutMS = 20000,
					SemplestClientBiddingTimeoutMS = 1200000,
					SemplestClientMailTimeoutMS = 5000,
					SemplestClientSchedulerTimeoutMS = 5000,
					
					SemplestBiddingMaxRetry = 10,
					SemplestBiddingSleepPeriod = 500,
					SemplestBiddingSleepBackOffTime = 1000,
					SemplestBiddingMaxMicroBid = 3000000,
					SemplestBiddingStepAboveFpCPC = 500000,
					SemplestBiddingDefaultMicroBid = 1000000,
					SemplestBiddingMaxDefaultMicroBid = 1500000,
					SemplestBiddingStepFirst = 100000,
					SemplestBiddingStepSecond =  600000,
					SemplestBiddingStepRest =  800000,
					SemplestBiddingGooglePercent = 70,
					
					ESBRegServicePort = 9999,
					ESBBrokerName = 'SEMplestMQ',
					ESBBrokerPort = 61616,
					ESBBrokerIP = 'VMDEVJAVA1',
					ESBWebServerPort = 9898,
					ESBWebServerURL = 'http://VMDEVJAVA1:9898/semplest',
					ESBHeaderBufferSize = 512000,
					ESBAsynchServletCorePoolSize = 10,
					ESBAsynchServletMaxPoolSize = 100,
					ESBAsynchCallDefaultTimeoutMS = 10000,
					
					ServiceESBServerPort = 9999,
					ServiceESBServerIP = 'VMDEVJAVA1',
					ServicePingFrequencyMS = 10000,
					ServiceNumberServiceThreads = 15,
					ServiceSMTP = 'VMDEVJAVA2',
					ServiceESBPingWaitMS = 30000,
					
					SemplestEncryptionkey = '12345678901234567890123456789044',
					
					SemplestKeywordsdictfile = 'data/stemword.dict',
					SemplestKeywordsdocfile = 'data/dmoz/all.cats',
					SemplestKeywordstwfile = 'data/dmoz/all.tw',
					SemplestKeywordsdfile = 'data/dmoz/all/all.descs',
					SemplestKeywordsbaseMultiWPath = 'data/dmoz/multiwords/',
					SemplestKeywordsnGramsSubC = 'arts,business,computers,games,health,home,news,recreation,reference,science,shopping,society,sports',
					SemplestKeywordsnGramsC = 'all_2',
					SemplestKeywordsvalidCat = 'arts,business,computers,games,health,home,news,recreation,reference,science,shopping,society,sports',
					SemplestKeywordslucenedfile = 'data/dmoz/all/all.descs',
					SemplestKeywordssmallhCounts = '/semplest/data/dmoz/small/hCounts.txt',
					SemplestKeywordsstoplist = 'data/stoplists/en.txt',
					SemplestKeywordsnumTopics  = 126,
					SemplestKeywordsuserInfoWeight = 0.75,
					SemplestKeywordsnumKeywordsGoogle = 1500,
					SemplestKeywordsnumKeywordsMSN = 4000,
					SemplestKeywordsnumThreads = 6

				where ConfigurationPK = 1

				
--add first admin user

SET IDENTITY_INSERT [dbo].[Users] ON
INSERT [dbo].[Users] ([UserPK], [CustomerFK], [FirstName], [LastName], [Email], [CreatedDate], [EditedDate], [IsActive], [MiddleInitial], [IsRegistered]) VALUES (1, NULL, N'Mark', N'Kelley', N'mk@rre.com', CAST(0x070000000000000000 AS DateTime2), NULL, 1, N'x', 1)
SET IDENTITY_INSERT [dbo].[Users] OFF
SET IDENTITY_INSERT [dbo].[Employee] ON
INSERT [dbo].[Employee] ([EmployeePK], [EmployeeTypeFK], [UsersFK], [ReportingTo], [HireDate]) VALUES (1, 4, 1, NULL, CAST(0x070000000000A6350B AS DateTime2))
SET IDENTITY_INSERT [dbo].[Employee] OFF
SET IDENTITY_INSERT [dbo].[Credential] ON
INSERT [dbo].[Credential] ([CredentialPK], [UsersFK], [Username], [Password], [RememberMe], [SecurityQuestion], [SecurityAnswer]) VALUES (1, 1, N'markkelley', N'markkelley', 0, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Credential] OFF
SET IDENTITY_INSERT [dbo].[UserRolesAssociation] ON
INSERT [dbo].[UserRolesAssociation] ([UserRolesAssociationPK], [UsersFK], [RolesFK]) VALUES (1, 1, 6)
SET IDENTITY_INSERT [dbo].[UserRolesAssociation] OFF



-- Email Types
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('ChangePassword')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('CreditCardDeclined')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('ExpiredActivationIds')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('ForgottenPassword')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('PromotionNotStarted')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('WelcomeEmailChild')
INSERT INTO [semplest].[dbo].[EmailType] ([EmailType]) VALUES ('WelcomeEmailParent')


--EmailTemplates
INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Your New SEMplest Password',
        'Dear [VendorName],<br><br>Our records show your password to SEMplest has been changed. If you did not change your password, please contact us at [help@semplest.com].<br><br>Thank you!<br>SEMplest',
		'help@semplest.com',
		1)	           


INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Your SEMplest Account has been Paused',
        'Dear [VendorName],<br><br>Our records show the credit card on file needs to be updated in order for your ads to remain active.<br><br>Please log into your SEMplest account to update your billing information or contact us at [help@semplest.com].<br><br>[SEMPLEST LINK]<br><br>Thank you!<br>SEMplest',
		'help@semplest.com',
		2)	             
  

INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Expired SEMplest Password',
        'Dear [VendorName],<br><br>Due to inactivity using the SEMplest tool, your user name and password have expired. Below you will find updated account information. Please note, these activation IDs will expire in [XX] days.<br><br>User Name: [xxxxxx]<br>Password: [xxxxx]<br><br>[SEMPLEST LINK]<br><br>If you need any assistance, please reach out to us at [help@semplest.com].<br><br>Thank you!<br>SEMplest',
		'help@semplest.com',
		3)	   



INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Your New SEMplest Password',
        'Dear [VendorName],<br><br>Below please find the temporary password to access your SEMplest account. Your user id has not changed. You will be prompted to change your password after logging in.<br><br>Password: [xxxxx]<br><br>[SEMPLEST LINK]<br><br>If you need any assistance, please reach out to us at [help@semplest.com].<br><br>Thank you!<br>SEMplest',
		'help@semplest.com',
		4)
		
		
INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Let SEMplest manage your search marketing!',
        'Dear [VendorName]<br><br>We noticed you haven’t created any search ads using SEMplest.<br><br>Creating a promotion is fast, easy and cost effective. If you need assistance getting started, please contact us at [help@semplest.com] or log into [SEMplest Link] and let us do the heavy lifting! We’ll have your promotions live on Google, Bing and Yahoo! in no time.<br><br>Want to learn more? Read our [FAQs] or [About Us].<br><br>We look forward to showing you how effective and efficient SEMplest makes search engine marketing!<br><br>Best,<br><br>The SEMplest Team',
		'help@semplest.com',
		5)
		
		
INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'How to reach the right audience and increase visits to your website—effortlessly!',
        'Dear [VendorName]<br><br>Interested in reaching your target audience through search engine marketing, but not sure how to get started?<br><br>Already have a search program and want to save time and money?<br><br>No worries!  [Allstate] now offers an easy-to-use search marketing tool to help you get the most out of your advertising -- through search engine marketing on Google, Bing and Yahoo!.<br><br>You’re invited to try out [Allstate Search] -- our intuitive tool for small businesses to create search engine marketing campaigns effectively and efficiently.<br><br>It lets you:<ul><li>Discover hundreds of personalized keywords for all of your business products.<li>Launch a search campaign in just three easy steps.<li>Sit back and relax – it automates the ongoing management of your campaigns.</ul><br><br>Want to learn more? Read our [FAQs].<br><br>Ready to get started? Go to [INSERT LINK] and use the following log in information:<br><br>User Name: [xxxxxx]<br>Password: [xxxxx]<br><br>With [Allstate], it’s that easy!',
		'help@semplest.com',
		6)
		
		
INSERT INTO [semplest].[dbo].[EmailTemplate] ([CustomerFK],[EmailSubject],[EmailBody],[EmailFrom],[EmailTypeFK]) VALUES
        (null,
        'Welcome to SEMplest! Account Details Inside',
        'Dear [VendorName]<br><br>Thank you for choosing SEMplest to effortlessly manage your search marketing needs!<br><br>To initiate your account and invite [Allstate agents] to use the tool, go to [INSERT LINK] and enter the following log in information:<br><br>User Name: [xxxxxx]<br>Password: [xxxxx]<br><br>If you need any assistance, please reach out to us at [help@semplest.com].<br><br>We look forward to showing you how effective and efficient SEMplest makes search engine marketing!<br><br>Thank you![SEMplest (OR REP NAME)]',
		'[salesrepemailaddress]',
		7)
		
--Web Content		
INSERT INTO [semplest].[dbo].[WebContent]

([FAQ],[AboutUs],[ContactUs]) Values
('<ol>
<li><p><b>What is SEM?</b><br>SEM is Search Engine Marketing. Search Engine Marketing is a form of Internet marketing that promotes websites by increasing visibility in search engine results. It is sometimes referred to as Pay Per Click (PPC) advertising since you only pay when a user clicks on your ad.</p></li>
<li><p><b>What are SEM ads?</b><br>SEM ads are short, text-based ads that appear above and/or to the side of the natural (unpaid) search results.<br>[~IMAGE_BEGIN]SearchEngineMarketingAds.png[~IMAGE_END]</p></li>
<li><p><b>What is the SEMplest tool?</b><br>The SEMplest tool is a simple, intuitive, well designed tool for businesses to create SEM campaigns effectively and efficiently.  This tool helps businesses seamlessly manage hundreds of keywords for multiple products and services, with multiple audience targets across multiple search engines. SEMplest uses artificial intelligence to automate the optimization of SEM campaigns. We do the heavy lifting for you.</p></li>
<li><p><b>How does SEMplest work?</b><br>Based on some basic information you provide about your business, the tool automatically generates hundreds of keywords and bids on each keyword(s) using proprietary technology and artificial intelligence. The tool monitors and dynamically changes the bids over time, across search engines to give you the best possible cost per visitor, and therefore, the best return on your investment. It''s important to note that you are not charged for your ads that potential customers see. You are only charged for those visitors that clicked on your ads and land on your website.</p></li>
<li><p><b>What does CPC mean?</b><br>CPC is cost per click, which is the amount of money that is spent on each click on your search engine advertisement.</p></li>
<li><p><b>Which search engines will my ads appear in?</b><br>The tool places advertisements in Google and Bing/Yahoo! You have the choice which search engine you want your ads to appear, although we recommend you use all of them.</p></li>
</ol>
<h3>Creating Ad Campaigns</h3>
<ol start="7">
<li><p><b>How many ads should I create?</b><br>SEMplest only requires one ad copy, but you are encouraged to try several different ads and the tool  will rotate and monitor to see which is performing better. The better performing ad will be used more often to help maximize the number of visitors to your site.</p></li>
<li><p><b>Why am I being asked to select locations to advertise in?</b><br>Selecting locations will help target your ads to the specific geographic areas in which you would like to market your services and products.</p></li>
<li><p><b>What is a keyword?</b><br>Keywords are words browsers type into search engines to find information relating to that search term. When a keyword is typed into a search box on a search engine, the engine uses that keyword to search the web for relevant web sites.</p></li>
<li><p><b>What is a long tail keyword?</b><br>Long tail keyword refers to several keywords or phrases a user types into a search engine to find relevant information. For example, "flowers" would be considered a broad keyword, whereas "flowers for a June wedding" would be a long tail phrase.</p></li>
<li><p><b>What is a negative keyword?</b><br>Negative keywords are keywords within a paid search campaign that prevent your ad from showing up. For example, if you are a florist who only sells fresh flowers, you may make "silk" a negative keyword.</p></li>
<li><p><b>How did you come up with the particular keywords for my advertisement?</b><br>Based on the information you entered to describe your business, along with your selected category, ad copy, website and location, we generated the best set of keywords to match what your target audience is using as search terms.</p></li>
<li><p><b>What do I do if I see a keyword that I don''t want my ad to appear under?</b><br>You may remove keywords that you don''t want to see your ads to appear under by checking off those keywords from the link that appears on your advertisement summary screen.</p></li>
<li><p><b>What if I don''t see a keyword that I would like to bid on?</b><br>With a typical SEM campaign you bid on the big keywords that everyone else wants. You''ll have to bid very high to win those words and phrases so your monthly budget doesn''t go too far. With SEMplest''s tool, we find you a large number of keywords and phrases that, individually, are not clicked as often, but together, in total, can get you many more high value prospects for the same investment.</p></li>
<li><p><b>What are Sitelinks?</b><br>Sitelinks are sub landing pages that sometimes appear under a search advertisement and are meant to help users navigate your site.  Currently, site links are only available for search campaigns via Google.<br>[~IMAGE_BEGIN]WhatAreSiteLinks.png[~IMAGE_END]</p></li>
<li><p><b>Can I edit my campaign after it is started?</b><br>Yes, you may edit your campaign at any time.</p></li>
<li><p><b>Can I stop my campaign if I don''t want to continue?</b><br>Yes, you may stop your campaign at any time.</p></li>
<li><p><b>Can I restart my campaign if it has been stopped?</b><br>Yes.</p></li>
</ol>

<h3>Campaign Billing</h3>
<ol start="19">
<li><p><b>How do I add or change a credit card?</b><br>This can all be done online in your account dashboard.</p></li>
<li><p><b>How am I billed?</b><br>When you first start a campaign, we immediately bill your selected credit card for the amount of the monthly budget you set, along with any SEMplest fees. On a reoccurring monthly basis, we will bill your card approximately 7 days before the next month of services is due to begin. Any unused budget from the previous month will roll over into the next full billing cycle (e.g. if only half your budget was used this month, the unused amount will roll over and you''ll only be charged half your budget during the next billing cycle!). Please note, services cannot begin until your credit card has been approved.</p></li>
<li><p><b>I changed my campaign budget in the middle of the month, but the change is not reflected on the campaign information page.</b><br>Any changes to your campaign budget will be reflected during the next billing cycle (i.e., the next month).</p></li>
<li><p><b>Why did I get charged more than my budget?</b><br>Occasionally there are charges applied by the search engines after your budget has been used up. This rarely occurs, and will generally be constrained to several dollars over your budget. Any overages will be reflected on your next month''s bill.</p></li>
<li><p><b>What if I am not using the tool anymore and I want a refund of the remaining money on my account?</b><br>First, stop any active campaigns. Then, email help@semplest.com to request a refund. Note that you will not be able to receive a refund until the end of the current billing cycle.</p></li>
</ol>

<h3>Campaign Reporting and Communications</h3>
<ol start="24">
<li><p><b>I received an email telling me that my account has been suspended. Why?</b><br>Accounts can be suspended for a number of reasons, most often because of billing issues. Please make sure you have an updated credit card on file or email help@semplest.com to work with a representative to correct any account issues. Once the issue(s) has been corrected, the representative can un-suspend your account.</p></li>
<li><p><b>Why did I stop receiving clicks on my campaign part way through the month? I was getting a lot of traffic!</b><br>Once your campaign''s budget has been exhausted for a particular month, the campaign will be paused until the next month. If this occurs consistently, you should consider increasing your campaign budget.</p></li>
<li><p><b>How can I track my results?</b><br>You can track your results by logging into the tool and visit the "Reporting" section.</p></li>
<li><p><b>Why did I receive an email saying my campaign had been rejected?</b><br>If the ad you entered contained content objectionable to a search engine (language that is obscene, proprietary/trademarked, spam, etc.), it''s possible that the search engine will later review your ad and reject it. If this occurs, we''ll send you an email and ask you to rewrite and resubmit your ads.</p></li>
</ol>
For additional questions or concerns, please reach out to SEMplest at [help@semplest.com]'
,
'<p>SEMplest is the only SEM provider to offer even novice users a simple, "do-it-yourself" option to create search marketing campaigns that delivers effective results.</p>
<p>In just three simple steps, businesses can simultaneously purchase keywords and create ads on multiple search engines using new technologies that automate keyword selection, bidding and campaign creation. Then, let us do all the heavy lifting to manage your campaign on an ongoing basis with our proprietary bidding algorithm that constantly makes sure you are getting the best bang for your buck across Google, Bing and Yahoo!</p>
<p>We are the only SEM provider to offer</p>
<ul>
<li>Automated optimization: dynamically optimizing keyword purchases and ongoing campaign management</li>
<li>Personalization: hundreds of unique keywords purchased for each advertiser</li>
<li>Intelligent spend of money:  automatically generating and optimizing hundreds of long tail keywords which drives down advertiser expenses (lowers cost per click)</li>
<li>Transparent pricing: no hidden charges or mark-ups</li>
<li>Cost efficiencies: SEMplest''s automated technology creates lower overhead expenses</li>
</ul>
<p>It''s really as simple as one, two, three:</p>
<p>1.Briefly Describe Your Business<br>[~IMAGE_BEGIN]BrieflyDescribeYourBusiness.png[~IMAGE_END]<br>2. Create Your Ads<br>[~IMAGE_BEGIN]CreateYourAds.png[~IMAGE_END]<br>3. Launch Your Campaign<br>[~IMAGE_BEGIN]LaunchYourCampaign.png[~IMAGE_END]<br></p>
<p>Click here to schedule a demo or email info@semplest.com</p>
<p><b>SEMPLEST MANAGEMENT TEAM</b></p>
<p><b>Paul Benjou</b> - Chief Executive Officer<br>Mr. Benjou brings over 35 years of executive level management to SEMplest. Mr. Benjou has been the Founder and Principal at The Center for Media Management Strategies, a consultancy serving digital media companies and prior companies include Universal McCann, DraftWorldwide, ValueClick and Datran Media.  He serves as a Senior Fellow and Advisory Board member with the Society for New Communications Research and is a sustaining member of the American Institute for Economic Research. He served on the Board of Global Networks, Inc. and remains as an advisor.</p>
<p><b>Mitchell Berg, Ph.D.</b> - Chief Technology Officer<br>Mr. Berg has over 25 years of technology and software architecture and development experience with a focus on developing and commercializing R&D. Previous roles included Chief Information Officer of BenefitPlan Manager Corp. and Advanced Information Technologist at The Boeing Company.</p>
<p><b>Chad Miller</b> - Chief Revenue Officer<br>Mr. Miller has been in the digital and technology industry for 15 years. During most of this period he has been focused on marketing and selling technology and media services involving email, display and analytics with companies like Nielsen, Accenture and Valueclick.</p>
<p><b>Nicole Ragains</b> - Executive Vice President, Operations<br>Ms. Ragains has been in the financial and digital industries for over 11 years. Prior roles included running Corporate Development for XO Group and financial consulting for Huron Consulting Group and Arthur Andersen.</p>'
,
'<p>Need assistance? We''re here to help!</p><p>Feel free to contact us at help@semplest.com and someone will be in touch shortly.</p><p>Thank you!</p>'
)


--Web Content QuestionMarkHelper
--Web Content QuestionMarkHelper
SET IDENTITY_INSERT [dbo].[WebContentQuestionMarkHelp] ON
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (1,'[configuration.DefaultProductGroupName]','Please type a word or phrase that best describes the over-arching category of your product or service. For example, if you are a florist, you may enter "Seasonal Events" as your category and "Wedding Flowers" or "Prom Flowers" as your Sub Category. This information will help us create a targeted advertising message to deliver you the best fit customer.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (2,'[configuration.DefaultPromotionName]','Please type a word or phrase that best describes your product or service in more detail. This information will help us create a targeted advertising message to deliver you the best fit customer. For example, if you are a florist and you entered "Seasonal Events" as your category, you may type "Wedding Flowers" or "Prom Flowers" as your Sub Category. Sub Category should be as specific as possible to help us best understand your what you are trying to advertise.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (3,'Description','Please type in words and phrases that describe, in detail, what you sell or promote. For example, if you are a florist, and your Category is "Seasonal Events" and your Sub Category is "Wedding Flowers", your description may be "beautiful, fresh wedding flowers, creative designs, gorgeous centerpieces  and  bridal bouquets, specializing in unique and exquisite floral wedding arrangements." The more descriptive, the more information we have to best understand what will draw a customer to your site.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (4,'Budget','Please enter the MONTHLY amount you would like to spend across all selected search engines. Whole dollars amounts only.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (5,'Search Engines','Please select which search engines you would like your advertisement to appear. We highly encourage advertising across all search engines.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (6,'Location','Entering a location helps target your ads to the specific geographic area in which you would like to market your services and products. You can enter an entire address or just a city/state or zip. Then select a radius (in miles) from that address that you would like your ad to appear. For example, if you are a florist in Boston, you may want to advertise your services within a 50 mile radius of your shop’s actual address. You may enter as many business addresses as necessary. For example, you may have a flower shop in Brooklyn, NY and Staten Island, NY. ')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (7,'URL','This URL will serve as the landing page for all of your ads and appears directly below your ad copy in the search results.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (8,'Ad Title','An ad title is meant to catch the eye of a consumer so they read your advertisement. Ad Copy has a maximum character limit of 25. Search engines do not allow all caps, or the use of punctuation marks in ad titles.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (9,'Ad Copy','This is your advertisement that is going to appear on the search engines. Ad Copy has a maximum character limit of 70. Offers or discounts are a great way to attract customers.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (10,'Additional Click Through','Additional click through options is a feature that lets you include additional links to other content on your site that''s different than the landing page you entered under URL. These sub links appear below your ad copy in the search results.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (11,'Negative Keywords','Adding a negative keyword to your product sub category means that your ads won''t show for searches containing that term. By filtering out unwanted impressions, negative keywords can help you reach the most appropriate prospects. For example, if you are a florist who only sells fresh flowers, you may add "silk" a negative keyword.')
INSERT INTO [semplest].[dbo].[WebContentQuestionMarkHelp]([WebContentQuestionMarkHelpPK],[Title],[Copy]) VALUES (12,'Categories','Please pick the category that most closely represents your product or service. This information will help us create a targeted advertising message to deliver you the best fit customer.')
SET IDENTITY_INSERT [dbo].[WebContentQuestionMarkHelp] OFF
GO