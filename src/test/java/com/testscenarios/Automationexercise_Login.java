package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

	String results;

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
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ Excel row count is: " + sh.getLastRowNum());
		
		
		
		for (int row = 1; row <= sh.getLastRowNum(); row++) {
			Row r = sh.getRow(row);
			Cell un = r.getCell(0);
			Cell pwd = r.getCell(1);
			

			driver.findElement(loc.aeHomePage_email_editBox).clear();
			driver.findElement(loc.aeHomePage_email_editBox).sendKeys(un.getStringCellValue());
			driver.findElement(loc.aeHomePage_password_editBox).clear();
			driver.findElement(loc.aeHomePage_password_editBox).sendKeys(pwd.getStringCellValue());

			clickByAnyLocator(loc.aeHomePage_login_button);
			Thread.sleep(3000);
			getTextFromElement(loc.aeHomePage_Error_message);
			// verify the error message has displayed or not?
			if (driver.findElements(loc.aeHomePage_Error_message).size() > 0) {
				System.out.println("Error message is presents on screen****");
				results = "Fail / Invalid credentils";
				//c2.setCellValue(results);
			} else {
				System.out.println("Error text is NOT presents on screen********************");
				results = "Pass / Valid credentils";
				//c2.setCellValue(results);
			}
			
			//send the results to Excel file ( Temp: RAM location it will move)
			Cell c2 = r.createCell(2);
			c2.setCellValue(results);
		} // for loop end

		FileOutputStream fo = new FileOutputStream(".\\src\\test\\resources\\testdata\\TestDataNew.xlsx");
		wb.write(fo);
		wb.close();

	}
}
