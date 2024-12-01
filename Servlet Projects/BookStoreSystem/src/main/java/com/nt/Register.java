package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.UserDAO;

public class Register extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		String email = req.getParameter("email");

		UserDAO dao = new UserDAO();

		int checkuser = dao.checkUsername_registration(username);
		

		if (checkuser == 0) {

			int result = dao.RegisterUser(username, password, email);

			if (result > 0) {

				// Cookie Creation
			
				// After Successful registration
				RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
				rd.forward(req, res);

			} else {
				req.setAttribute("ErrorMsg", "Registration Failed");
				RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
				rd.forward(req, res);

			}

		} else {
			req.setAttribute("ErrorMsg", "Try with Different Username");
			RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
			rd.forward(req, res);
		}
	}
}
