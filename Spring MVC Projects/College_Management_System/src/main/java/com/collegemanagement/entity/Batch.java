package com.collegemanagement.entity;

public class Batch {
    private int id;
    private int courseId;
    private int semester;
    private int professorId;

    // Constructor
    public Batch() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public Batch(int id, int courseId, int semester, int professorId) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.semester = semester;
		this.professorId = professorId;
	}
    
    

}