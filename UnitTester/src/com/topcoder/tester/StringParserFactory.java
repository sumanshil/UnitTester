package com.topcoder.tester;

public class StringParserFactory extends ParserFactory 
{
	@Override
	public ParseResult parse(String input, int startIndex) 
	{
		//TODO recognize the pattern """"
		// we have two cases where string starts with "
		// or we have an array of strings which is considered to be a whole string
		// we will not support second case as of now
		// example of second case is com.topcoder.problems.round13.WordFinder
		int count = 0 ;
		int j;
		boolean isValidString = false;
		for(j = startIndex ; j < input.length() ; j++)
		{
			if (input.charAt(j) == '"')
			{
				count++;
			}
			else if (input.charAt(j) != '"'
				     &&input.charAt(j) != '\t')
			{
				isValidString = true;
				break;
			}
			else if (input.charAt(j) == '\t')
			{
				break;
			}
		}
		
		if (!isValidString && 
				count == j)
		{
			// """""" case
			return new TopCoderTestParseResult(input.substring(startIndex, startIndex+count), j);
		}
		
		int startAt = input.indexOf('"', startIndex);
		int endsAt  = input.indexOf('"', startAt+1);
		
		if (startAt == -1 || endsAt == -1)
		{
			// throw exception
		}
		
		if (startAt > endsAt)
		{
			// throw exception
		}
		StringBuffer sb      = new StringBuffer();
		for(int i = startAt+1 ; i < endsAt ; i++)
		{
			sb.append(input.charAt(i));			
		}
		
		return new TopCoderTestParseResult(sb.toString(), endsAt+1);
	}


	@Override
	public boolean compare(Object actual, Object expected)
	{		
		return ((String)actual).equals((String)expected);
	}
	
}
