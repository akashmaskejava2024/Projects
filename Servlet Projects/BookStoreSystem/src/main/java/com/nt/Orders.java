package com.nt;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.OrderDAO;
import com.nt.entity.Books;

public class Orders extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		OrderDAO od = new OrderDAO();
	     List<Books> list = 	od.booksAddedinCart();
	     
	     if (list != null) {

				req.setAttribute("booklist", list);
				RequestDispatcher rd = req.getRequestDispatcher("DisplayOrdersList.jsp");
				rd.forward(req, res);

			} else {
				req.setAttribute("ErrorMsg", "No Records found");
				RequestDispatcher rd = req.getRequestDispatcher("DisplayAllBooksList");
				rd.forward(req, res);

			    
			}
		
	}
}
