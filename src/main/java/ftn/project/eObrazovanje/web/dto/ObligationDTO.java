package ftn.project.eObrazovanje.web.dto;

import java.util.Date;

import ftn.project.eObrazovanje.model.Obligation;

public class ObligationDTO {
	
	private Long id;
	private String obligationType;
	private Date dateOfObligation;
	private Integer points;
	private SubjectDTO subject;
	
	public ObligationDTO(Obligation obligation){
		this.id = obligation.getId();
		this.obligationType = obligation.getObligationType();
		this.dateOfObligation = obligation.getDateOfObligation();
		this.points = obligation.getPoints();
		this.subject = new SubjectDTO(obligation.getSubject());
	}
	
	public ObligationDTO(){}

	public ObligationDTO(String obligationType, Date dateOfObligation, Integer points,
			SubjectDTO subject, ExamDTO exam) {
		super();
		this.obligationType = obligationType;
		this.dateOfObligation = dateOfObligation;
		this.points = points;
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObligationType() {
		return obligationType;
	}

	public void setObligationType(String obligationType) {
		this.obligationType = obligationType;
	}

	public Date getDateOfObligation() {
		return dateOfObligation;
	}

	public void setDateOfObligation(Date dateOfObligation) {
		this.dateOfObligation = dateOfObligation;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}


}
