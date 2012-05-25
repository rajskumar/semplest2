package semplest.server.service.springjdbc.storedproc;

import java.sql.Types;
import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import semplest.server.service.springjdbc.BaseDB;

public class AddScheduleJobSP extends StoredProcedure
{
	private static final String SPROC_NAME = "AddScheduleJob";
	
	public AddScheduleJobSP()
	{
		super(BaseDB.jdbcTemplate.getDataSource(), SPROC_NAME);
		declareParameter(new SqlParameter("ScheduleFK", Types.INTEGER));
		declareParameter(new SqlParameter("ExecutionStartTime", Types.TIMESTAMP));
		declareParameter(new SqlOutParameter("ID", Types.INTEGER));
		compile();
	}

	/*
	 * returns the next schedule job to run
	 */
	public Integer execute(int ScheduleFK, Date ExecutionStartTime)
	{
		Map<String, Object> results = super.execute(ScheduleFK, ExecutionStartTime);
		if (results.get("ID") == null)
		{
			return null;
		}
		else
		{
			Integer res = (Integer) results.get("ID");
			if (res == null)
			{
				return null;
			}
			else
			{
				return res;
			}
		}
	}
}