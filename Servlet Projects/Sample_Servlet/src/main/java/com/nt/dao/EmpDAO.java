package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDAO {

	
	public int InsertEmp(int id, String name , int sal,boolean isGood) {
		
		Connection conn = null;
		PreparedStatement  ps = null;
		int r = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root" , "Admin");
			ps  = conn.prepareStatement("insert into empl values (?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, sal);
			ps.setBoolean(4, isGood);
			
			r = ps.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;

	}
	
	
	
	public ResultSet SelectEmp() {
		
		Connection conn = null;
		PreparedStatement  ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root" , "Admin");
			ps  = conn.prepareStatement("select * from empl");
			
			ResultSet rs = ps.executeQuery();
			return rs;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
