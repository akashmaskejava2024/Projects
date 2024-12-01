package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;

public class RemovefromCart extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		int book_id = Integer.parseInt(req.getParameter("id"));

		BookDAO bd = new BookDAO();
		int r = bd.RemoveBookfromCart(book_id);

		if (r == 1) {
			

			RequestDispatcher rd = req.getRequestDispatcher("Orders");
			rd.forward(req, res);
		} else {
			req.setAttribute("ErrorMsg", "Unable to Remove from cart");
			RequestDispatcher rd = req.getRequestDispatcher("ErrorMsg.jsp");
			rd.forward(req, res);
		}

	}
}
