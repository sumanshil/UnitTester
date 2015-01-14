package com.topcoder.tester.junit;

import org.junit.Assert;
import org.junit.Test;

import com.topcoder.tester.ParseResult;
import com.topcoder.tester.ParserFactory;


public class ParserFactoryTest 
{
   @Test
   public void testParserStringArrayStringArrayStringString()
   {
       String input = "{\"A, B\", \"K, A\", \"X, B\", \"R, G\", \"S, K\", \"Z, R\", \"K, I\", \"G, H\", \"B, Z\", \"U, O\", \"S, M\"}, {\"A, B\", \"K, A\", \"Y, C\"}, \"S\", \"K\"";
       ParserFactory parser = ParserFactory.getInstance(ParserFactory.FactoryType.STRING_ARRAY.getValue());
       ParseResult result = parser.parse(input, 0);
       String[] result1   = (String[])result.getResult();
       Assert.assertEquals(11, result1.length);

       parser = ParserFactory.getInstance(ParserFactory.FactoryType.STRING_ARRAY.getValue());
       result = parser.parse(input, result.getIndex());
       result1   = (String[])result.getResult();
       Assert.assertEquals(3, result1.length);
       
       parser = ParserFactory.getInstance(ParserFactory.FactoryType.STRING.getValue());
       result = parser.parse(input, result.getIndex());
       Assert.assertTrue(result.getResult() instanceof String);
       Assert.assertTrue("S".equals(result.getResult()));
       
       parser = ParserFactory.getInstance(ParserFactory.FactoryType.STRING.getValue());
       result = parser.parse(input, result.getIndex());
       Assert.assertTrue(result.getResult() instanceof String);
       Assert.assertTrue("K".equals(result.getResult()));       
   }
   
   @Test
   public void testStringStringString()
   {
	   String input = "\"aA\", \"Aa\", \"A\"";
	   ParserFactory factory = ParserFactory.getInstance(ParserFactory.FactoryType.STRING.getValue());
	   ParseResult result = factory.parse(input, 0);
	   Assert.assertEquals("aA", result.getResult());
	   
	   result = factory.parse(input, result.getIndex());
	   Assert.assertEquals("Aa", result.getResult());

	   result = factory.parse(input, result.getIndex());
	   Assert.assertEquals("A", result.getResult());
   }
   
   @Test
   public void testIntArrayInt()
   {
        String input = "{2, 3, 5, 7, 11, 13, 17, 23}, 29";
        ParserFactory factory = ParserFactory.getInstance(ParserFactory.FactoryType.INT_ARRAY.getValue());
        ParseResult result = factory.parse(input, 0);
        Integer[] array    = (Integer[])result.getResult();
        Assert.assertEquals(8, array.length);
        
        factory = ParserFactory.getInstance(ParserFactory.FactoryType.INT.getValue());
        result = factory.parse(input, result.getIndex());
        Integer intNumber = (Integer)result.getResult();
        Assert.assertEquals((Object)29,(Object)intNumber);
   }
}
