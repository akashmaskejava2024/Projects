package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nt.utility.ConnectionFactory;

public class AdminDAO {

	
	public int ValidateAdminLogin(String username, String password) {

		Connection conn = ConnectionFactory.getConn();

		try {
			PreparedStatement ps = conn.prepareStatement("select username, password from admin");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String DBusername = rs.getString("username");
				String DBpassword = rs.getString("password");

				if (username.equals(DBusername) && password.equals(DBpassword)) {
					return 1;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;

	}
}
