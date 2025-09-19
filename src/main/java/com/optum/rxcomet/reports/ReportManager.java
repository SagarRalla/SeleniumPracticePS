package com.optum.rxcomet.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.optum.rxcomet.utils.FilePathUtils;
import com.optum.rxcomet.utils.Interactions;
import com.optum.rxcomet.utils.JavaUtils;

public class ReportManager {
	
	public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;

	public static void initializeReport() throws IOException 
	{
		//Deletes the entire "reports" folder from your working directory before creating new
        FileUtils.deleteDirectory(new File(FilePathUtils.getWorkingDirectoryPath()+"/reports"));
        extentReports = new ExtentReports();
        
        //SparkReporter will generate the report in HTML format
        extentSparkReporter = new ExtentSparkReporter(
                FilePathUtils.getWorkingDirectoryPath()+"/reports/reports.html");
        
        //Attaches the SparkReporter to the ExtentReports object means HTML file will be used to display the report.
        extentReports.attachReporter(extentSparkReporter);
        
        //Sets the visual theme of the report to STANDARD(clean and professional look)
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        
        //Sets the title of the HTML document
        extentSparkReporter.config().setDocumentTitle("Excel Report validation");
        
        //Sets the name of the report
        extentSparkReporter.config().setReportName("Automation Report");
    }
	
	//It creates new test case in report with the name you pass
	public static void createTest(String testcaseName)
	{
	    ExtentManager.setExtentTest(extentReports.createTest(testcaseName));
	}

	//It logs a pass message to the report.
	public static void pass(String log)
	{
	    System.out.println(log);
	    ExtentManager.getExtentTest().pass(log);
	}

	//It logs an info message
	public static void info(String log)
	{
	    System.out.println(log);
	    ExtentManager.getExtentTest().info(log);
	}
	
	public static void fail(String log){
        try {
            System.out.println(log);
            ExtentTest test = ExtentManager.getExtentTest();
            TakesScreenshot ts = (TakesScreenshot) Interactions.driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            String name = FilePathUtils.getWorkingDirectoryPath() +"/reports/"+JavaUtils.generateRandomNumber()+".png";
            FileUtils.copyFile(file, new File(name));
            
            //It logs the failure message and attach SS in report using MediaEntityBuilder
            test.fail(log,  MediaEntityBuilder.createScreenCaptureFromPath(name).build());
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static void exception(String log, Throwable exceptionError){
        try {
            System.out.println(log);
            System.out.println(exceptionError);
            ExtentTest test = ExtentManager.getExtentTest();
            TakesScreenshot ts = (TakesScreenshot) Interactions.driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            String name = FilePathUtils.getWorkingDirectoryPath() +"/reports/"+ JavaUtils.generateRandomNumber() + ".png";
            FileUtils.copyFile(file, new File(name));
            test.fail(log);
            test.fail(exceptionError, MediaEntityBuilder.createScreenCaptureFromPath(name).build());
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    public static void saveReport(){
        extentReports.flush();
    }
}
