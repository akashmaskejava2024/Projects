package com.collegemanagement.entity;

public class Student {
    private int id;
    private String name;
    private String email;
    private String password;
    private String rollNumber;
    private int semester;
    private int professorId; // FK to Professor

    // Constructors
    public Student() {}

    public Student(int id, String name, String email, String password, String rollNumber, int semester, int professorId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.professorId = professorId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}

    
    
}