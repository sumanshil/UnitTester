package com.topcoder.tester;

import java.util.LinkedList;
import java.util.List;


public class StringArrayParserFactory extends ParserFactory
{

	@Override
	public ParseResult parse(String input, int startIndex) 
	{
		int braceStartAt = input.indexOf("{", startIndex);
		int braceEndsAt  = input.indexOf("}", braceStartAt+1);
		
		if (braceStartAt == -1 || braceEndsAt == -1)
		{
			// throw exception
		}
		
		if (braceStartAt > braceEndsAt)
		{
			// throw exception
		}
		List<String> list    = new LinkedList<String>();
		StringBuffer sb      = new StringBuffer();
		boolean elementFound = false;
		for(int i = braceStartAt ; i <= braceEndsAt ; i++)
		{
			if (input.charAt(i) == '"')
			{
				if (elementFound)
				{
					// we found one element
					list.add(sb.toString());
					sb = new StringBuffer();
					elementFound = false;
				}
				else
				{
					elementFound = true;					
				}
				continue;
			}
			if(elementFound)
			{
			    sb.append(input.charAt(i));
			}
		}
		
		return new TopCoderTestParseResult((String[])list.toArray(new String[0]), braceEndsAt+1);
	}

	@Override
	public boolean compare(Object actual, Object expected) 
	{
		String[] actualArr   = (String[])actual;
		String[] expectedArr = (String[])expected;
		if (actualArr.length != expectedArr.length)
		{
			return false;
		}
		for(int i = 0 ; i < actualArr.length ; i++)
		{
			if (!actualArr[i].equals(expectedArr[i]))
			{
				return false;
			}
		}
		return true;
	}
}
