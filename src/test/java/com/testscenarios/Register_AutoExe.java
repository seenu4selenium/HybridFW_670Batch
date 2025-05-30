package com.testscenarios;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Register_AutoExe {
	public static void main(String[] args) throws Exception {
		WebDriver driver;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.automationexercise.com/");
		Thread.sleep(2000);

//New User SIgnUp
		driver.findElement(By.xpath("//*[text()=\" Signup / Login\"]")).click();
		driver.findElement(By.xpath("//*[@name='name']")).sendKeys("Naga");
		//create random number
		Random r = new Random();
		int randomNumber = r.nextInt(1000,99999);
		System.out.println("randomNumber is : "+randomNumber);
		//71746
		//chlakshmi.hyd171746@gmail.com
		driver.findElement(By.xpath("(//*[@name='email'])[2]")).sendKeys("chlakshmi.hyd1"+randomNumber+"@gmail.com");
		driver.findElement(By.xpath("(//*[@type='submit'])[2]")).click();
//Filling Signup page
		driver.findElement(By.xpath("//*[@value='Mrs']")).click();
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("NagaNaga");
		driver.findElement(By.id("first_name")).sendKeys("Naga");
		driver.findElement(By.name("last_name")).sendKeys("Ye");
		driver.findElement(By.name("address1")).sendKeys("123 Data Drive");
		driver.findElement(By.id("country")).click();
		driver.findElement(By.xpath("//*[@value='United States']")).click();
		driver.findElement(By.name("state")).sendKeys("New Jersey");
		driver.findElement(By.id("city")).sendKeys("Edison");
		driver.findElement(By.name("zipcode")).sendKeys("08888");
		driver.findElement(By.id("mobile_number")).sendKeys("9999999999");
		driver.findElement(By.xpath("(//*[@type='submit'])[1]")).click();

// Selecting an item
		driver.findElement(By.xpath("(//*[@class='btn btn-default add-to-cart'])[11]")).click();

		//// Clicking on View Cart link on pop up message
//Clicking on Proceed to Checkout button
		driver.findElement(By.xpath("//*[@class='btn btn-default check_out']")).click();

	}
}