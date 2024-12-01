package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nt.utility.ConnectionFactory;

public class UserDAO {

    public int RegisterUser(String username, String password, String email) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.getConn();
            ps = conn.prepareStatement("INSERT INTO users (username, password, email) VALUES (?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);

            int r = ps.executeUpdate();
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    public int checkUsername_registration(String Username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConn();
            ps = conn.prepareStatement("SELECT username FROM users");
            rs = ps.executeQuery();

            while (rs.next()) {
                String DBuser = rs.getString("username");
                if (Username.equals(DBuser)) {
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    public int ValidateLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConn();
            ps = conn.prepareStatement("SELECT username, password FROM users");
            rs = ps.executeQuery();

            while (rs.next()) {
                String DBusername = rs.getString("username");
                String DBpassword = rs.getString("password");

                if (username.equals(DBusername) && password.equals(DBpassword)) {
                    return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

    public int getUser_Id(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.getConn();
            ps = conn.prepareStatement("SELECT user_id FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                int user_Id = rs.getInt("user_id");
                return user_Id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return 0;
    }

}
