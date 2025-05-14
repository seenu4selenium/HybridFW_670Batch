package com.testscenarios;

import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class Fblogin extends CommonFunctions {

	@Test
	public void f() throws Exception {
		chromeBrowserLaunch();
		getURL("FB_URL");
		sendkeysByAnyLocator(loc.fbLogin_Email_editBox, "FB_UN");
		clickByAnyLocator(loc.fbLogin_login_button);
	}
}
