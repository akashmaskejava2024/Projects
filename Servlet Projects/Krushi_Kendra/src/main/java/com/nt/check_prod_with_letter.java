package com.nt;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.entity.product;

public class check_prod_with_letter extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String letters = req.getParameter("checker");
		List<product> list = new ArrayList<product>();
				
		ResultSet rs = ProdDAO.check_stock_with_first_letter(letters);
		
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int batch = rs.getInt("batch");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				String type = rs.getString("type");
				product pr = new product(id, name, batch, price, stock, type);
				list.add(pr);
 
			}
			req.setAttribute("product_list", list);
			RequestDispatcher rd = req.getRequestDispatcher("Display.jsp");
			rd.forward(req, resp);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
