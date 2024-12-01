package com.nt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.EmpDAO;

public class InsertEmp extends HttpServlet{
 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int sal = Integer.parseInt(req.getParameter("sal"));

		EmpDAO dao = new EmpDAO();
		int result = dao.InsertEmp(id, name, sal);
		
		PrintWriter pw =  res.getWriter();
		if(result==1) {
			pw.write("<h1>INsertion Success</h1>");
			
		} else {
			pw.write("<h1>INsertion Failed</h1>");

		}
		
		pw.close();
		
	}
}
