package com.collegemanagement.entity;

public class StudentCourse {
    private int id;
    private int studentId;
    private int courseId;
    private int professorId;
    private int semester;

    public StudentCourse() {}

    public StudentCourse(int id, int studentId, int courseId, int professorId, int semester) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.professorId = professorId;
        this.semester = semester;
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

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}