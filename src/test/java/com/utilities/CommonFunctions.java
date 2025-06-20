package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.objectrepository.Locators;

public class CommonFunctions {

	public String screenshotPath;
	public String className;
	public String methodName;
	public FileInputStream fi;
	public Actions actions;
	public JavascriptExecutor js;
	public Scanner s = new Scanner(System.in);	
	public String projectDir = System.getProperty("user.dir");
	
	public String propertyFile = "QA_Environment.properties";
	public Locators loc = new Locators();
	public Properties p = new Properties();
	public static WebDriver driver;

	// Constructor: By default constructor will run first method in a class.
	public CommonFunctions() {
		File screenshotPath = new File(".\\Screenshots");
		if (screenshotPath.exists()) {
			System.out.println("screenshot folder is exits***************");
		} else {
			screenshotPath.mkdir();
			System.out.println("screenshot folder is NOT available, system has created a Folder ***************");
		}

	}

	public CommonFunctions(WebDriver driver) {
		this.driver = driver;
	}

	/********************** Browser launch ******************************/
	public void chromeBrowserLaunch() {
//		WebDriverManager.chromedriver().browserVersion("85").setup();
		driver = new ChromeDriver();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		System.out.println("chrome Browser Launched");
	}

	public void chromeIncognitoBrowserLaunch() {
		// Initialize ChromeOptions to configure browser settings
		ChromeOptions options = new ChromeOptions();
		// Add the argument for incognito mode
		options.addArguments("--incognito");
		// Initialize ChromeDriver instance with options
		driver = new ChromeDriver(options);
		System.out.println("chrome Incognito Browser Launched");
	}

	public void firefoxBrowserLaunch() {
		driver = new FirefoxDriver();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		System.out.println("Firefox Browser Launched");
	}

	public void edgeBrowserLaunch() {
		driver = new EdgeDriver();
		driver.navigate().refresh();
		System.out.println("Edge Browser Launched");

	}

	@Parameters("browserName")
	public void crossBrowser(@Optional("Chrome") String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			chromeBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("edge")) {
			edgeBrowserLaunch();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			firefoxBrowserLaunch();
		} else {
			System.out.println("******Please check the browerName******");
		}
	}

	public void clearCacheFromChrome() throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver","C://WebDrivers/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
		Thread.sleep(5000);
	}

	public void getURL(String URL) throws IOException {
		fi = new FileInputStream(".\\src\\test\\resources\\testdata\\" + propertyFile);
		p.load(fi);
		driver.get(p.getProperty(URL));
	}

	// Scanner method
	public String scnnerByString() {
		String value = s.next();
		return value;
	}

	/*******
	 * SendKeys
	 * 
	 * @throws Exception
	 ************************/
	public void sendKeysByAnyLocator(By locator, String inputdata) throws Exception {
		fi = new FileInputStream(".\\src\\test\\resources\\testdata\\" + propertyFile);
		p.load(fi);

		WebElement element = driver.findElement(locator);

		// Check your locator is displayed?
		if (driver.findElements(locator).size() > 0) {
			// Check your element is in enable state?
			if (element.isEnabled()) {
				System.out.println("Given locator is enable state ***");
				// Clear any existing data
				highlightElement(element);
				element.clear();
				// Send the test data to Edit box
				highlightElement(element);
				element.sendKeys(p.getProperty(inputdata));
			} else {
				System.out.println("Given locator is not enable state on DOM(Current page***");
			}
		} else {
			System.out.println("Given locator is not displayed on DOM(Current page***");
		}
	}

	public void getCaptchaFromConsoleAndSendToEditBox(By locator) throws Exception {
		WebElement ele = driver.findElement(locator);
		System.out.println("Enter the captcha manually in console:");
		String captcha = s.next();
		Thread.sleep(6000);
		if (ele.isDisplayed() && ele.isEnabled()) {
			highlightElement(ele);
			ele.clear();
			highlightElement(ele);
			ele.sendKeys(captcha);
		}
	}

	/*******
	 * Click
	 * 
	 * @throws Exception
	 ************************/
	public void clickByAnyLocator(By locator) throws Exception {
		implicitWait(10);
		WebElement element = driver.findElement(locator);
		// Check your locator is displayed?
		if (driver.findElements(locator).size() > 0) {
			// Check your element is in enable state?
			if (element.isEnabled()) {
				// Click on Button/radiobutton/checkbox/Link...
				highlightElement(element);
				element.click();
			} else {
				System.out.println("Given locator is not enable state on DOM(Current page***");
			}
		} else {
			System.out.println("Given locator is not displayed on DOM(Current page***");
		}
	}

	public void clickUsingJavaScript(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		highlightElement(element);
		executor.executeScript("arguments[0].click();", element);
	}

	public void highlightElement(WebElement element) throws InterruptedException {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			for (int i = 0; i < 1; i++) {
				executor.executeScript("arguments[0].style.border='7px solid red'", element);
				Thread.sleep(200);
				executor.executeScript("arguments[0].style.border='7px groove green'", element);
			}
		} catch (Exception e) {
			System.out.println("Exception - " + e.getMessage());
		}
	}

	/***** Refresh the current screen *********/

	public void refreshCurrentPage() {
//		//method-1
//		driver.navigate().refresh();
//		//method-2
//		driver.navigate().to(driver.getCurrentUrl());
//		//method-3
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("history.go(0)");

	}

	/*********** timestamp **********/
	public String timestamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyyy_HHmmss");
		String timeTamp = df.format(d);
		return timeTamp;
	}

	public void getSystemDateAndTime() {
		Date d = new Date();
		System.out.println(d);

		System.out.println("****************");

		long currentTimeInMillis = System.currentTimeMillis();
		Date today = new Date(currentTimeInMillis);
		System.out.println(today);

		System.out.println("****************");

		Calendar cal = Calendar.getInstance();
		today = cal.getTime();
		System.out.println(today);
	}

	// Get current system time
	/**
	 * @Method:getcurrenttime This method is used to return system time in seconds.
	 */
	public static void getcurrenttime() {
		long currentDateMS = new Date().getTime();
		int seconds = (int) ((currentDateMS / 1000) % 3600);
		System.out.println(currentDateMS);
		System.out.println(seconds);
	}

	public static void GetUnixTimeStampSystem() {
		long unixTimestamp = System.currentTimeMillis() / 1000L;
		System.out.println("Unix timestamp: " + unixTimestamp);
	}

	public static void getUnixTimeStampDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 8);
		cal.set(Calendar.YEAR, 2022);

		Date givenDate = cal.getTime();
		long unixTimestamp = givenDate.toInstant().getEpochSecond();
		System.out.println("Unix timestamp: " + unixTimestamp);

	}

