update configuration set ReminderEmailUrlPrefix ='https://www.semplest.com/profile/verify?token='
GO
  
ALTER TABLE Configuration ADD SemplestKeywordslucenedir varchar(100)
go

ALTER TABLE Configuration ADD SemplestKeywordsbdbdir varchar(100)
go

ALTER TABLE Configuration ADD SemplestKeywordsdffile varchar(100)
go

ALTER TABLE Configuration ADD numTopics varchar(100)
go


update Configuration set SemplestKeywordslucenedir = 'data/dmoz/lucene/'
go

update Configuration set SemplestKeywordsbdbdir = 'data/dmoz/bdb/'
go

update Configuration set SemplestKeywordsdffile = 'data/word.dict'
go

update Configuration set numTopics = 120
go

---
--- CREATE TABLE: dbo.UserType
---
CREATE TABLE dbo.UserType
(
	UserTypePK int NOT NULL IDENTITY,
	UserType varchar(50) NOT NULL,
	PRIMARY KEY CLUSTERED (UserTypePK)
)
GO
---
--- CREATE COLUMN: UserTypeFK
---
ALTER TABLE dbo.Users ADD UserTypeFK int
GO
---
--- CREATE FOREIGN KEY CONSTRAINT: REL_UserType_Users_2
---
ALTER TABLE dbo.Users ADD 
	CONSTRAINT REL_UserType_Users_2 FOREIGN KEY (UserTypeFK)
		REFERENCES dbo.UserType(UserTypePK)
GO

---
--- CREATE COLUMN: IsKeywordServiceOnly
---
ALTER TABLE dbo.Promotion ADD IsKeywordServiceOnly bit NOT NULL DEFAULT 1
GO
ALTER TABLE dbo.Promotion ADD IsAutobid bit NOT NULL DEFAULT 0
GO
ALTER TABLE dbo.Promotion ADD AutoBidMaxCPC money NULL
GO

ALTER TABLE dbo.Customer ADD AllowAutoBid bit NOT NULL DEFAULT 0
GO
insert into UserType(UserType) Values ('KeywordOnly')
insert into UserType(UserType) Values ('KeywordBid')



IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'dbo.GetAllPromotionData') AND type in (N'P', N'PC'))
  DROP PROCEDURE dbo.GetAllPromotionData;
GO

-- +----------------------------------------------------------------------------------------------------------------+
-- | Author  - Mitch                                                                                                |
-- | Written - 4/20/2012																							|
-- | Parms   - 																							|
-- | Purpose - 						|
-- +----------------------------------------------------------------------------------------------------------------+

CREATE PROCEDURE dbo.GetAllPromotionData
(
	@PromotionPK            INT
)
AS
BEGIN TRY
	SET NOCOUNT ON;
	DECLARE @ErrMsg VARCHAR(250)

	--validate data
	IF NOT EXISTS (select * from Promotion p where p.PromotionPK =@PromotionPK)
	BEGIN
		SELECT @ErrMsg = 'The Promotion was not found.'; 
		RAISERROR (@ErrMsg, 16, 1);
	END;
	--get general info
	select p.PromotionPK,p.ProductGroupFK,p.PromotionName,p.PromotionDescription,p.LandingPageURL,p.DisplayURL,
		p.PromotionBudgetAmount,p.PromotionStartDate, p.PromotionEndDate,bc.BudgetCycle,
		p.CycleStartDate,
		p.RemainingBudgetInCycle,p.StartBudgetInCycle,
		p.EditedDate, p.IsLaunched, p.IsCompleted,p.IsPaused,p.CreatedDate, aep.AdvertisingEngineCampaignPK, aep.AdvertisingEngineAdGroupID , 
		aea.AdvertisingEngineAccountPK,aea.AdvertisingEngineAccountNumber, ae.AdvertisingEngine, p.IsAutobid, p.AutoBidMaxCPC, p.CycleEndDate

		from Promotion p 
		inner join BudgetCycle bc on bc.BudgetCyclePK = p.BudgetCycleFK
		left join AdvertisingEnginePromotion aep on aep.PromotionFK = p.PromotionPK
		left join AdvertisingEngineAccount aea on aea.AdvertisingEngineAccountPK = aep.AdvertisingEngineAccountFK
		left join AdvertisingEngine ae on aea.AdvertisingEngineFK = ae.AdvertisingEnginePK
		where p.PromotionPK  = @PromotionPK
		--get ADs
		select pa.PromotionAdsPK,pa.PromotionFK,  pa.AdTitle, pa.AdTextLine1, pa.AdTextLine2, aea.AdvertisingEngineAdPK [AdEngineAdID], pa.IsDeleted, pa.CreatedDate, pa.DeletedDate, aea.AdvertisingEngineFK from Promotion p
		inner join PromotionAds pa on pa.PromotionFK = p.PromotionPK
		left outer join AdvertisingEngineAds aea on aea.PromotionAdsFK = pa.PromotionAdsPK
		where p.PromotionPK = @PromotionPK
		--get Geotargeting
		select gt.Address, gt.City,st.StateAbbr [State], gt.Zip, gt.Latitude, gt.Longitude,gt.ProximityRadius [Radius] from Promotion p 
		inner join GeoTargeting gt on gt.PromotionFK = p.PromotionPK
		left join StateCode st on st.StateAbbrPK = gt.StateCodeFK
		where p.PromotionPK = @PromotionPK			 
	
END TRY
BEGIN CATCH
	DECLARE @ErrMessage	nvarchar(4000),
          @ErrorSeverity	int,
          @ErrorState		int;
	SELECT @ErrMessage = ERROR_MESSAGE(), @ErrorSeverity = ERROR_SEVERITY(), @ErrorState = ERROR_STATE();
	RAISERROR (@ErrMessage, @ErrorSeverity, @ErrorState);
END CATCH;
GO




insert into Job values ('EXPIRED_CREDENTIALS_EMAIL_SENDER', GETDATE());
insert into Job values ('PROMOTION_MAINTENANCE', GETDATE());





