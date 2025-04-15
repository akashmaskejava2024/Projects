package com.collegemanagement.entity;

public class Activity {
    private int id;
    private int studentId; // FK to Student
    private String type; // Sports, Cultural, Other
    private String subtype; // Cricket, Dance, etc.
    private String competitionLevel; // College, State, National
    private int rank; // Position secured in the competition
    private String achievement;
    private String image; // URL or Base64 image for proof

    // Constructors
    public Activity() {}

    public Activity(int id, int studentId, String type, String subtype, String competitionLevel, int rank, String achievement, String image) {
        this.id = id;
        this.studentId = studentId;
        this.type = type;
        this.subtype = subtype;
        this.competitionLevel = competitionLevel;
        this.rank = rank;
        this.achievement = achievement;
        this.image = image;
    }

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCompetitionLevel() {
        return competitionLevel;
    }

    public void setCompetitionLevel(String competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}