package com.testscenarios;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class AutomationTest_NewReg extends CommonFunctions{
  @Test
  public void f() throws Exception {
	  chromeBrowserLaunch();
	  getURL("AE_URL");
	  Thread.sleep(3000);
	  clickByAnyLocator(loc.AE_signup_click);
	  //Thread.sleep(3000);
	  sendKeysByAnyLocator(loc.AE_Name_Enter,"AE_NAME");
	  sendKeysByAnyLocator(loc.AE_EmailAddress,"AE_EMAIL");
	  clickByAnyLocator(loc.AE_Signup);
	
  }
}
