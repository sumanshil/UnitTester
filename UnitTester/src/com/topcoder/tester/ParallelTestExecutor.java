package com.topcoder.tester;

public abstract class ParallelTestExecutor
{
    public abstract void execute(TopCoderTesterConcurrent tester);
    public abstract void shutdown();
    public static ParallelTestExecutor getInstance()
    {
    	return TestExecutorUsingExecuteService.doGetInstance();
    }
}
