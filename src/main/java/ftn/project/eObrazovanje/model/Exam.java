package ftn.project.eObrazovanje.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Exam {

	@Id
	@GeneratedValue
	private Long id;
	private int points;
	private Boolean pass;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JsonBackReference
	private Subject subject;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JsonBackReference
	private Student student;
	

	public Exam() {
		super();
	}

	public Exam(Long id, int points, Boolean pass, Subject subject, Student student) {
		super();
		this.id = id;
		this.points = points;
		this.pass = pass;
		this.subject = subject;
		this.student = student;
	}










	public Subject getSubject() {
		return subject;
	}










	public void setSubject(Subject subject) {
		this.subject = subject;
	}










	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}




}
