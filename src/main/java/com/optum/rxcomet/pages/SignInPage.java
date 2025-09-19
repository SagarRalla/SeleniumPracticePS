package com.optum.rxcomet.pages;

import org.openqa.selenium.WebDriver;

import com.optum.rxcomet.reports.ReportManager;
import com.optum.rxcomet.utils.Interactions;

public class SignInPage extends BasePage{

	WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public HomePage doEmployeeSignIn() {
        try {
            driver.manage().window().maximize();
            Thread.sleep(5000);
            interactions.click(interactions.getElementByXpath("//button[.//p[text()='Employee Sign in']]"));
            report.info("Clicked on Employee SignIn");
        } catch (Throwable t) {
            report.exception("Unable to click Employee Sign In", t);
        }
        return new HomePage(driver);
    }

    public SignInPage enterMSID(String username) {
        try {
            interactions.clearAndType(interactions.getElementById("username"), username);
            report.info("Entered username as "+username);
        } catch (Throwable t) {
            report.exception("Unable to enter username", t);
        }
        return this;
    }
    
    public SignInPage enterPassword(String password) {
        try {
            interactions.clearAndType(interactions.getElementById("password"), password);
            report.info("Entered password as "+password);
        } catch (Throwable t) {
            report.exception("Unable to enter password", t);
        }
        return this;
    }

    public SignInPage clickSubmit(){
        try {
            interactions.click(interactions.getElementByXpath(".//*[contains(text(),'Submit')]"));
            report.info("Clicked on submit button");
        } catch (Throwable t) {
            report.exception("Unable to click Submit", t);
        }
		return this;
    }
}
