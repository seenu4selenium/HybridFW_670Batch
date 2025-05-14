package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.objectrepository.Locators;

public class Automationexercise_Login {
	WebDriver driver;
	Locators loc = new Locators();
	Properties prop = new Properties();
	
  @Test
  public void f() throws Exception {
	  FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_Environment.properties");
	  prop.load(fi);
	  
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();	  
	  driver.get(prop.getProperty("AE_URL"));
	  Thread.sleep(3000);	  
	  
	  //Verify the web element is present on screen or not?
	  if(driver.findElements(loc.aeHomePage_Signup_MenuLink).size()>0) {
		  
	  }
	  
	  
	  driver.findElement(loc.aeHomePage_Signup_MenuLink).click();
	  
	  
	  Thread.sleep(3000);	  
	  //Type dummy Credentials under "Login to your account" form
	  driver.findElement(loc.aeHomePage_email_editBox).sendKeys(prop.getProperty("AE_UN"));
	  driver.findElement(loc.aeHomePage_password_editBox).sendKeys(prop.getProperty("AE_PWSD"));
	  //Click on Login button
	  driver.findElement(loc.aeHomePage_login_button).click();
	  Thread.sleep(3000);
	  //Get the error message and print into Console. 
	  String errMsg = driver.findElement(loc.aeHomePage_Error_message).getText();
	  System.out.println(errMsg);	  
	  
  }
}
