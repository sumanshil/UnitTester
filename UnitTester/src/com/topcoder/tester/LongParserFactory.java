package com.topcoder.tester;

public class LongParserFactory extends NumberParserFactory {

	@Override
	protected Object convertStringToNumeric(String string) 
	{
		return Long.parseLong(string);
	}

	@Override
	public boolean compare(Object actual, Object expected) 
	{
		return ((Long)actual).equals((Long)expected);
	}

}
