package semplest.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import semplest.server.service.ServiceInterface;

public class TestService2 implements ServiceInterface
{

	private static final Logger logger = Logger.getLogger(TestService2.class);
	@Override
	public String ServiceGet(String methodName, String jsonStr) throws Exception
	{
		
			logger.debug("Running test service " + methodName + ":" + jsonStr);
			TestService2Impl service = new TestService2Impl();
			Class[] parameterTypes = new Class[] {String.class};
			Method method = service.getClass().getMethod(methodName, parameterTypes);
			return (String) method.invoke(service,jsonStr);
	}
	
	

}