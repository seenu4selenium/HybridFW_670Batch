package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.objectrepository.Locators;

public class Automationexercise_LoginWihtValidtestdata {
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

		// verify given locator is displayed on screen?
		if (driver.findElements(loc.aeHomePage_Signup_MenuLink).size() > 0) {
			System.out.println("Given element is displayed on screen****");
			// Check the given locator is enable state?
			if (driver.findElement(loc.aeHomePage_Signup_MenuLink).isEnabled()) {
				driver.findElement(loc.aeHomePage_Signup_MenuLink).click();
			} else {
				System.out.println("Given element is in disable state*****");
			}
		} else {
			System.out.println("Given element is NOT displayed on screen****");
		}

		Thread.sleep(3000);
		// Type dummy Credentials under "Login to your account" form

		// verify given locator is displayed on screen?
		// Check the given locator is enable state?
		// do clear the existing data?
		driver.findElement(loc.aeHomePage_email_editBox).sendKeys(prop.getProperty("AE_Valid_UN"));
		driver.findElement(loc.aeHomePage_password_editBox).sendKeys(prop.getProperty("AE_Valid_PWSD"));
		// Click on Login button
		driver.findElement(loc.aeHomePage_login_button).click();
		Thread.sleep(3000);

		// Verify Sreeni name/text has displayed in next the screen?
		if (driver.findElements(loc.aeHomePage_Sreeni_User).size() > 0) {
			System.out.println("Sreeni text is presents on screen****");
		} else {
			System.out.println("Sreeni text is NOT presents on screen********************");
		}

	}
}
