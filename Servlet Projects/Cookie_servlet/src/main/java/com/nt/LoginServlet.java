package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if( username.equals(password) ) {
			Cookie c1 = new  Cookie("role", "hr");
			Cookie c2 = new Cookie("username", username);
			
			res.addCookie(c2);
			res.addCookie(c1);
			
			
			RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
			rd.forward(req, res);

		} else {
			req.setAttribute("errorMsg", "Invalid creidntials ");
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			rd.forward(req, res);
			
		}
			
		
	}
}
