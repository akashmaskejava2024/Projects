package com.collegemanagement.entity;

public class StudentAcademicInfo {
    private int id;
    private int studentId;
    private int academicYear; // 1st Year, 2nd Year, etc.
    private String calendarYear; // Format: "YYYY-YY"

    public StudentAcademicInfo() {}

    public StudentAcademicInfo(int studentId, int academicYear, String calendarYear) {
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.calendarYear = calendarYear;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getAcademicYear() { return academicYear; }
    public void setAcademicYear(int academicYear) { this.academicYear = academicYear; }

    public String getCalendarYear() { return calendarYear; }
    public void setCalendarYear(String calendarYear) { this.calendarYear = calendarYear; }
}