package com.nt.books;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;

public class Registerbook extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		int price = Integer.parseInt(req.getParameter("price"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		
		
		BookDAO bd = new BookDAO();
		
		int result = bd.RegisterBook(title, author, price, stock);
		
		if(result>0) {
			
			req.setAttribute("Msg", "BookRegistered Successfully");
			
			
		} else {
			req.setAttribute("Msg", "BookRegistered Failed");

		}
		RequestDispatcher rd = req.getRequestDispatcher("Bookresult.jsp");
		rd.forward(req, res);
		
		
		
	}
	
}
