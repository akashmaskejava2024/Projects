package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakePayment extends HttpServlet{

	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setAttribute("SuccessMsg", "Payment Successfull");
		RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
		rd.forward(req, res);
		
		
	}
	
	
}
