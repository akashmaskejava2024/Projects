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

public class Insert_Sevlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		res.setContentType("text/html");
		String name = req.getParameter("name");
		int batch = Integer.parseInt(req.getParameter("batch"));
		int price = Integer.parseInt(req.getParameter("price"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		String type = req.getParameter("type");
		
		int result = 0;
		 result = ProdDAO.InsertProduct(name, batch, price, stock, type);

		PrintWriter pw = res.getWriter();
		if(result > 0) {
			pw.write("<h1> Data Inserted Successfully</h1>");	
		} else {
			pw.write("<h1> Data Insertion Failed</h1>");	

		}
		
		pw.write("<form action='index.jsp'> <input type='submit' value='Go to main menu'> </form>");
		
		pw.close();
		
		
		
		
		

	}

}
