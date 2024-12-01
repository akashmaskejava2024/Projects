package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.entity.Books;
import com.nt.utility.ConnectionFactory;

public class OrderDAO {

	public List<Books> booksAddedinCart() {

		Connection conn = ConnectionFactory.getConn();
		boolean added = true;

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * from books where isAddedToCart = ?");
			ps.setBoolean(1, added);
			ResultSet rs = ps.executeQuery();
			List<Books> list = new ArrayList<Books>();

			while (rs.next()) {
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				Boolean IsAddedToCart1 = rs.getBoolean("IsAddedToCart");
				if (stock > 0) {

					Books b = new Books(id, title, author, price, stock, IsAddedToCart1);
					list.add(b);
				}

			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<Books> getOrderedBooks() {

		Connection conn = ConnectionFactory.getConn();
		boolean added = true;

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE isAddedToCart = ?");
			ps.setBoolean(1, added);
			ResultSet rs = ps.executeQuery();
			List<Books> list = new ArrayList<Books>();

			while (rs.next()) {
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				Boolean IsAddedToCart1 = rs.getBoolean("IsAddedToCart");
				if (stock > 0) {

					Books b = new Books(id, title, author, price, stock, IsAddedToCart1);
					list.add(b);
				}

			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updatestockConfirmedOrders() {

		Connection conn = ConnectionFactory.getConn();
		boolean added = true;

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE books SET stock = stock - 1 WHERE isAddedToCart = ?");
			ps.setBoolean(1, added);
			int r = ps.executeUpdate();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public int removeAllFromCart() {

		Connection conn = ConnectionFactory.getConn();
		boolean added = true;
		boolean remove = false;

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE books SET isAddedToCart = ? WHERE isAddedToCart = ?");
			ps.setBoolean(1, remove); // Set the value for removing from cart
			ps.setBoolean(2, added); // Set the current state to look for

			int r = ps.executeUpdate();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public List<Books> getConfirmOrderedBooks() {
		Connection conn = ConnectionFactory.getConn();
		boolean added = true;

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT book_id FROM books");
			ps.setBoolean(1, added);
			ResultSet rs = ps.executeQuery();
			List<Books> list = new ArrayList<Books>();

			while (rs.next()) {
				int book_id = rs.getInt("book_id");
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM FROM books WHERE book_id = ?");
				pst.setInt(1, book_id);
				
				ResultSet rs1 = pst.executeQuery();
				while (rs.next()) {
					int id = rs1.getInt("book_id");
					String title = rs1.getString("title");
					String author = rs1.getString("author");
					int price = rs1.getInt("price");
					int stock = rs1.getInt("stock");
					Boolean IsAddedToCart1 = rs1.getBoolean("IsAddedToCart");
					

						Books b = new Books(id, title, author, price, stock, IsAddedToCart1);
						list.add(b);
					

				}
				
				
			}
			

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
