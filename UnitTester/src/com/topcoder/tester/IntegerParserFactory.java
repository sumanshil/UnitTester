package com.topcoder.tester;

public class IntegerParserFactory extends NumberParserFactory 
{


	@Override
	public boolean compare(Object actual, Object expected)
	{
		return ((Integer)actual).equals((Integer)expected);
	}

	@Override
	protected Object convertStringToNumeric(String string) 
	{		
		return Integer.parseInt(string);
	}

}
