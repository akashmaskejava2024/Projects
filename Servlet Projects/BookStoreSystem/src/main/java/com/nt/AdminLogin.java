package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.AdminDAO;
import com.nt.dao.UserDAO;

@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String username = req.getParameter("user");
		
		String password = req.getParameter("pass");
		
		
		
		AdminDAO ad = new AdminDAO();
		int result = ad.ValidateAdminLogin(username, password);
		
		if(result > 0 )
		{
			Cookie cookie = new Cookie("admin", username);
			res.addCookie(cookie);
			//After Successful Admin login 
			RequestDispatcher rd = req.getRequestDispatcher("AdminMenu.jsp");
			rd.forward(req, res);
			
		} else {
			req.setAttribute("ErrorMsg", "Invalid Credinitals");
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.jsp");
			rd.forward(req, res);
		}
		
	}
}
