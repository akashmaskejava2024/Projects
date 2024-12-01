package com.nt;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.OrderDAO;
import com.nt.entity.Books;

public class ConfirmOrders extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		OrderDAO od = new OrderDAO();
		List<Books> list = od.getOrderedBooks();

		int result = od.updatestockConfirmedOrders();
		int r = od.removeAllFromCart();
		
		
		

		if (list != null) {
			if (result > 0) {

				if (r > 0) {
					req.setAttribute("booklist", list);
					RequestDispatcher rd = req.getRequestDispatcher("DisplayConfirmedOrders.jsp");
					rd.forward(req, res);

				} else {
					req.setAttribute("ErrorMsg", "Not All orders removed from cart");
					RequestDispatcher rd = req.getRequestDispatcher("Orders");
					rd.forward(req, res);

				}
			} else {

				req.setAttribute("ErrorMsg", "No orders are selected");
				RequestDispatcher rd = req.getRequestDispatcher("ErrorMsg.jsp");
				rd.forward(req, res);

			}

		} else {

			req.setAttribute("ErrorMsg", "No Orders Confirmed ");
			RequestDispatcher rd = req.getRequestDispatcher("Orders");
			rd.forward(req, res);

		}

	}
}
