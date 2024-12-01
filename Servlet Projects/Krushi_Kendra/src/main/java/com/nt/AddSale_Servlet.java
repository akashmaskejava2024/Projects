package com.nt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSale_Servlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		res.setContentType("text/html");
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		int result = 0;
		 result = ProdDAO.AddSale(id);
		PrintWriter pw = res.getWriter();
		if(result > 0) {
			pw.write("<h1> Sale Added Successfully</h1>");	
		} else {
			pw.write("<h1> Sale Addition Failed</h1>");	

		}
		
		pw.write("<form action='index.jsp'> <input type='submit' value='Go to main menu'> </form>");

		pw.close();
		
		
		

	}
}
