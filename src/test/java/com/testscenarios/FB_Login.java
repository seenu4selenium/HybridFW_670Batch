package com.testscenarios;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.objectrepository.Locators;

public class FB_Login {
	WebDriver driver;
	
	// Here will define the object for Locators class
	Locators loc = new Locators();
	

	@Test
	public void f() throws Exception {

		// Create object for PROPERTIES class from JAVA
		Properties prop = new Properties();
		// Give property file path to the system
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_Environment.properties");
		// Load the test data to property file variable
		prop.load(fi);

		
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(prop.getProperty("FB_URL"));

		Thread.sleep(2000);

		driver.findElement(loc.fbLogin_ForgotPassword_Link).click();
		Thread.sleep(2000);

		driver.findElement(loc.fbLogin_Email_editBox).sendKeys(prop.getProperty("FB_UN"));

	}
}
