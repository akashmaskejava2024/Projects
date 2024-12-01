package com.nt;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.BookDAO;
import com.nt.entity.Books;

@SuppressWarnings("serial")
public class DisplayAllBooksList extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		
		
		
		
		
		BookDAO bd = new BookDAO();
		List<Books> list = bd.DisplayAllBooks();

		if (list != null) {

			req.setAttribute("booklist", list);
			RequestDispatcher rd = req.getRequestDispatcher("DisplayAllBooksList.jsp");
			rd.forward(req, res);

		} else {
			req.setAttribute("ErrorMsg", "No Records found");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);

		    
		}

	}
}
