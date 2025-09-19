package com.optum.rxcomet.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	//ThreadLocal is a Java class that allows you to store data separately for each thread
	//It creates a thread-local variable to hold the ExtentTest object for each thread
	private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
	
	//This method retrieves the ExtentTest object for the current thread
	public static ExtentTest getExtentTest()
	{
	    return extentTestThreadLocal.get();
	}
	
	//This method sets the ExtentTest object for the current thread
	public static void setExtentTest(ExtentTest test)
	{
	    extentTestThreadLocal.set(test);
	}
}
