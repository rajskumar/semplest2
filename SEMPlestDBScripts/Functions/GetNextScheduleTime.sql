USE [semplest]
GO
/****** Object:  UserDefinedFunction [dbo].[GetNextScheduleTime]    Script Date: 04/20/2012 20:39:51 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Mitch
-- Create date: 4/20/2012
-- Description:	Gets Next Time To Run
-- =============================================
ALTER FUNCTION [dbo].[GetNextScheduleTime] 
(
	-- Add the parameters for the function here
	@StartDateTime DATETIME,
	@Frequency VARCHAR(25)
)
RETURNS DATETIME
AS
BEGIN
	-- Declare the return variable here
	DECLARE @Result DATETIME, @currentDate DATETIME, @date DATETIME
	DECLARE @WeekDay INT,@i INT;
	DECLARE @TodaysDay INT,@numWeeks INT, @dayOfWeekToRun INT
	DECLARE @MaxDaysInCurrentMonth INT,@afterStart BIT
	
	SET @currentDate = CURRENT_TIMESTAMP
	SET @currentDate = DATEADD(s, 60, @currentDate)		--Add 60 sec to compasate for time synchronisation between 2 servers
		
	IF (@Frequency = 'Now')
	BEGIN
		SELECT @Result = @currentDate
	END
	ELSE IF (@Frequency = 'Once')
	BEGIN
		--if start date is in the future
		IF (DATEDIFF(s,@currentDate,@StartDateTime) > 0)
		BEGIN
			SELECT @Result = @StartDateTime
		END
		ELSE --run now
		BEGIN
			SELECT @Result = @currentDate
		END 
	END	
	--TEST
	ELSE IF (@Frequency = 'TenMinutes')
	BEGIN
		--if start date is in the future
		SET @result = DATEADD(mm,10,@StartDateTime);
	END
	ELSE IF (@Frequency = 'Daily')
	BEGIN
		--if start date is in the future
		SET @result = DATEADD(dd,1,@StartDateTime);
	END
	ELSE IF (@Frequency = 'Weekly')
	BEGIN
		SET @result = DATEADD(dd,7,@StartDateTime);
	END
	ELSE IF (@Frequency = 'Monthly')
	BEGIN
		SET @Result = DATEADD(mm,1,@StartDateTime)		
	END
	-- Return the result of the function
	RETURN @Result

END
