package ftn.project.eObrazovanje.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Integer id;
	private String gender;
	private Date dateOfBirth;
	private String address;
	private String JMBG;
	private String picturePath;

	@ManyToMany(targetEntity = Subject.class, fetch = FetchType.LAZY)
	private Set<Subject> subjects = new HashSet<Subject>();
	 
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Document> documents = new HashSet<Document>();

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Exam> exams = new HashSet<Exam>();

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Payment> payments = new HashSet<Payment>();

	public Student() {
		super();
	}


	public Student(Integer id, String gender, Date dateOfBirth, String address, String jMBG, String picturePath,
			Set<Subject> subjects, Set<Document> documents, Set<Exam> exams, Set<Payment> payments) {
		super(); 
		this.id = id;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.JMBG = jMBG;
		this.picturePath = picturePath;
		this.subjects = subjects;
		this.documents = documents;
		this.exams = exams;
		this.payments = payments;
	}


	public Set<Exam> getExams() {
		return exams;
	}


	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}


	public Set<Payment> getPayments() {
		return payments;
	}


	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
}
