package com.rt.entity;

public class IssueBookEntity {
private int  IssueId;
private int  UserId;
private String  UserType;
private int  BookId;
private String  IssueDate;
private String  DueDate;
private String  Status;

public IssueBookEntity(){}

public IssueBookEntity(int issueId, int userId, String userType, int bookId, String issueDate, String dueDate,
		String status) {
	super();
	IssueId = issueId;
	UserId = userId;
	UserType = userType;
	BookId = bookId;
	IssueDate = issueDate;
	DueDate = dueDate;
	Status = status;
}

public int getIssueId() {
	return IssueId;
}

public void setIssueId(int issueId) {
	IssueId = issueId;
}

public int getUserId() {
	return UserId;
}

public void setUserId(int userId) {
	UserId = userId;
}

public String getUserType() {
	return UserType;
}

public void setUserType(String userType) {
	UserType = userType;
}

public int getBookId() {
	return BookId;
}

public void setBookId(int bookId) {
	BookId = bookId;
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

public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}





}