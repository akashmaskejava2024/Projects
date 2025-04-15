package com.rt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.dao.BookDao;
import com.rt.dao.IssueBookDao;
import com.rt.entity.BookEntity;

@Service
public class BookService {
	@Autowired
	BookDao bookDao;
	
	@Autowired
	IssueBookDao issuedBookDao;
	
	public boolean addBook(BookEntity entity) {

		return bookDao.addBook(entity);
	}

	public List<BookEntity> all() {
		List<BookEntity> books = bookDao.AllBook();
		return books;
	}

	public boolean update(BookEntity entity) {
		return bookDao.update(entity);
	}

	public boolean deletedata(int bookId) {

		return bookDao.deletedata(bookId);
	}

	public Map<String, Integer> calculateQuantities() {
		int totalQuantity = bookDao.getTotalQuantity();
		int availableQuantity =bookDao.getAvailableQuantity();

		Map<String, Integer> quantities = new HashMap<>();
		quantities.put("TotalQuantity", totalQuantity);
		quantities.put("AvailableQuantity", availableQuantity);

		return quantities;
	}
/*
	private int calculateAvailableQuantity() {
		int totalIssuedBooks = issueBookService.getTotalIssuedBooks();
		int totalQuantity = bookDao.getTotalQuantity();
		return totalQuantity - totalIssuedBooks;
	}

*/	/*
	 * public boolean isBookAvailable(int bookId) { int totalQuantity =
	 * bookDao.getTotalQuantityByBookId(bookId); int issuedQuantity =
	 * issuedBookDao.getIssuedQuantityByBookId(bookId); return issuedQuantity <
	 * totalQuantity; }
	 */
	public void increaseAvailableQuantity(int bookId, String status) {
		bookDao.increaseAvailableQuantity(bookId, status);

	}

	public boolean isBookAvailable(int bookId) {
		int availableQuantity = bookDao.getAvailableQuantityByBookId(bookId);
		return availableQuantity > 0;
	}

}
