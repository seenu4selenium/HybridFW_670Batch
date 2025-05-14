package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.objectrepository.Locators;

public class CommonFunctions {

	public static WebDriver driver;
	public Locators loc = new Locators();
	public Properties prop = new Properties();

	// Browser Launch
	public void chromeBrowserLaunch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void firefoxBrowserLaunch() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public void edgeBrowserLaunch() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	public void safariBrowserLaunch() {
		driver = new SafariDriver();
		driver.manage().window().maximize();
	}

	// Type ULR into Browser
	public void getURL(String URL) throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_Environment.properties");
		prop.load(fi);
		driver.get(prop.getProperty(URL));

	}

	/*
	 * Click on button/radiobutton/hyperlink/image/checkbox
	 * 
	 */
	public void clickByAnyLocator(By locator) {
		// verify given locator is displayed on screen?
		if (driver.findElements(locator).size() > 0) {
			System.out.println("Given element is displayed on screen****");
			// Check the given locator is enable state?
			if (driver.findElement(locator).isEnabled()) {
				driver.findElement(locator).click();
			} else {
				System.out.println("Given element is in disable state*****");
			}
		} else {
			System.out.println("Given element is NOT displayed on screen****");
		}
	}

	/*
	 * Sendkey()
	 */

	public void sendkeysByAnyLocator(By locator, String inputdata) throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\QA_Environment.properties");
		prop.load(fi);

		if (driver.findElements(locator).size() > 0) {
			System.out.println("Given element is displayed on screen****");
			// Check the given locator is enable state?
			if (driver.findElement(locator).isEnabled()) {
				driver.findElement(locator).sendKeys(prop.getProperty(inputdata));
			} else {
				System.out.println("Given element is in disable state*****");
			}
		} else {
			System.out.println("Given element is NOT displayed on screen****");
		}
	}
	
	
	

}
