package com.topcoder.tester;

public abstract class NumberParserFactory extends ParserFactory {
    protected abstract Object convertStringToNumeric(String string);
	@Override
	public ParseResult parse(String input, int startIndex)
	{
		int start = -1;
		for(int i = startIndex ; i < input.length() ; i++)
		{
			if( input.charAt(i) == '-' 
				|| 
                (input.charAt(i) >= '0'
				&& input.charAt(i) <= '9'))   
			{
				start = i;
				break;
			}
		}
		
		if (start == -1)
		{
			// throw exception
		}
		StringBuffer sb = new StringBuffer();
		Object result = 0;
		int indexRead = 0;
		for (int j = start ; j < input.length() ; j++ )
		{
			if (input.charAt(j) == ','
				|| isEmptyChar(input.charAt(j)))
			{
				result    = convertStringToNumeric(sb.toString().trim());
				indexRead = j;
				break;
			}
			sb.append(input.charAt(j));
		}
		if (sb.toString().length() > 0)
		{
			result = convertStringToNumeric(sb.toString().trim());
		}
		return new TopCoderTestParseResult(result, indexRead+1);
	}

	@Override
	public abstract boolean compare(Object actual, Object expected);

}
