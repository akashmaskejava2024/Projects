package com.collegemanagement.entity;

public class ExamScore {
    private int id;
    private int studentId; // FK to Student
    private int courseId;  // FK to Course
    private int semester;
    private int marks;
    private int totalMarks;

    // Constructors
    public ExamScore() {}

    public ExamScore(int id, int studentId, int courseId, int semester, int marks, int totalMarks) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.marks = marks;
        this.totalMarks = totalMarks;
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}