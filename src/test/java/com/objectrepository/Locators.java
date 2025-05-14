package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {

	// Here QA will store all application Locators
	// public final By pagename_ElementName_elementType = By.name("Sdgffdgd");
	public final By fbLogin_ForgotPassword_Link = By.linkText("Forgot password?");
	public final By fbLogin_Email_editBox = By.name("email");
	public final By fbLogin_login_button = By.name("login");

	// automationexercise
	public final By aeHomePage_Signup_MenuLink = By.partialLinkText("Signup");
	public final By aeHomePage_email_editBox = By.name("email");
	public final By aeHomePage_password_editBox = By.name("password");
	public final By aeHomePage_login_button = By.xpath("//*[@data-qa='login-button']");
	public final By aeHomePage_Error_message = By.xpath("//*[text()='Your email or password is incorrect!']");
	public final By aeHomePage_Sreeni_User = By.xpath("//*[text()='Sreeni']");
	
	
}
