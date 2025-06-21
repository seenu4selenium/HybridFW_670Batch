package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class FramesHandling2 extends CommonFunctions {

	@Test
	public void f() throws Exception {

		//chromeBrowserLaunch();
		firefoxBrowserLaunch();
		getURL("framesURL");		
		implicitWait(10);		
		loopAllFramesAndReturnCountofElement(By.name("P-2Alist"));		
		driver.findElement(By.name("P-2Alist")).click();
		
		
	}
}
