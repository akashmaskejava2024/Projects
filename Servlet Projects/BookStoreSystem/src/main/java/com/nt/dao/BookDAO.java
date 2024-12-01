package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nt.entity.Books;
import com.nt.utility.ConnectionFactory;

public class BookDAO {

	public int RegisterBook(String title, String author, int price, int stock) {

		Connection conn = ConnectionFactory.getConn();

		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO books (title, author, price, stock) VALUES (?, ?, ?, ?)");
			ps.setString(1, title);
			ps.setString(2, author);
			ps.setInt(3, price);
			ps.setInt(4, stock);

			int r = ps.executeUpdate();

			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public int RemoveBooks(int id, int stock) {

		Connection conn = ConnectionFactory.getConn();

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE books SET stock = ? WHERE book_id = ?");

			ps.setInt(2, id);
			ps.setInt(1, stock);

			int r = ps.executeUpdate();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public List<Books> DisplayAllBooks() {

		Connection conn = ConnectionFactory.getConn();

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
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

	public int AddBookToCart(int book_Id, boolean isAddedToCart) {
	    Connection conn = ConnectionFactory.getConn();

	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE books SET isAddedToCart = ? WHERE book_id = ?");
	        ps.setBoolean(1, isAddedToCart); // Set the boolean value for isAddedToCart
	        ps.setInt(2, book_Id); // Set the integer value for book_Id

	        int result = ps.executeUpdate();
	        return result;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	
	public int RemoveBookfromCart(int book_Id) {
	    Connection conn = ConnectionFactory.getConn();
	    boolean isAddedToCart = false;
	    try {
	        PreparedStatement ps = conn.prepareStatement("UPDATE books SET isAddedToCart = ? WHERE book_id = ?");
	        ps.setBoolean(1, isAddedToCart); // Set the boolean value for isAddedToCart
	        ps.setInt(2, book_Id); // Set the integer value for book_Id

	        int result = ps.executeUpdate();
	        return result;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	
	

}
