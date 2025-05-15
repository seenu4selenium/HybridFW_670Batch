package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class Automationexercise_Login extends CommonFunctions {
	
  @Test
  public void f() throws Exception {
	  chromeBrowserLaunch();
	  getURL("AE_URL");
	  Thread.sleep(3000);
	  clickByAnyLocator(loc.aeHomePage_Signup_MenuLink);
	  Thread.sleep(3000);
	  sendKeysByAnyLocator(loc.aeHomePage_email_editBox, "AE_UN");
	  sendKeysByAnyLocator(loc.aeHomePage_password_editBox, "AE_PWSD");
	  clickByAnyLocator(loc.aeHomePage_login_button);	
	  Thread.sleep(3000);
	  getTextFromElement(loc.aeHomePage_Error_message);	  
  }
}