// Get date lessthan current date (based on the user given days)
	public String getDate(int reverseDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		cal.add(Calendar.DAY_OF_MONTH, -reverseDate);
		date = cal.getTime();
		String reportDate = sdf.format(date);
		System.out.println("reportDate :" + reportDate);
		return reportDate;
	}

	/*****
	 * takescreenshot
	 * 
	 * @throws Exception
	 ************/
	public void takeScreenshot(String name) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scrFile, new File(".\\screenshots\\" + name + timestamp() + ".PNG"));
		System.out.println("Screenshot taken*** ");
	}

	public void screenshotWithStatus(ITestResult res) throws Exception {
		projectDir = System.getProperty("user.dir");
		screenshotPath = projectDir + "\\Screenshots\\";
		className = res.getTestClass().getName().trim();
		methodName = res.getName().trim();
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "PASS_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}

	}

	/****************** Dropdown selection **************************************/

	public void selectByVisibleText(By locater, String visibleText) {
		WebElement element = driver.findElement(locater);
		if (driver.findElements(locater).size() > 0) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(visibleText);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}
	}

	public void selectByIndex(By locater, int index) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByIndex(index);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByValue(By locater, String value) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByValue(value);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void printAllDropdownValues(By locater) {
		WebElement element = driver.findElement(locater);

		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {

				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());

					// Select Aug option from the dropdown
					if (dropdownValues.get(i).getText().equals(visibleText)) {
						dropdown.selectByIndex(i);
						break;
					}
				}

			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.accept();
	}

	public void alertHandleByDismiss() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.dismiss();
	}

	/************
	 * popupHandle
	 * 
	 * @throws InterruptedException
	 *********************************/
	public void popupHandleToCloseChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String childWindowName : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(childWindowName)) {
				// switch to child window
				driver.switchTo().window(childWindowName);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		// move cursor point to back to mainwindow
		driver.switchTo().window(mainWindowName);
	}

	public void navigateToPopupWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String string : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}

	}

	/*********** SwithchToWindow using Tab ***************************/
	public void switchToNewTab() {
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
	}

	/***********
	 * SwithchToWindow using Tab then close the New Tab againg back to Parent Window
	 ***************************/
	public void switchAndCloseNewTab() {
		// Get the current window handle
		String parentWindow = driver.getWindowHandle();
		// Switch to New tab [chilld window]
		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		// Close the newly Opened Window[chilld window]
		driver.close();
		// Switch back to original window[parentWindow]
		driver.switchTo().window(parentWindow);
	}

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		                  //Number of iframes on the page are: 3
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesAndReturnCountofElement(By locator) {

		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramecount = iframeCount();// 3
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramecount) {//3<3
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 0
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "	+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;
		}
		return elementpresenceCount;
	}

	/************ waits in selenium ***********************/

	public void implicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		System.out.println("Implicit wait method used***");
	}

	public void waitForElementToBevisibilityOf(By locator, int waitTime) {
		WebElement element = driver.findElement(locator);
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOf(element));
			System.out.println("element To Be visibilityOf***");
		} catch (Exception Ex) {
			System.out.println(Ex);
			System.out.println("element To Be not visibilityOf***");
		}
	}

	public void waitForElementToBeClickable(By locator, int waitTime) {
		WebElement element = driver.findElement(locator);
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("element To Be lickable***");
		} catch (Exception Ex) {
			System.out.println(Ex);
			System.out.println("element To Be not Clickable***");
		}
	}

	public void waitForElementToBeClickable(int waitTime) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTime)).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.alertIsPresent());
			System.out.println("alert Is Present***");
		} catch (Exception Ex) {
			System.out.println(Ex);
			System.out.println("alert Is not Present****");
		}
	}

	/***
	 * instead of fluent wait use customized While loop statement
	 ** 
	 * @throws Exception
	 *****/

	public void waitforElement(By locater) throws Exception {
		int i = 0;
		while (driver.findElements(locater).size() < 1) {
			Thread.sleep(500);
			System.out.println("Wait for the element***************");
			// Suppose system has not present the element it will repeat 30 time
			// then stop
			// the execution using break
			if (i > 30) {
				System.out.println("Element is not present");
				break;
			}
			i++;
		}
	}

