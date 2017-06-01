package ftn.project.eObrazovanje.web.dto;

import java.util.Date;

import ftn.project.eObrazovanje.model.Obligation;

public class ObligationDTO {
	
	private Long id;
	private String obligationType;
	private Date date;
	private Integer maxPoints;
	private SubjectDTO subjectDTO;
	
	public ObligationDTO(Obligation obligation){
		this.id = obligation.getId();
		this.obligationType = obligation.getOblitagionType();
		this.date = obligation.getDate();
		this.maxPoints = obligation.getPoints();
		this.subjectDTO = new SubjectDTO(obligation.getSubject());
	}
	public ObligationDTO(){}

	
	public String getObligationType() {
		return obligationType;
	}
	public void setObligationType(String obligationType) {
		this.obligationType = obligationType;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}
	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}
	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

}
