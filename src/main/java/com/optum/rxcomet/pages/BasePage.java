package com.optum.rxcomet.pages;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.optum.rxcomet.reports.ReportManager;
import com.optum.rxcomet.utils.Interactions;
import com.optum.rxcomet.utils.JavaUtils;

public class BasePage 
{
	protected Interactions interactions;
	protected Properties prop;
    protected static ReportManager report;
    protected WebDriver driver;

	public BasePage() 
	{
        interactions = new Interactions();
        report = new ReportManager();
        try {
            prop = JavaUtils.readPropertiesFile(System.getProperty("user.dir")
                    + "/src/test/resources/Objects.properties");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
