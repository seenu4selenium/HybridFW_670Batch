package com.testscenarios;

import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class RetestingWithDataProviderDemo extends CommonFunctions {
	@Test(dataProvider = "abc")
	public void f(String UN, String PWSD) throws Exception {

		chromeBrowserLaunch();
		getURL("AE_URL");
		Thread.sleep(3000);
		clickByAnyLocator(loc.aeHomePage_Signup_MenuLink);
		Thread.sleep(3000);
		driver.findElement(loc.aeHomePage_email_editBox).clear();
		driver.findElement(loc.aeHomePage_email_editBox).sendKeys(UN);
		driver.findElement(loc.aeHomePage_password_editBox).clear();
		driver.findElement(loc.aeHomePage_password_editBox).sendKeys(PWSD);
		//clickByAnyLocator(loc.aeHomePage_login_button);

	}

	@DataProvider
	public Object[][] abc() {
		return new Object[][] {
			new Object[] { "Himawetgsdfsfs@gmail.com", "hima23124" }, 
//			new Object[] { "ytest1@gmail.com", "ytest1@" },
//			new Object[] { "ytest1456y746@gmail.com", "ytest135465465@" },
			};
	}
}
