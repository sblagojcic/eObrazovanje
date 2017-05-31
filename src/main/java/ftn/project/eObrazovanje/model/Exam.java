package ftn.project.eObrazovanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exam {

	@Id
	@GeneratedValue
	private Integer id;
	private int points;
	private Boolean pass;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Obligation obligation;


	public Exam() {
		super();
	}
	

	public Exam(Integer id, int points, Boolean pass, Student student, Obligation obligation) {
		super();
		this.id = id;
		this.points = points;
		this.pass = pass;
		this.student = student;
		this.obligation = obligation;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Obligation getObligation() {
		return obligation;
	}


	public void setObligation(Obligation obligation) {
		this.obligation = obligation;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
