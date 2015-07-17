package com.plthanh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.plthanh.util.MySQLProvider;

public class RecoverDao {

	// Add recover mapping
	public static int addRecoverMapping(String code, String username) {
		try {
			String conString = "jdbc:mysql://localhost:3306/kamailiosecall";
			MySQLProvider provider = MySQLProvider.getInstance(conString);
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();
				// Check recover mapping exist
				String sqlCheck = "SELECT * FROM recover WHERE username='"
						+ username + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (rs.next()) { // recover existed, just update new code for
									// this username
					String sql = "UPDATE recover SET code='" + code
							+ "' WHERE username='" + username + "'";
					statement.execute(sql);
					return 6; // recover process is still not passed, just
								// update the code
				} else { // recover not exist, insert new mapping
					String sql = "INSERT recover(code,username) VALUES ('"
							+ code + "', '" + username + "')";
					statement.executeUpdate(sql);
					return 1;
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Check recover mapping information
	public static int checkRecoverMapping(String code, String username) {
		try {
			String conString = "jdbc:mysql://localhost:3306/kamailiosecall";
			MySQLProvider provider = MySQLProvider.getInstance(conString);
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();
				// Check recover mapping exist
				String sqlCheck = "SELECT * FROM recover WHERE code='" + code
						+ "' AND username='" + username + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (rs.next()) {
					return 1; // correct information
				} else {
					return 7; // wrong recover code
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Delete recover mapping
	public static int deleteRecoverMapping(String code, String username) {
		try {
			String conString = "jdbc:mysql://localhost:3306/kamailiosecall";
			MySQLProvider provider = MySQLProvider.getInstance(conString);
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();
				// delete recover mapping
				String sql = "DELETE FROM recover WHERE code='" + code
						+ "' AND username='" + username + "'";
				statement.execute(sql);
				return 1;
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
