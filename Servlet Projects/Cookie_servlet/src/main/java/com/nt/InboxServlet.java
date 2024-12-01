package com.nt;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InboxServlet  extends HttpServlet{ 

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie[] c = req.getCookies();
		
		String loginuser = null;
		
		if(c != null) {
			for( Cookie ch : c){
				if(ch.getName().equals( "username")){
					loginuser = ch.getValue();
				}
			}
		}
		
		if( loginuser == null || loginuser == "") {
			req.setAttribute("errorMsg", "Login First");
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			rd.forward(req, res);
			
		}
		else {
			req.setAttribute("LogedInUser", loginuser);
			RequestDispatcher rd = req.getRequestDispatcher("inbox.jsp");
			rd.forward(req, res);
			
		}
	}
}
