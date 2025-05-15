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

	// NRG
	public final By nrg_email_Editbox = By.xpath("(//*[@name='ucc_email'])[2]");
	public final By nrg_signup_button = By.xpath("(//*[@value='Sign up now'])[2]");
	public final By nrg_country_dropdown = By.xpath("//*[@id='fbclc_ituCode']");
	public final By nrg_career_checkbox = By.id("fbclc_campaignEmailEnabled");
	public final By nrg_create_button = By.id("fbclc_createAccountButton");
	public final By nrg_page_accept = By.id("cookie-acknowledge");

	

}
