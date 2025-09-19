package com.optum.rxcomet.pages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.optum.rxcomet.utils.ExcelUtils;
import com.optum.rxcomet.utils.JavaUtils;

public class HomePage extends BasePage{

	 WebDriver driver;

	 public HomePage(WebDriver driver) {
	     this.driver = driver;
	 }
	 
	 public HomePage clickActive() {
	     try {
	         Thread.sleep(5000);
	         interactions.click(interactions.getElementByXpath(".//*[text()='Active']"));
	         report.info("Clicked on Active Tag option");
	     } catch (Throwable t) {
	         report.exception("Unable to click on Active Tag", t);
	     }
	     return this;
	 }
	 
	 public HomePage typeValueToSearch(String valueToSearch) {
	     try {
	         interactions.clearAndType(interactions.getElementById("searchFiled"), valueToSearch);
	         interactions.click(interactions.getElementByCssSelector(".MuiButtonBase-root.MuiIconButton-root"));
	         report.info("Entered search value as " + valueToSearch);
	     } catch (Throwable t) {
	         report.exception("Unable to enter value in search field", t);
	     }
	     return this;
	 }
	 
	 public HomePage openSearchedFirstClient() {
	     try {
	         String xpath = ".//*[contains(@class,'MuiDivider-root')]" +
	                 "/following::*[contains(@class, 'MuiPaper-elevation1')]";
	         interactions.click(interactions.getElementByXpath(xpath));
	         report.info("Clicked on First result from search items");
	     } catch (Throwable t) {
	         report.exception("Unable to click first search item", t);
	     }
	     return this;
	 }
	 
	 public HomePage validateClientName(String fileName, String clientName) {
	     try {
	         Assert.assertEquals(getClientName(clientName), ExcelUtils.getAnswer(fileName, "Client Name"));
	         report.pass("Client name validated as excel report.");
	     } catch (Throwable t) {
	         t.printStackTrace();
	         report.exception("Client Name is incorrect ", t);
	     }
	     return this;
	 }
	 
	 public HomePage validateGoLiveDate(String fileName) {
	     try {
	         String date = JavaUtils.dateFormat("MM/dd/yyyy", "MMM dd, yyyy",
	                 ExcelUtils.getAnswer(fileName, "Go live date"));
	         Assert.assertEquals(getHeaderValue("Go Live Date"), date);
	         report.pass("Go Live Date validated as excel report.");
	     } catch (Throwable t) {
	         t.printStackTrace();
	         report.exception("Go Live Date is incorrect ", t);
	     }
	     return this;
	 }
	    
	 public HomePage validateHeader(String fileName) {
	     try {
	         Map<String, String> map = new LinkedHashMap<>();

	         map.put("Carrier ID", "Carrier ID");
	         map.put("Platform", "Adjudication Platform");
	         map.put("IPM", "Implementation Manager");
	         //map.put("", "Export Generated Date");
             for (Map.Entry<String, String> m : map.entrySet()) {
	             Assert.assertEquals(getHeaderValue(m.getKey()), ExcelUtils.getAnswer(fileName, m.getValue()));
	             report.pass(m.getKey() + " validated against excel report.");
	         }
	     } catch (Throwable t) {
	         t.printStackTrace();
	         report.exception("Exception occurred while validating header part", t);
	     }
	     return this;
	 }
	 
	 public String getClientName(String clientName) {
	     return interactions.getText(interactions.getElementByXpath(
	        	"(//div[contains(@class,'MuiBox-root') and contains(text(), '"+clientName+"')])")); 
	 }
	 
	 public String getHeaderValue(String headerLabel) {
	     return interactions.getText(interactions.getElementByXpath(
	             ".//*[contains(text(), '" + headerLabel + "')]/following-sibling::span"));
	 }
}
