package ftn.project.eObrazovanje.web.dto;

import java.util.Date;

import ftn.project.eObrazovanje.model.Exam;
import ftn.project.eObrazovanje.model.Obligation;

public class ObligationDTO {
	
	private Long id;
	private String obligationType;
	private Date dateOfObligation;
	private Integer maxPoints;
	private SubjectDTO subjectDTO;
	private Exam exam;
	
	public ObligationDTO(Obligation obligation){
		this.id = obligation.getId();
		this.obligationType = obligation.getOblitagionType();
		this.dateOfObligation = obligation.getDateOfObligation();
		this.maxPoints = obligation.getPoints();
		this.subjectDTO = new SubjectDTO(obligation.getSubject());
		this.exam=obligation.getExam();
	}
	
	public ObligationDTO(){}

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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
