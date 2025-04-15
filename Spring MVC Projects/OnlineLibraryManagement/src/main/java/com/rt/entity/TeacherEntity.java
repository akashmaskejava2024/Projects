package com.rt.entity;

import javax.persistence.Entity;

@Entity
public class TeacherEntity {
	private int TeacherId;
	private String FirstName;
	private String LastName;
	private String Email;
	private String ContactNumber;
	private String Faculty;
	
	
	public TeacherEntity(){}



public TeacherEntity(int teacherId, String firstName, String lastName, String email, String contactNumber,String faculty) {
	super();
	this.TeacherId = teacherId;
	this.FirstName = firstName;
	this.LastName = lastName;
	this.Email = email;
	this.ContactNumber = contactNumber;
	this.Faculty = faculty;
	

		
	}



public int getTeacherId() {
	
	return TeacherId;
}
public void setTeacherId(int teacherId) {
	
	this.TeacherId = teacherId;
}
public String getFirstName() {
	
	return FirstName;
}


public void setFirstName(String firstName) {
	this.FirstName = firstName;
}

public String getLastName() {
	return LastName;
}


public void setLastName(String lastName) {
	this.LastName = lastName;
}




public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	this.Email = email;
}

public String getContactNumber() {
	return ContactNumber;
}

public void setContactNumber(String contactNumber) {
	this.ContactNumber = contactNumber;
}



public String getFaculty() {
	return Faculty;
}



public void setFaculty(String faculty) {
	Faculty = faculty;
}








}
