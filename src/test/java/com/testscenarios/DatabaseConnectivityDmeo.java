package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DatabaseConnectivityDmeo {
	// Connection
	// Statement
	// Resultset

	@Test
	public void f() throws Exception {
		// connect the db
		String db_Url = "jdbc:mysql://127.0.0.1:3306/670batch";
		String db_username = "root";
		String db_password = "admin@123";

		Class.forName("com.mysql.cj.jdbc.Driver");
		//Class.forName("com.ibm.db2.jcc.DB2Driver");
		
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
			String FB_UN = rs.getString(3);
			String FB_PSWD = rs.getString(4);
			String FB_EMAIL = rs.getString(5);

			System.out.println(FB_ID + "\t" + FB_NAME + "\t" + FB_UN + "\t" + FB_PSWD + "\t" + FB_EMAIL);

		}

		// Close database connection
		cnn.close();

	}
}
