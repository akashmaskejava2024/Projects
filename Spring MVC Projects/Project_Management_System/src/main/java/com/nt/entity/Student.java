package com.nt.entity;


public class Student {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Long professorId; // Foreign Key reference to Professor

    // Constructor
    public Student() {}

    public Student(Long id, String name, String email, String password, Long professorId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.professorId = professorId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}