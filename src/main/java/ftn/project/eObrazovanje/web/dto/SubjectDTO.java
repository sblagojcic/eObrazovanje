package ftn.project.eObrazovanje.web.dto;

import ftn.project.eObrazovanje.model.Subject;

public class SubjectDTO {
	
	private Long id;
	private String name;
	private Integer semester;
	
	public SubjectDTO(Subject subject){
		this.id = subject.getId();
		this.name = subject.getName();
		this.semester = subject.getSemester();

	}
	
	public SubjectDTO(){}
	
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
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	
	
	
}
