package com.optum.rxcomet.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.optum.rxcomet.pages.SignInPage;
import com.optum.rxcomet.utils.BaseTest;
import com.optum.rxcomet.utils.Interactions;

public class ImplementationTest extends BaseTest{

	 String filename = "";
	 public String templateNumber = "";
	 public String answerOfSpecialtyInfusion = "No";
	 
	 @Parameters({"clientName", "fileName"})
	 @Test
	 public void headerTest(String clientName, String filename) {
	     this.filename = filename;
	     SignInPage signInPage = new SignInPage(Interactions.driver);
//	     signInPage.doEmployeeSignIn();
//	             .clickActive()
//	             .typeValueToSearch(clientName)
//	             .openSearchedFirstClient()
//	             .validateClientName(filename,clientName)
//	             .validateHeader(filename)
//	             .validateGoLiveDate(filename);
	 }
}
