package com.testscenarios;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class TS_04_SearchCatgeoryandBrnd extends CommonFunctions  {
  @Test
  public void f() throws IOException, Exception {
	  chromeBrowserLaunch();
		getURL("AES_URL");
		Thread.sleep(3000);
		sendKeysByAnyLocator(loc.AESearch_Searchpdct_editbox, "AES_Srch");
		Thread.sleep(7000);
		clickByAnyLocator(loc.AESearch_Search_button);
		Thread.sleep(3000);
		String srchpr = driver.findElement(By.xpath("//h2[contains(text(),'Searched')]")).getText();
		System.out.println("Searched Products:" + srchpr);
		Thread.sleep(5000);
		System.out.println("Dispaly the text as:" + srchpr);
		Thread.sleep(2000);
		List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class,'productinfo')]/p"));
		for (WebElement abc : items) {
			// Verify each and every product contains jeans
			// System.out.println(abc.getText());
			if (abc.getText().contains("Jeans")) {
				System.out.println("This product contains Jeans text");

			}

			else {
				System.out.println("This product  does not contains Jeans text");
			}

			System.out.println(abc.getText());
		}

		// System.out.println(items);
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
		driver.findElement(By.xpath("//span[@class='badge pull-right']")));
		driver.findElement(By.xpath("//span[@class='badge pull-right']")).click();
		Thread.sleep(7000);
		driver.findElement(By.xpath("(//*[contains(@id,'Women')]//a)[2]")).click();
		String srchtops = driver.findElement(By.xpath("//h2[contains(text(),' Tops')]")).getText();
		System.out.println("Dispaly with srchtopsproducts:" + srchtops);
		driver.findElement(By.xpath("(//div[contains(@class,\"brands-name\")]//a)[2]")).click();
		String brandprdcts = driver.findElement(By.xpath("(//h2[contains(text(),'Brand')])[2]")).getText();
		System.out.println("Brand H and M Procucts" + brandprdcts);

  }
}
