package com.topcoder.tester;

import java.lang.reflect.Method;

public class TopCoderTesterBackup
{
	private String className;
	private String methodName;
	private String inputString;
	private int    index;
	public TopCoderTesterBackup(String className, 
			              String methodName)
	{
		this.className  = className;
		this.methodName = methodName;
	}
	
	
	public void test() throws Exception
	{
		System.out.println("Input String" + this.inputString);
		removeLeadingTabs(this.inputString);
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
		Object[]   inputParameters = processString(parameterTypes,
				                                   this.inputString);
		Object     obj             = Class.forName(className).newInstance();
		Method     m               = Class.forName(className).
		                                   getMethod(methodName,
				                                     parameterTypes);
		
		Object     result          = m.invoke(obj, inputParameters);
		Class<?>   returnType      = targetMethod.getReturnType();
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
	                           Object expectedResult)
	{
		ParserFactory parser = ParserFactory.getInstance(
				                                        returnType.toString());
		ParseResult   result = parser.parse(this.inputString, index);
		boolean testResult   = parser.compare(result.getResult(),
				                              expectedResult);
		if (testResult)
		{
			System.out.println("===> PASSED");
		}
		else
		{
			System.out.println("===> FAILED");
			System.out.println("Expected result "+ expectedResult);
			System.out.println("Actual   result "+ result.getResult());
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
			System.out.println(parameterTypes[i]);
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
        TopCoderTester tester = new TopCoderTester(className, methodName);
        while(parser.hasNext())
        {
        	String line  = parser.next();
        	if (line.length() > 0)
        	{
        	    tester.setInputString(line);
        	    tester.test();
        	}
        }
	}

}
