package ftn.project.eObrazovanje.web.dto;

import ftn.project.eObrazovanje.model.Document;

public class DocumentDTO {
	
	private Long id;
	private String name;
	private String path;
	private Long studentID;
	
	public DocumentDTO(Document document){
		this.id = document.getId();
		this.name = document.getName();
		this.path = document.getPath();
		this.studentID = document.getStudent().getId();
	}
	public DocumentDTO(){}
	
	
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getStudentID() {
		return studentID;
	}
	public void setStudentID(Long studentID) {
		this.studentID = studentID;
	}
}