package com.nt.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProjectStatusDTO {
    private Long studentId;
    private Long projectId;
    public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	private String phase;
    private String updateDescription;
    private MultipartFile uiImage; // Store the file
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
	public String getUpdateDescription() {
		return updateDescription;
	}
	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}
	public MultipartFile getUiImage() {
		return uiImage;
	}
	public void setUiImage(MultipartFile uiImage) {
		this.uiImage = uiImage;
	}
	public ProjectStatusDTO(Long studentId,Long projectId, String phase, String updateDescription, MultipartFile uiImage) {
		super();
		this.studentId = studentId;
		this.projectId = projectId;
		this.phase = phase;
		this.updateDescription = updateDescription;
		this.uiImage = uiImage;
	}
    public ProjectStatusDTO() {
		// TODO Auto-generated constructor stub
	}
    
    
    

}
