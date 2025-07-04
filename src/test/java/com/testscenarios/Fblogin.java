package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class Fblogin extends CommonFunctions {

	@Test
	public void f() throws Exception {
		String FB_UN = null;
		String FB_PSWD = null;

		// connect the db
		String db_Url = "jdbc:mysql://127.0.0.1:3306/670batch";
		String db_username = "root";
		String db_password = "admin@123";

		Class.forName("com.mysql.cj.jdbc.Driver");

		// Connection
		Connection cnn = DriverManager.getConnection(db_Url, db_username, db_password);

		// write the SQL query
		//String myQuery = "SELECT * FROM FB_DB WHERE FB_NAME = 'NAGALAKSHMI';";
		 String myQuery = "SELECT * FROM FB_DB;";

		// Statement
		Statement stmt = cnn.createStatement();
		// Resultset
		ResultSet rs = stmt.executeQuery(myQuery);
		// to print the results into console will use any loop stmt (While)

		while (rs.next()) {
			String FB_ID = rs.getString(1);
			String FB_NAME = rs.getString(2);
			FB_UN = rs.getString(3);
			FB_PSWD = rs.getString(4);
			String FB_EMAIL = rs.getString(5);
			System.out.println(FB_ID + "\t" + FB_NAME + "\t" + FB_UN + "\t" + FB_PSWD + "\t" + FB_EMAIL);
			
			chromeBrowserLaunch();
			getURL("FB_URL");
//			sendKeysByAnyLocator(loc.fbLogin_Email_editBox, "FB_UN");
//			sendKeysByAnyLocator(loc.fbLogin_password_editBox, "FB_UN");	
			System.out.println(FB_UN);
			System.out.println(FB_PSWD);

			driver.findElement(loc.fbLogin_Email_editBox).sendKeys(FB_UN);
			driver.findElement(loc.fbLogin_password_editBox).sendKeys(FB_PSWD);
			clickByAnyLocator(loc.fbLogin_login_button);
			
		}

		

		// Close database connection
		cnn.close();

	}
}
