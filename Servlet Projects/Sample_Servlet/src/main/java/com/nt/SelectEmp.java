package com.nt;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.EmpDAO;
import com.nt.utility.Employee;

public class SelectEmp extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		EmpDAO dao = new EmpDAO();
		ResultSet rs =   dao.SelectEmp();
		List<Employee> list = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int sal = rs.getInt("sal");
				Employee e = new Employee(id, name, sal);
				
				list.add(e);
				
				
			}
			
			
			req.setAttribute("emplist", list);
			
			RequestDispatcher rd = req.getRequestDispatcher("Display.jsp");
			rd.forward(req, resp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
