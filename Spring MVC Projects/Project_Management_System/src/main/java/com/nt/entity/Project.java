package com.nt.entity;
public class Project {
    private Long id;
    private String title;
    private String description;
    private Long studentId; // Foreign Key reference to Student

    // Constructor
    public Project() {}

    public Project(Long id, String title, String description, Long studentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.studentId = studentId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}