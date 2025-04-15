package com.rt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.dao.IssueBookDao;
import com.rt.entity.IssueBookEntity;

@Service
public class issueBookService{
	
	
	@Autowired
	  IssueBookDao issueBookDao;
	
	public boolean add(IssueBookEntity issueBook) {
		return issueBookDao.add(issueBook);
		
	}

	public List<IssueBookEntity> issuerecord() {
		return issueBookDao.issuerecord();
	}

	
	public boolean isBookAvailable(int bookId) {
	    int issuedQuantity = issueBookDao.getIssuedQuantityByBookId(bookId);
	    int totalQuantity = issueBookDao.getTotalQuantityByBookId(bookId);
	    return issuedQuantity < totalQuantity;
	}



		public  int getTotalIssuedBooks() {
			return issueBookDao.getTotalIssuedBooks();
		}

		public List<IssueBookEntity> getByUserId(int userId,String userType) {
			 return issueBookDao.getByUserId(userId,userType);		}

		
		
		
		public void updateStatus(int issueId, String status) {
			 issueBookDao.updateIssueBookStatus(issueId, status);
			
		}
		
			}
