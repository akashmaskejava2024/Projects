package com.collegemanagement.entity;

import java.util.Date;

public class Attendance {
    private int id;
    private int studentId; // FK to Student
    private Date date;
    private String status; // "Present" or "Absent"

    // Constructors
    public Attendance() {}

    public Attendance(int id, int studentId, Date date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
    
    
    // Getters and Setters
    // ...
} 
