package com.testscenarios;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class NewTest extends CommonFunctions{
  @Test
  public void f() throws Exception {
	  chromeBrowserLaunch();
	  getURL("AE_URL");
	  scrollIntoView(By.xpath("//input[@id='susbscribe_email']"));
  }
}
