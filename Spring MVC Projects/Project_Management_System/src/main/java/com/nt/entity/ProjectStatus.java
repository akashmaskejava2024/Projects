package com.nt.entity;
import java.util.Date;

public class ProjectStatus {
    private Long id;
    private Long projectId;  // Foreign Key reference to Project
    private Long studentId;  // Foreign Key reference to Student
    private String phase; // Requirement Gathering, Design, Development, Testing, Deployment
    private String uiImage; // Path or URL of the uploaded image
    private String updateDescription; // Short description of the update
    private String feedback; // Professor's feedback
    private Date updatedAt; // Timestamp of the latest update

    // Constructor
    public ProjectStatus() {}

    public ProjectStatus(Long id, Long projectId, Long studentId, String phase, String uiImage, 
                         String updateDescription, String feedback, Date updatedAt) {
        this.id = id;
        this.projectId = projectId;
        this.studentId = studentId;
        this.phase = phase;
        this.uiImage = uiImage;
        this.updateDescription = updateDescription;
        this.feedback = feedback;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getUiImage() {
        return uiImage;
    }

    public void setUiImage(String uiImage) {
        this.uiImage = uiImage;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}