package com.optum.rxcomet.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.optum.rxcomet.reports.ReportManager;

public class BaseTest {

	protected Interactions interactions = new Interactions();
	
	@BeforeSuite
    public void reportSetup() throws IOException {
        ReportManager.initializeReport();
    }
	
	@Parameters("testCaseName")
    @BeforeClass
    public void setup(String testCaseName) throws MalformedURLException {
        ReportManager.createTest(testCaseName);
        interactions.launchBrowser("chrome");			
    }
	
	@AfterClass
    public void quitBrowser() {
        interactions.quitDriver();

    }

    @AfterSuite
    public void endReport() {
        ReportManager.saveReport();
    }
}
