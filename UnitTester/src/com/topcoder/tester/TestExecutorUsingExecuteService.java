package com.topcoder.tester;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutorUsingExecuteService extends ParallelTestExecutor
{
	private static ExecutorService executors =
			Executors.newFixedThreadPool(
					Runtime.getRuntime().availableProcessors());
	
	private static ParallelTestExecutor thisInstance =
			new TestExecutorUsingExecuteService();
	@Override
	public void execute(final TopCoderTesterConcurrent tester)
	{
		executors.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					tester.test();
				} 
				catch (Exception e)
				{				
					e.printStackTrace();
				}				
			}
		});
	}
	@Override
	public void shutdown()
	{
	    executors.shutdown();	
	}
	
	public static  ParallelTestExecutor doGetInstance()
	{
		return thisInstance;
	}

}
