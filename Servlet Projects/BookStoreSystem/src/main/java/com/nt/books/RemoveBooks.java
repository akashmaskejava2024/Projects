package com.nt.books;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;

public class RemoveBooks extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int id = Integer.parseInt(req.getParameter("id"));
		 int stock = Integer.parseInt(req.getParameter("stock"));
		 
		 BookDAO bd = new BookDAO();
		 
		  int result = bd.RemoveBooks(id, stock);
		 
		  if(result > 0) {
			  
			  req.setAttribute("Msg", "Books stock Updated Successfully");
			 
		  }  else {
			  req.setAttribute("Msg", "Books stock Updated Failed");

			  
		  }
		  RequestDispatcher rd = req.getRequestDispatcher("Bookresult.jsp");
		  rd.forward(req, resp);
		 
	}
}
