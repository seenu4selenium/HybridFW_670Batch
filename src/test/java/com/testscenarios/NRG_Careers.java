package com.testscenarios;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class NRG_Careers extends CommonFunctions {

	@Test
	public void f() throws Exception {
		firefoxBrowserLaunch();
		getURL("nrg_URL");
		Thread.sleep(5000);
		clickByAnyLocator(loc.nrg_page_accept);
		Thread.sleep(2000);
		
//		loopAllFramesAndReturnCountofElement(loc.nrg_email_Editbox);
//		scrollIntoView(loc.nrg_email_Editbox);
		
		sendKeysByAnyLocator(loc.nrg_email_Editbox, "nrg_emailAddresss");
		clickByAnyLocator(loc.nrg_signup_button);
		Thread.sleep(5000);
		//selectByVisibleText(loc.nrg_country_dropdown, "nrg_countryName");
		Select abc = new Select(driver.findElement(loc.nrg_country_dropdown));
		abc.selectByContainsVisibleText("Japan");
		
		clickByAnyLocator(loc.nrg_career_checkbox);
		clickByAnyLocator(loc.nrg_create_button);
		Thread.sleep(3000);
		// Takescreenshot of current page
		takeScreenshot("NRG_Careers");

	}

}
