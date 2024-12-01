package com.nt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnProduct_Servlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		res.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		int result = 0;
		 result = ProdDAO.ReturnProduct(id);
		PrintWriter pw = res.getWriter();
		if(result > 0) {
			pw.write("<h1> Product Returned Successfully</h1>");	
		} else {
			pw.write("<h1> Product Return Failed</h1>");	

		}
		pw.write("<form action='index.jsp'> <input type='submit' value='Go to main menu'> </form>");

		pw.close();
		
		
		

	}
	
	
}
