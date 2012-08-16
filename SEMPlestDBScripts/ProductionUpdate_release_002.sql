---
--- CHANGE COLUMN: AddressType.AddressType
---
ALTER TABLE dbo.AddressType
	ALTER COLUMN AddressType
		nvarchar(25)
GO


---
--- CREATE COLUMN: AddressTypeFK
---
ALTER TABLE dbo.GeoTargeting ADD AddressTypeFK int
GO
---
--- CREATE FOREIGN KEY CONSTRAINT: REL_AddressType_GeoTargeting_3
---
ALTER TABLE dbo.GeoTargeting ADD 
	CONSTRAINT REL_AddressType_GeoTargeting_3 FOREIGN KEY (AddressTypeFK)
		REFERENCES dbo.AddressType(AddressTypePK)
GO


alter table configuration add MSNReportRetrievalTimeoutSecs int
go
update Configuration set MSNReportRetrievalTimeoutSecs = 900
go

IF EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'dbo.SetBidObject') AND type in (N'P', N'PC'))
  DROP PROCEDURE dbo.SetBidObject;
GO

-- +----------------------------------------------------------------------------------------------------------------+
-- | Author  - Mitch                                                                                                |
-- | Written - 4/20/2012																							|
-- | Parms   - 																							|
-- | Purpose - 						|
-- +----------------------------------------------------------------------------------------------------------------+

CREATE PROCEDURE dbo.SetBidObject   
(
	@PromotionPK            INT,
	@KeywordAdEngineID      BIGINT,
	@Keyword				NVARCHAR(250),
	@MicroBidAmount			INT,
	@BidType				VARCHAR(25),
	@AdvertisingEngine		VARCHAR(50),
	@IsNegative				bit = 1,
	@CompetitionType		varchar(20),
	@IsDefaultValue bit,
	@ID int output
)
AS
BEGIN TRY
	SET NOCOUNT ON;
	DECLARE @ErrMsg VARCHAR(250), @currentMicroBidAmt int, @keywordBidPK int, @currentTime datetime2, @BidTypeID int, 
			@AdEngineID int,@keywordPK int

	--validate data
	--AdEngine
	IF NOT EXISTS (select * from AdvertisingEngine a where a.AdvertisingEngine = @AdvertisingEngine)
	BEGIN
		SELECT @ErrMsg = 'The Advertising Engine was not found.'; 
		RAISERROR (@ErrMsg, 16, 1);
	END;
	--BidType
	IF NOT EXISTS (select * from BidType bt where bt.BidType = @BidType)
	BEGIN
		SELECT @ErrMsg = 'The Bid Type was not found.'; 
		RAISERROR (@ErrMsg, 16, 1);
	END;
	if not exists (select * from Keyword k where k.Keyword = @Keyword)
	BEGIN
		SELECT @ErrMsg = 'The Selected keyword does not exist'; 
		RAISERROR (@ErrMsg, 16, 1);
	END;		
	select @AdEngineID = a.AdvertisingEnginePK from AdvertisingEngine a where a.AdvertisingEngine = @AdvertisingEngine
	select @BidTypeID = bt.BidTypePK from BidType bt where bt.BidType = @BidType
	if (@IsNegative = 1)
	BEGIN
		SET @MicroBidAmount = 0
	END	
	SET @currentTime = CURRENT_TIMESTAMP
	
	BEGIN TRANSACTION
	--Check to see if this keyword is already bid on for the AdEngine and BidType	
	if exists (select * from PromotionKeywordAssociation pka
				inner join Keyword k on k.KeywordPK = pka.KeywordFK
				inner join KeywordBid kb on kb.KeywordFK = k.KeywordPK and kb.PromotionFK = @PromotionPK
				inner join AdvertisingEngine a on a.AdvertisingEnginePK = kb.AdvertisingEngineFK
				where pka.PromotionFK = @PromotionPK and a.AdvertisingEnginePK = @AdEngineID 
					 and kb.BidTypeFK = @BidTypeID
					 and (k.Keyword = @Keyword or kb.KeywordAdEngineID = @KeywordAdEngineID))
	BEGIN
		
		select @currentMicroBidAmt = kb.MicroBidAmount, @keywordBidPK = kb.KeywordBidPK , @keywordPK = kb.KeywordFK
			from PromotionKeywordAssociation pka
					inner join Keyword k on k.KeywordPK = pka.KeywordFK
					inner join KeywordBid kb on kb.KeywordFK = k.KeywordPK and kb.PromotionFK = @PromotionPK
					inner join AdvertisingEngine a on a.AdvertisingEnginePK = kb.AdvertisingEngineFK
					where pka.PromotionFK = @PromotionPK and pka.IsActive = 1 and a.AdvertisingEnginePK = @AdEngineID
						 and kb.BidTypeFK = @BidTypeID
						 and (k.Keyword = @Keyword or kb.KeywordAdEngineID = @KeywordAdEngineID)
		
			if (@MicroBidAmount != @currentMicroBidAmt) 
			BEGIN
				 --update the last bid with an end Date and set inactive
				 UPDATE KeywordBid set EndDate = @currentTime, IsActive = 0 WHERE KeywordBidPK = @keywordBidPK
				 --add new active keyword bid
				 INSERT INTO KeywordBid(KeywordFK,AdvertisingEngineFK,PromotionFK,StartDate,EndDate,IsActive,BidTypeFK,MicroBidAmount,KeywordAdEngineID, CompetitionType, IsDefaultValue)
				 select kb.KeywordFK,kb.AdvertisingEngineFK,kb.PromotionFK,@currentTime,null,1,@BidTypeID,@MicroBidAmount,@KeywordAdEngineID, @CompetitionType, @IsDefaultValue
					from KeywordBid kb where kb.KeywordBidPK = @keywordBidPK
				SET @ID = @@IDENTITY	 
				--make sure the associaition is active
				update PromotionKeywordAssociation set IsActive = 1 where PromotionFK = @PromotionPK and KeywordFK = @keywordPK	
			END	
			ELSE  -- update competition parameters 
			BEGIN
				update KeywordBid set CompetitionType = @CompetitionType, IsDefaultValue = @IsDefaultValue
					from KeywordBid kb where kb.KeywordBidPK = @keywordBidPK
				SET @ID = @keywordBidPK	
			END	 
	END	
	
	ELSE --New Bid on Keyword
	  BEGIN
			--create the keyword bid
			select @keywordPK = k.KeywordPK from Keyword k where k.Keyword = @Keyword
			insert into KeywordBid(KeywordFK,AdvertisingEngineFK,PromotionFK,StartDate,EndDate,IsActive,BidTypeFK,MicroBidAmount,KeywordAdEngineID,CompetitionType,IsDefaultValue)
				VALUES (@keywordPK,@AdEngineID,@PromotionPK,@currentTime,null,1,@BidTypeID,@MicroBidAmount,@KeywordAdEngineID, @CompetitionType,@IsDefaultValue)
			SET @ID = @@IDENTITY	
	  END
	   		
	COMMIT TRANSACTION	
	
	RETURN @ID			 
	
END TRY
BEGIN CATCH
 IF XACT_STATE() != 0 OR @@TRANCOUNT > 0
    ROLLBACK TRANSACTION;
	DECLARE @ErrMessage	nvarchar(4000),
          @ErrorSeverity	int,
          @ErrorState		int;
	SELECT @ErrMessage = ERROR_MESSAGE(), @ErrorSeverity = ERROR_SEVERITY(), @ErrorState = ERROR_STATE();
	RAISERROR (@ErrMessage, @ErrorSeverity, @ErrorState);
END CATCH;
GO