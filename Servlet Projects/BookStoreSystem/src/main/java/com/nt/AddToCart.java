package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;
import com.nt.dao.CartDAO;

public class AddToCart extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int book_id = Integer.parseInt(req.getParameter("id"));

		BookDAO bd = new BookDAO();
		boolean isAddedToCart = true;
		int r = bd.AddBookToCart(book_id, isAddedToCart);

		if (r == 1) {
			

			RequestDispatcher rd = req.getRequestDispatcher("DisplayAllBooksList");
			rd.forward(req, res);
		} else {
			req.setAttribute("ErrorMsg", "Unable to Add to cart");
			RequestDispatcher rd = req.getRequestDispatcher("ErrorMsg.jsp");
			rd.forward(req, res);
		}

	}
}
