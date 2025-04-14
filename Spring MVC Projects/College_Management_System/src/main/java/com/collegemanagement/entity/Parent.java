package com.collegemanagement.entity;

public class Parent {
    private int id;
    private int studentId; // FK to Student
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    // Constructors
    public Parent() {}

    public Parent(int id, int studentId, String name, String email, String password, String phoneNumber) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    // Getters and Setters
    // ...
    
    
}