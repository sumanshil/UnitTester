package com.topcoder.tester;

import java.util.HashMap;
import java.util.Map;

public abstract class ParserFactory
{
	public enum FactoryType
	{
		INT("int"),
		LONG("long"),
		INT_ARRAY("class [I"),
		STRING("class java.lang.String"),
		STRING_ARRAY("class [Ljava.lang.String;");
		
		private String value;
		
		private FactoryType(String value)
		{
			this.value = value;
		}
		
		public String getValue()
		{
			return value;
		}
	}
	
	private static Map<FactoryType, ParserFactory> map = new HashMap<FactoryType, ParserFactory>(); 
	static 
	{
		map.put(FactoryType.STRING_ARRAY, new StringArrayParserFactory());
		map.put(FactoryType.STRING, new StringParserFactory());
		map.put(FactoryType.INT, new IntegerParserFactory());
		map.put(FactoryType.INT_ARRAY, new IntegerArrayParserFactory());
		map.put(FactoryType.LONG, new LongParserFactory());
	}
	public abstract ParseResult parse(String input, int startIndex);
	public abstract boolean     compare(Object actual, Object expected);
    public static ParserFactory getInstance(String inputType)
    {
    	for( FactoryType type : FactoryType.values())
    	{
    		if (type.getValue().equals(inputType))
    		{
    			return map.get(type);
    		}
    	}
    	return null;
    }
    
    protected boolean isEmptyChar(char c)
    {
    	return c == '\t' || c == ' ';
    }
}
