package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.UserDAO;

public class Login extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String username = req.getParameter("user");
		
		String password = req.getParameter("pass");
		
		
		
		UserDAO dao = new UserDAO();
		int user_Id = dao.getUser_Id(username, password);
		
		int result = dao.ValidateLogin(username, password);
		 
		
		if(result > 0 )
		{
            Cookie cookie = new Cookie("userId", String.valueOf(user_Id));
			res.addCookie(cookie);
			//After Successful login 
			RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
			rd.forward(req, res);
			
			
		} else {
			req.setAttribute("ErrorMsg", "Invalid Credinitals");
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			rd.forward(req, res);
		}
		
	}
}
