package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

public class Automationexercise_Login extends CommonFunctions {

	@Test
	public void f() throws Exception {
		// Read the test data from excel file
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\TestDataNew.xlsx");
		Workbook wb = new XSSFWorkbook(fi);
		Sheet sh = wb.getSheet("Testabc");

		chromeBrowserLaunch();
		getURL("AE_URL");
		Thread.sleep(3000);
		clickByAnyLocator(loc.aeHomePage_Signup_MenuLink);
		Thread.sleep(3000);

		// re-testing we will use for loop

		for (int row = 1; row < 5; row++) {
			Row r = sh.getRow(row);
			Cell un = r.getCell(0);
			Cell pwd = r.getCell(1);

//	  sendKeysByAnyLocator(loc.aeHomePage_email_editBox, "AE_UN");
//	  sendKeysByAnyLocator(loc.aeHomePage_password_editBox, "AE_PWSD");

			driver.findElement(loc.aeHomePage_email_editBox).clear();
			driver.findElement(loc.aeHomePage_email_editBox).sendKeys(un.getStringCellValue());
			driver.findElement(loc.aeHomePage_password_editBox).clear();
			driver.findElement(loc.aeHomePage_password_editBox).sendKeys(pwd.getStringCellValue());

			clickByAnyLocator(loc.aeHomePage_login_button);
			Thread.sleep(3000);
			getTextFromElement(loc.aeHomePage_Error_message);
		}

	}
}
