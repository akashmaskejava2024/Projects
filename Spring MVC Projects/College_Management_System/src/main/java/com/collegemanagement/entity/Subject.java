package com.collegemanagement.entity;

public class Subject {
    private int id;
    private String name;
    private int courseId;
    private int semester;

    public Subject() {}
    public Subject(int id, String name, int courseId, int semester) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.semester = semester;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}
