package ftn.project.eObrazovanje.web.dto;

import ftn.project.eObrazovanje.model.Exam;

public class ExamDTO {
	
	private Long id;
	private Integer points;
	private boolean pass;
	private Long studentID;
	
	public ExamDTO(Exam exam){
		this.id = exam.getId();
		this.points = exam.getPoints();
		this.pass = exam.getPass();
		this.studentID = exam.getStudent().getId();
	}
	
	public ExamDTO(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Long getStudentID() {
		return studentID;
	}
	public void setStudentID(Long studentID) {
		this.studentID = studentID;
	}

}
