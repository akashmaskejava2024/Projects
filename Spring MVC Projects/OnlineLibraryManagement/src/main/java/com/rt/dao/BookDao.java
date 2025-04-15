package com.rt.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.entity.BookEntity;
import com.rt.mapper.BookRowMapper;

@Repository
public class BookDao {

	@Autowired
	JdbcTemplate template;

	public boolean addBook(BookEntity entity) {
		try {
			Object args[] = { entity.getTitle(), entity.getAuthor(), entity.getLanguage(), entity.getISBN(),
					entity.getPublisher(), entity.getPublisherCity(), entity.getPublicationDate(), entity.getStatus(),
					entity.getAvailableQuantity(), entity.getTotalQuantity() };
			int data = template.update(
					"INSERT INTO book (`Title`, `Author`,`Language`, `ISBN`, `Publisher`, `PublisherCity`, `PublicationDate`, `Status`,`AvailableQuantity`,`TotalQuantity`) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)",
					args);

			if (data == 1) {
				System.out.println(data);
				return true;

			} else {
				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(BookEntity entity) {
		try {
			Object[] args = { entity.getTitle(), entity.getAuthor(), entity.getLanguage(), entity.getISBN(),
					entity.getPublisher(), entity.getPublisherCity(), entity.getPublicationDate(), entity.getBookId() };

			String sql = "UPDATE book SET `Title` = ?, `Author` = ?, `Language` = ?, `ISBN` = ?, `Publisher` = ?, "
					+ "`PublisherCity` = ?, `PublicationDate` = ? WHERE BookId = ?";

			int rowsUpdated = template.update(sql, args);

			return rowsUpdated == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<BookEntity> AllBook() {
		List<BookEntity> list = null;

		try {
			list = template.query("select * from book", new BookRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean deletedata(int bookId) {
		try {

			Object[] args = { bookId };
			int data = template.update("delete from book where bookId=?", args);

			if (data == 1) {
				return true;
			}

		} catch (Exception e2) {
			System.out.println(e2);
		}
		return false;
	}

	public int getTotalQuantity() {
		String sql = "SELECT SUM(TotalQuantity) FROM Book";
		return template.queryForObject(sql, Integer.class);
	}

	public int getAvailableQuantity() {
		String sql = "SELECT SUM(AvailableQuantity) FROM Book WHERE Status = 'Available'";
		return template.queryForObject(sql, Integer.class);
	}

	public void increaseAvailableQuantity(int bookId, String status) {
		String sql = "UPDATE Book SET AvailableQuantity = AvailableQuantity + 1 , Status = ? WHERE BookId = ?";
		template.update(sql, bookId);
	}

	public int getAvailableQuantityByBookId(int bookId) {
		String sql = "SELECT AvailableQuantity FROM Book WHERE BookId = ?";
		return template.queryForObject(sql, Integer.class, bookId);
	}

}