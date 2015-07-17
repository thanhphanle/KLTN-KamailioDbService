/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plthanh.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Phan
 */
public class MySQLProvider {
	private static MySQLProvider mysql = null;

	private Connection connection = null;
	private String conString = "jdbc:mysql://localhost:3306/kamailio";
	private String driverString = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "123456";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	private void getDbConfig() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = this.getClass().getResourceAsStream("/db.properties");

			// load a properties file
			prop.load(input);

			this.conString = "jdbc:mysql://localhost:"
					+ prop.getProperty("port") + "/"
					+ prop.getProperty("database");
			this.username = prop.getProperty("dbuser");
			this.password = prop.getProperty("dbpassword");
			
			System.out.println(conString);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	*/

	private MySQLProvider() {
		//getDbConfig();
		try {
			Class.forName(driverString);
			String url = conString + "?user=" + username + "&password="
					+ password;
			connection = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		}
	}

	private MySQLProvider(String user, String pass) {
		//getDbConfig();
		try {
			Class.forName(driverString);
			String url = conString + "?user=" + user + "&password=" + pass;
			connection = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		}
	}
	
	private MySQLProvider(String newConString) {
		//getDbConfig();
		try {
			Class.forName(driverString);
			String url = newConString + "?user=" + this.username + "&password=" + this.password;
			connection = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		}
	}

	public static MySQLProvider getInstance() throws ClassNotFoundException,
			SQLException {
		if (mysql == null) {
			return new MySQLProvider();
		}
		return mysql;
	}

	public static MySQLProvider getInstance(String username, String password)
			throws ClassNotFoundException, SQLException {
		if (mysql == null) {
			return new MySQLProvider(username, password);
		}
		return mysql;
	}
	
	public static MySQLProvider getInstance(String newConString)
			throws ClassNotFoundException, SQLException {
		if (mysql == null) {
			return new MySQLProvider(newConString);
		}
		return mysql;
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
}
