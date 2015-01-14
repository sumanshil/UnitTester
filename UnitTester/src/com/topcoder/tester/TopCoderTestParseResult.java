package com.topcoder.tester;

public class TopCoderTestParseResult implements ParseResult {
    private Object result = null;
    private int    index  = 0;
    public TopCoderTestParseResult(Object result, int index)
    {
    	this.result = result;
    	this.index  = index;
    }
	public Object getResult() 
	{
		return result;
	}

	public int getIndex() 
	{
		return index;
	}

}
