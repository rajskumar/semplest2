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
Insert into AdvertisingEngine(AdvertisingEngine) VALUES ('MSN')
Insert into AdvertisingEngine(AdvertisingEngine) VALUES ('Google')
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
					
					OrbitalGatewaySalemPlatform = '000001',
					OrbitalGatewayMerchantID = '041756',
					OrbitalGatewayUsername = 'TSEMPLEST01',
					OrbitalGatewayPassword = '01tsemplest',
					
					SemplestClientAdwordsTimeoutMS = 20000,
					SemplestClientKeywordTimeoutMS = 20000,
					SemplestClientMSNTimeoutMS = 20000,
					SemplestClientBiddingTimeoutMS = 20000,
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
					ESBBrokerIP = 'VMJAVA1',
					ESBWebServerPort = 9898,
					ESBWebServerURL = 'http://VMJAVA1:9898/semplest',
					ESBHeaderBufferSize = 512000,
					ESBAsynchServletCorePoolSize = 10,
					ESBAsynchServletMaxPoolSize = 100,
					ESBAsynchCallDefaultTimeoutMS = 10000,
					
					ServiceESBServerPort = 9999,
					ServiceESBServerIP = 'VMJAVA1',
					ServicePingFrequencyMS = 10000,
					ServiceNumberServiceThreads = 15,
					ServiceSMTP = 'VMJAVA2',
					
					SemplestEncryptionkey = '12345678901234567890123456789044'

				where ConfigurationPK = 1




/****** Object:  Table [dbo].[EmployeeType]    Script Date: 05/14/2012 15:09:37 ******/
SET IDENTITY_INSERT [dbo].[EmployeeType] ON
INSERT [dbo].[EmployeeType] ([EmployeeTypeID], [EmployeeType]) VALUES (1, N'Rep')
INSERT [dbo].[EmployeeType] ([EmployeeTypeID], [EmployeeType]) VALUES (2, N'Finance')
INSERT [dbo].[EmployeeType] ([EmployeeTypeID], [EmployeeType]) VALUES (3, N'Sales')
INSERT [dbo].[EmployeeType] ([EmployeeTypeID], [EmployeeType]) VALUES (4, N'Admin')
SET IDENTITY_INSERT [dbo].[EmployeeType] OFF


				
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

