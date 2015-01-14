package com.topcoder.tester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.function.Consumer;



public class Parser implements Iterator<String>
{
    private static String fileName = "C:/Users/sshil.ORADEV/Downloads/TopCoder/src/com/topcoder/tester/TestFile.txt";
    private static String targetFileName = "C:/Users/sshil.ORADEV/Downloads/TopCoder/src/com/topcoder/tester/TargetTestFile.txt";
    private BufferedReader reader;
    private BufferedWriter writer;
    private String nextLine;
    public final static String INPUT_SEPARATOR = "$";    
    public final static String INPUT_OUTPUT_SEPARATOR = "@";
    public final static String ARR_ELEM_SEPARATOR = "&";
    public static Parser getInstance() throws IOException
    {
    	return new Parser(fileName);
    }
    
    
    
    private Parser(String fileName) throws IOException
    {
    	this.reader   = new BufferedReader(new InputStreamReader
    			                          (new FileInputStream
    			                          (new File(fileName))));
    	this.writer   = new BufferedWriter(new FileWriter
    			                          (new File(targetFileName)));    	
    	nextLine      = reader.readLine();
//    	preprocess();
//    	reader.close();
//		writer.close();
//    	reader        = new BufferedReader(new InputStreamReader
//                                          (new FileInputStream
//                                          (new File(targetFileName))));
//    	nextLine      = reader.readLine();
    }
    
    
    private void preprocess() throws IOException 
    {
		this.writer.write(this.next()+"\n");
		this.writer.write(this.next()+"\n");
		while(this.hasNext())
		{
			String str = this.next();
			if (str.length() == 0)
				continue;
			boolean commaFound  = true;
			boolean insideWord = false; 
			boolean insideArray = false;
			StringBuffer sb     = new StringBuffer();
			char prevChar='@';
			for(int i = 0 ; i < str.length() ; i++)
			{

				if (commaFound && (str.charAt(i) == ' '||
						           str.charAt(i) == '\t'))
				{
					continue;
				}
                if (str.charAt(i) == ',')
                {
                	// comma found. This may be a array element separator
                	// in this case do nothing
                	if (insideWord)
                	{
                		// NO OP
                	}
                	else if (!insideWord
                			&& insideArray)
                	{
                		commaFound = true;
                	    sb.append(ARR_ELEM_SEPARATOR);
                	    continue;
                	}
                	else if (!insideWord
                			&& !insideArray)
                	{
                		commaFound = true;
                	    sb.append(INPUT_SEPARATOR);
                	    continue;
                	}
                	
                }
                else
                {
                	commaFound = false;
                }
                
                if (str.charAt(i) == '"')
                {
                	if (!insideWord)
                	{
                		insideWord = true;                		
                	}
                	else
                	{
                		insideWord = false;
                	}
                }
                
				
				if(!isEmptyCharacter(str.charAt(i))
					&& isEmptyCharacter(prevChar)
					&& !commaFound
					&& !insideArray)
				{
					//input output separator
					sb.append(INPUT_OUTPUT_SEPARATOR);
					sb.append(str.charAt(i));
				}
				else if (isEmptyCharacter(str.charAt(i))
					     && isEmptyCharacter(prevChar))
				{
					//NO OP
				}
				else if (isEmptyCharacter(str.charAt(i))
						&& !isEmptyCharacter(prevChar)
						&& !commaFound
						&& !insideArray)
				{
					//NO OP
				}
				else
				{
					sb.append(str.charAt(i));	
				}
				
                if (str.charAt(i) == '{' )
                {
                	insideArray = true;
                }
                if ( str.charAt(i) == '}')
                {
                	if (commaFound)
                	{
                		commaFound = false;
                	}
                	insideArray = false;
                }

				prevChar = str.charAt(i);
			}
			
			this.writer.write(sb.toString()+"\n");
		}		
	}
    
    private boolean isEmptyCharacter(char c)
    {
    	return c == '\t' || c ==' ';
    }

	public void close() throws IOException
    {
    	reader.close();
    }

    
	public boolean hasNext() 
	{		
	    return nextLine != null;
	}


	public String next() 
	{
	    String temp = nextLine;
	    try
	    {
			nextLine    = reader.readLine();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	    return temp;
	}


	public void remove() {
		// TODO Auto-generated method stub
		
	}
    
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		Parser parser = new Parser("C:/Users/sshil.ORADEV/Downloads/TopCoder/src/com/topcoder/tester/TestFile.txt");
		while(parser.hasNext())
		{
			System.out.println(parser.next());
		}
        parser.close();
	}



	public void forEachRemaining(Consumer<? super String> arg0) {
		// TODO Auto-generated method stub
		
	}


}
