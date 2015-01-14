package com.topcoder.tester;

import java.util.LinkedList;
import java.util.List;

public class IntegerArrayParserFactory extends ParserFactory 
{
	@Override
	public ParseResult parse(String input, int startIndex) 
	{
		int braceStartAt = input.indexOf("{", startIndex);
		int braceEndsAt  = input.indexOf("}", braceStartAt);
		
		if (braceStartAt == -1 || braceEndsAt == -1)
		{
			// throw exception
		}
		
		if (braceStartAt > braceEndsAt)
		{
			// throw exception
		}
		List<Integer> list    = new LinkedList<Integer>();
		StringBuffer sb      = new StringBuffer();
		for(int i = braceStartAt+1 ; i < braceEndsAt ; i++)
		{
			if (input.charAt(i) == ',')
			{
				// we found one element
				list.add(Integer.parseInt(sb.toString().trim()));
				sb = new StringBuffer();
				continue;
			}
        	sb.append(input.charAt(i));
		}
		if (sb.toString().length() > 0)
		{
			list.add(Integer.parseInt(sb.toString().trim()));
		}
		Integer[] arr = (Integer[])list.toArray(new Integer[0]);
		int[] arr1 = new int[arr.length];
		for(int i = 0 ; i < arr.length ; i++)
		{
			arr1[i] = arr[i];
		}
		return new TopCoderTestParseResult(arr1, braceEndsAt+1);
	}

	@Override
	public boolean compare(Object actual, Object expected) 
	{
		int[] actualArr   = (int[])actual;
		int[] expectedArr = (int[])expected;
		if (actualArr.length != expectedArr.length)
		{
			return false;
		}
		for(int i = 0 ; i < actualArr.length ; i++)
		{
			if (actualArr[i] != expectedArr[i])
			{
				return false;
			}
		}
		return true;
	}

}
