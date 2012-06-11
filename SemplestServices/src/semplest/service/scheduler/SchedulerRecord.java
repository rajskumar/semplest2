package semplest.service.scheduler;

public class SchedulerRecord
{
// CustomerID, Integer ProductGroup, Integer PromotionID, String StartTime
	private Integer scheduleJobID;
	private Integer scheduleID;
	private Long timeToRunInMS;
	private Boolean IsDelete = false;
	public Integer getScheduleID()
	{
		return scheduleID;
	}
	public void setScheduleID(Integer scheduleID)
	{
		this.scheduleID = scheduleID;
	}
	public Long getTimeToRunInMS()
	{
		return timeToRunInMS;
	}
	public void setTimeToRunInMS(Long timeToRunInMS)
	{
		this.timeToRunInMS = timeToRunInMS;
	}
	public Boolean getIsDelete()
	{
		return IsDelete;
	}
	public void setIsDelete(Boolean isDelete)
	{
		IsDelete = isDelete;
	}
	public Integer getScheduleJobID()
	{
		return scheduleJobID;
	}
	public void setScheduleJobID(Integer scheduleJobID)
	{
		this.scheduleJobID = scheduleJobID;
	}
	@Override
	public String toString() {
		return "SchedulerRecord [scheduleJobID=" + scheduleJobID
				+ ", scheduleID=" + scheduleID + ", timeToRunInMS="
				+ timeToRunInMS + ", IsDelete=" + IsDelete + "]";
	}
	
}