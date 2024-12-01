package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogout extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		Cookie c = new Cookie("admin", null);
		res.addCookie(c);
		
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
		
		
	}
}
