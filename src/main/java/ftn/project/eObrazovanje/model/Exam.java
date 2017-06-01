package ftn.project.eObrazovanje.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Exam {

	@Id
	@GeneratedValue
	private Long id;
	private int points;
	private Boolean pass;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JsonBackReference
	private Student student;
	
	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonManagedReference
	private Set<Obligation> obligations = new HashSet<Obligation>();
	

	public Exam() {
		super();
	}







	public Exam(Long id, int points, Boolean pass, Student student, Set<Obligation> obligations) {
		super();
		this.id = id;
		this.points = points;
		this.pass = pass;
		this.student = student;
		this.obligations = obligations;
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

	

	public Set<Obligation> getObligations() {
		return obligations;
	}



	public void setObligations(Set<Obligation> obligations) {
		this.obligations = obligations;
	}



	
}
