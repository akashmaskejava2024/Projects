package com.collegemanagement.entity;

public class StudentExamScore {
    private int id;
    private int studentId;
    private int subjectId;
    private int internalMarksObtained;
    private int internalMarksTotal;
    private int externalMarksObtained;
    private int externalMarksTotal;
    private int totalMarksObtained;
    private int totalMarksMax;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public int getInternalMarksObtained() { return internalMarksObtained; }
    public void setInternalMarksObtained(int internalMarksObtained) { this.internalMarksObtained = internalMarksObtained; }

    public int getInternalMarksTotal() { return internalMarksTotal; }
    public void setInternalMarksTotal(int internalMarksTotal) { this.internalMarksTotal = internalMarksTotal; }

    public int getExternalMarksObtained() { return externalMarksObtained; }
    public void setExternalMarksObtained(int externalMarksObtained) { this.externalMarksObtained = externalMarksObtained; }

    public int getExternalMarksTotal() { return externalMarksTotal; }
    public void setExternalMarksTotal(int externalMarksTotal) { this.externalMarksTotal = externalMarksTotal; }

    public int getTotalMarksObtained() { return totalMarksObtained; }
    public void setTotalMarksObtained(int totalMarksObtained) { this.totalMarksObtained = totalMarksObtained; }

    public int getTotalMarksMax() { return totalMarksMax; }
    public void setTotalMarksMax(int totalMarksMax) { this.totalMarksMax = totalMarksMax; }
}