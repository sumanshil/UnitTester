package com.topcoder.tester;

import java.lang.reflect.Method;

public class TopCoderTesterConcurrent
{
	private String inputString;
	private  Object classObj;
	private  Method method;
	private int index;
	
	public static TopCoderTesterConcurrent getInstance()
	{
		return new TopCoderTesterConcurrent();
	}
	
	public void initialize(String className,
			               String methodName)
			                 throws InstantiationException,
			                        IllegalAccessException,
			                        ClassNotFoundException,
			                        NoSuchMethodException,
			                        SecurityException
	{
		Method[] methods           = Class.forName(className).
		                                   getMethods();
		Method   targetMethod      = null;
		for(Method m : methods)
		{
			if (m.getName().equals(methodName))
			{
				targetMethod = m;
				break;
			}
		}
		
		Class<?>[] parameterTypes  = targetMethod.getParameterTypes();
		Object     obj             = Class.forName(className).newInstance();
		Method     m               = Class.forName(className).
		                                   getMethod(methodName,
				                                     parameterTypes);
		classObj = obj;
		method = m;
	}
	
	
	
	public void test() throws Exception
	{
		System.out.println("Input String" + this.inputString);
		removeLeadingTabs(this.inputString);
		Class<?>[] parameterTypes  = method.getParameterTypes();
		Object[]   inputParameters = processString(parameterTypes,
				                                   this.inputString);
		Object     result          = method.invoke(classObj,
				                                   inputParameters);
		Class<?>   returnType      = method.getReturnType();
		processResult(returnType, result);
	}
	
	
	private void removeLeadingTabs(String inputString)
	{
		for(int i = 0; i < inputString.length() ; i++)
		{
			if (this.inputString.charAt(i) == '\t')
			{
				this.inputString = this.inputString.substring(1);
			}
			else
			{
				break;
			}
		}
		
	}


	private void processResult(Class<?>  returnType,
	                           Object actualResult)
	{
		ParserFactory parser = ParserFactory.getInstance(
				                                        returnType.toString());
		ParseResult   result = parser.parse(this.inputString, index);
		boolean testResult   = parser.compare(result.getResult(),
				                              actualResult);
		if (testResult)
		{
		    System.out.println();
			System.out.println("===> PASSED");
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			sb.append("===> FAILED for input "+this.inputString+"\n");
			sb.append("Actual result "+ actualResult+"\n");
			sb.append("Expected  result "+ result.getResult()+"\n");
			System.out.println(sb.toString());
		}
	}
	
	
	public Object[] processString(Object[] parameterTypes,
			                       String string) throws Exception 
	{
		Object[] retVal   = new Object[parameterTypes.length];
		int    startIndex = 0;
		for(int i = 0 ; i < parameterTypes.length ; i++)
		{
			ParserFactory parser = ParserFactory.getInstance(
			                          parameterTypes[i].toString());
			ParseResult result   = parser.parse(string, startIndex);
			retVal[i]            = result.getResult();
			startIndex           = result.getIndex();
		//	System.out.println(parameterTypes[i]);
		}
		index = startIndex;
		return retVal;
	}

	
	public String getInputString() 
	{
		return inputString;
	}
	
	
	public void setInputString(String inputString) 
	{
		this.inputString = inputString;
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		Parser parser         = Parser.getInstance();
		String className      = parser.next();
		String methodName     = parser.next(); 
        while(parser.hasNext())
        {
        	String line  = parser.next();
        	if (line.length() > 0)
        	{
        		TopCoderTesterConcurrent instance = TopCoderTesterConcurrent.getInstance();
        		instance.initialize(className, methodName);
        		instance.setInputString(line);
        		ParallelTestExecutor.getInstance().execute(instance);
        	}
        }
        ParallelTestExecutor.getInstance().shutdown();
	}

}
