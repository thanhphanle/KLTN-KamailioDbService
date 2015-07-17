package com.plthanh.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.plthanh.model.Subscriber;
import com.plthanh.util.MySQLProvider;

public class SubscriberDao {

	// Get subsciber from id
	public static Subscriber getSubscriber(int userId) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Subscriber sub = new Subscriber();
				sub.setUserId(String.valueOf(userId));

				Statement statement = con.createStatement();
				String sql = "SELECT username, password, domain, email_address FROM subscriber WHERE id="
						+ userId;

				// execute select SQL stetement
				ResultSet rs = statement.executeQuery(sql);

				while (rs.next()) {
					sub.setUsername(rs.getString("username"));
					sub.setPassword(rs.getString("password"));
					sub.setDomain(rs.getString("domain"));
					sub.setEmail(rs.getString("email_address"));
				}
				return sub;
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Add subscriber
	public static int addSubscriber(Subscriber sub) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();

				// Check duplicate username
				String sqlCheck = "SELECT id FROM subscriber WHERE username='"
						+ sub.getUsername() + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (!rs.next()) {
					String sql = "INSERT INTO subscriber(username,password,domain) VALUES ('"
							+ sub.getUsername()
							+ "','"
							+ sub.getPassword()
							+ "','" + sub.getDomain() + "')";
					statement.executeUpdate(sql);
					return 1;
				} else {
					return 2; // duplicated username
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Change password
	public static int changePassword(String username, String oldPassword,
			String newPassword) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();

				// Check wrong old password
				String sqlCheck = "SELECT id FROM subscriber WHERE username='"
						+ username + "' AND password='" + oldPassword + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (rs.next()) { // same old password
					String sql = "UPDATE subscriber SET password='"
							+ newPassword + "' WHERE username='" + username
							+ "' AND password='" + oldPassword + "'";
					statement.execute(sql);
					return 1;
				} else { // different old password
					return 3; // wrong old password
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Delete subscriber
	public static int deleteSubscriber(Subscriber sub) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();

				// Check wrong old password
				String sqlCheck = "SELECT id FROM subscriber WHERE username='"
						+ sub.getUsername() + "' AND password='"
						+ sub.getPassword() + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (rs.next()) { // correct subscriber
					String id = rs.getString("id");
					String sql = "DELETE FROM subscriber WHERE id=" + id;
					statement.execute(sql);
					return 1;
				} else { // different old password
					return 4; // incorrect subscriber
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Recover password
	public static int recoverPassword(String username, String newPassword) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();

				// Check new password is same old password
				String sqlCheck = "SELECT id FROM subscriber WHERE username='"
						+ username + "' AND password='" + newPassword + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (!rs.next()) { // old and new password are different
					String sql = "UPDATE subscriber SET password='"
							+ newPassword + "' WHERE username='" + username
							+ "'";
					statement.execute(sql);
					return 1;
				} else {
					return 5; // old and new password are same
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	// Login subscriber
	public static int loginSubscriber(String username, String password) {
		try {
			MySQLProvider provider = MySQLProvider.getInstance();
			Connection con = provider.getConnection();
			if (con != null) {
				Statement statement = con.createStatement();

				// Check new password is same old password
				String sqlCheck = "SELECT id FROM subscriber WHERE username='"
						+ username + "' AND password='" + password + "'";
				ResultSet rs = statement.executeQuery(sqlCheck);

				if (rs.next()) {
					return 1;
				} else {
					return 8; // incorrect username or password
				}
			}
			provider.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
