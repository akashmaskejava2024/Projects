package com.rt.entity;

public class BorrowBook {
	
	private int id;
	private int BookId;
	private String StudentName;
	private String IssueDate;
	private String DueDate;
	
public BorrowBook() {}

public BorrowBook(int id, int bookId, String studentName, String issueDate, String dueDate) {
	super();
	this.id = id;
	BookId = bookId;
	StudentName = studentName;
	IssueDate = issueDate;
	DueDate = dueDate;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getBookId() {
	return BookId;
}

public void setBookId(int bookId) {
	BookId = bookId;
}

public String getStudentName() {
	return StudentName;
}

public void setStudentName(String studentName) {
	StudentName = studentName;
}

public String getIssueDate() {
	return IssueDate;
}

public void setIssueDate(String issueDate) {
	IssueDate = issueDate;
}

public String getDueDate() {
	return DueDate;
}

public void setDueDate(String dueDate) {
	DueDate = dueDate;
}


}