//	public void Fluent_Wait(final WebElement El) {
//
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
//				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				return El;
//			}
//		});
//	}
	/************************* MouseHover Actions ************/

	public void moveToOnElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.click().build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}

	public void mouseHoverClickandHold(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.clickAndHold(element);
			actions.click().build().perform();
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void mouseHoverContextClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.contextClick(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void doubleClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.doubleClick(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void dragandDrop(By sourceelementLocator, By destinationelementLocator) {
		try {
			WebElement sourceElement = driver.findElement(sourceelementLocator);
			WebElement destinationElement = driver.findElement(destinationelementLocator);

			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element(s) was not displayed to drag / drop");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceelementLocator + "or" + destinationelementLocator
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceelementLocator + "or" + destinationelementLocator
					+ " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}
	}

	// Reading ToolTip text
	public String tooltipText(By locator) {
		WebElement element = driver.findElement(locator);
		String tooltip = element.getAttribute("title");
		return tooltip;
	}

	// Press Enter/Return Key in Selenium
	public void pressEnter(By locator) {
		WebElement element = driver.findElement(locator);
		element.sendKeys(Keys.RETURN);
	}

	// Press TAB key on your keayboard
	public void pressTabKey(By locator) {
		WebElement element = driver.findElement(locator);
		element.sendKeys(Keys.TAB);
	}

	// Code snippet for Keyboard Actions
	public void typeTextInCAPS(By locator, String TextToBePrint) {
		WebElement textBoxElement = driver.findElement(locator);
		Actions builder = new Actions(driver);
		Action typeInCAPS = builder.keyDown(textBoxElement, Keys.SHIFT).sendKeys(textBoxElement, TextToBePrint)
				.keyUp(textBoxElement, Keys.SHIFT).build();
		typeInCAPS.perform();
	}

	public void randomNumberWithInRange(int minimum, int maximum) {
		int randomNum = ThreadLocalRandom.current().nextInt(minimum, maximum + 1);
		System.out.println(randomNum);
	}

	public int randomNumberWithRange(int maximum) {
		Random r = new Random();
		int randomNumber = r.nextInt(maximum);
		System.out.println(randomNumber);
		return randomNumber;
	}

	public void verifyWebElement(By locator) {

		if (driver.findElements(locator).size() > 0) {
			System.out.println(locator + " is displayed on screen ");

		} else {
			System.out.println(locator + " is not displayed on screen,please check the locator ");
		}

	}

	// Get data from property file and verify Webelement Text
	public void verifyTexttobePresent(By locator, String expectedresults) throws Exception {
		WebElement ele = driver.findElement(locator);
		highlightElement(ele);
		String eleText = ele.getText();
		fi = new FileInputStream(".\\src\\test\\resources\\testdata\\" + propertyFile);
		p.load(fi);
		if (eleText.equals(p.getProperty(expectedresults))) {
			System.out.println("expected text presented on screen");

		} else if (eleText.contains(expectedresults)) {
			System.out.println("expected text contains  on screen");
		} else {
			System.out.println("expected text not presented on screen");
		}
	}

	// getText From WebElement
	public String getTextFromElement(By locator) {
		String text = null;
		try {
			if (driver.findElements(locator).size() > 0) {
				// WebElement element = driver.findElement(locator);
				text = driver.findElement(locator).getText();
				System.out.println("Element Text is: " + text);
			} else {
				System.out.println("Element not present !");
			}
		} catch (Exception Ex) {
			System.out.println("Exception occured");
		}
		return text;
	}

	// Webelement Background Color Value
	public static void webelementBackgroundColorValue(By locator) throws Exception {
		// getting color attribute with getCssValue()
		String colr = driver.findElement(locator).getCssValue("color");
		// getting background color attribute with getCssValue()
		String bckgclr = driver.findElement(locator).getCssValue("background-color");
		// obtain color in rgba
		System.out.println(colr);
		System.out.println(bckgclr);
		// convert rgba to hex
		String hex = Color.fromString(colr).asHex();
		System.out.println(hex);
	}

	// Scrolling down the page till the element is found / visible
	public void scrollIntoView(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Page scroll done");
	}

	// Uploading file using Selenium-WebDriver
	public static void uploadFile(By locator, String path) {
		driver.findElement(locator).sendKeys(path);
	}

	// get All HyperLinks From HTML page
	public static void getAllHyperLinksFromHTMLpage(String URL) {
		driver.get(URL);
		// Get list of web-elements with tagName - a
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		// Traversing through the list and printing its text along with link address
		for (WebElement link : allLinks) {
			System.out.println(link.getText() + " - " + link.getAttribute("href"));
		}
	}

	public void isDisplayedValidation(By locator) {
		if (driver.findElement(locator).isDisplayed()) {
			System.out.println(locator + " Webelement is displayed");
		} else {
			System.out.println(locator + " Webelement is NOT displayed");
		}
	}

	public void isEnabledValidation(By locator) {
		if (driver.findElement(locator).isEnabled()) {
			System.out.println(locator + " Webelement is Enabled");
		} else {
			System.out.println(locator + " Webelement is NOT Enabled");
		}
	}

	public void isSelectedValidation(By locator) {
		if (driver.findElement(locator).isSelected()) {
			System.out.println(locator + " Webelement is Selected");
		} else {
			System.out.println(locator + " Webelement is NOT Selected");
		}
	}

	// validate font 'color'
	public boolean validateElementColor(String xpath, String expetedColor) {
		boolean ecolor = false;
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				String color = driver.findElement(By.xpath(xpath)).getCssValue("color");
				String[] hexValue = color.replace("rgba(", "").replace(")", "").split(",");
				int hexValue1 = Integer.parseInt(hexValue[0]);
				hexValue[1] = hexValue[1].trim();
				int hexValue2 = Integer.parseInt(hexValue[1]);
				hexValue[2] = hexValue[2].trim();
				int hexValue3 = Integer.parseInt(hexValue[2]);
				String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
				if (actualColor.equals(expetedColor)) {
					ecolor = true;
				}
				System.out.println("actualColor is: " + actualColor);

			}
		} catch (Exception e) {
		}
		return ecolor;
	}

	// validate font size of an element
	public boolean validateFontSize(String xpath, String expectedFont) {
		boolean font = false;
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				String actualFont = driver.findElement(By.xpath(xpath)).getCssValue("font-size");
				if (actualFont.equals(expectedFont)) {
					font = true;
				}
				System.out.println("actualFont is: " + actualFont);
			}
		} catch (Exception e) {
		}

		return font;
	}

	// validate 'background-color' of an element
	public boolean validateElementBGColor(String xpath, String expetedColor) {
		boolean ecolor = false;
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
				String bcolor = driver.findElement(By.xpath(xpath)).getCssValue("background-color");
				String[] hexValue = bcolor.replace("rgb(", "").replace(")", "").split(",");
				int hexValue1 = Integer.parseInt(hexValue[0]);
				hexValue[1] = hexValue[1].trim();
				int hexValue2 = Integer.parseInt(hexValue[1]);
				hexValue[2] = hexValue[2].trim();
				int hexValue3 = Integer.parseInt(hexValue[2]);
				String bactualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
				if (bactualColor.equals(expetedColor)) {
					ecolor = true;
				}
				System.out.println("bactualColor is: " + bactualColor);

			}
		} catch (Exception e) {
		}
		return ecolor;
	}

	// Count and print All 'Images' Present In a Web Page
	public int imagesCountAndPrintWebElements() {
		implicitWait(10);
		int numberofLinks = driver.findElements(By.xpath("//img")).size();
		for (int x = 1; x <= numberofLinks; x++) {
			System.out.println(driver.findElement(By.xpath("(//img)[" + x + "]")).getText());
		}
		return numberofLinks;
	}

	// Count and print All Links Present In a Web Page
	public int hyperLinksCountAndPrintWebElements() {
		implicitWait(10);
		int numberofLinks = driver.findElements(By.xpath("//a")).size();
		for (int x = 1; x <= numberofLinks; x++) {
			System.out.println(driver.findElement(By.xpath("(//a)[" + x + "]")).getText());
		}
		return numberofLinks;
	}

	public static String getRandomEmailAsString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		saltStr = saltStr + "@gmail.com";
		return saltStr;

	}

}
