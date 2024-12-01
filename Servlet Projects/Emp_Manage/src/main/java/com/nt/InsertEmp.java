package com.nt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dao.EmpDAO;

@SuppressWarnings("serial")
public class InsertEmp extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int sal = Integer.parseInt(req.getParameter("sal"));
				
		EmpDAO dao = new EmpDAO();
		dao.InsertEmp(id, name, sal);
		
		
		req.setAttribute("msg", "Insertion Successfull");
		RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
		rd.forward(req, resp);
		
		
		
		
		
		
		
	}
}
