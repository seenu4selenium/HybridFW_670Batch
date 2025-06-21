package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class FramesHandling extends CommonFunctions {

	@Test
	public void f() throws Exception {

		//chromeBrowserLaunch();
		firefoxBrowserLaunch();
		//getURL("framesURL");
		driver.get("https://www.hilton.com/en/");
		implicitWait(10);
		driver.findElement(By.xpath("(//span[text()='Sign In'])[2]")).click();
		implicitWait(10);
		loopAllFramesAndReturnCountofElement(By.xpath("//*[@id=\"username\"]"));
		
		implicitWait(10);
		
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("sdgfdgF");
		
		
		
		

		
		
		
		
		
		//driver.findElement(By.name("P-2Alist")).click();
		
		
	}
}
