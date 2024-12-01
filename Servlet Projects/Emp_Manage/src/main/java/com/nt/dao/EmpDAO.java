package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.entity.Employee;
import com.nt.utility.ConnectionFactory;

public class EmpDAO {
	private static Connection conn = ConnectionFactory.getConn();

	
	
	public void InsertEmp(int id, String name, int sal, String dep) {
		PreparedStatement ps = null;
		try {			
			 ps = conn.prepareStatement("insert into emp values(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, sal);
			ps.setString(4, dep);

			
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public List<Employee> SelectAllEmp() {
		PreparedStatement ps = null;
		try {
			
			 ps = conn.prepareStatement("select * from emp");
			
			 List<Employee> list = new ArrayList<Employee>();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sal = rs.getInt("sal");
				
				Employee e = new Employee(id, name, sal);
				list.add(e);
			}
			
			return list;

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		}

}
