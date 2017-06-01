package ftn.project.eObrazovanje.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Obligation {

    @Id
    @GeneratedValue
    private Long id;
    private String oblitagionType; //test kolokvijum usmeni 
    private Date dateOfObligation;

    private Integer points;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JsonBackReference
	private Exam exam;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JsonBackReference
    private Subject subject;

    public Obligation() {

    }

    public Obligation(Long id, String oblitagionType, Date dateOfObligation, Integer points, Subject subject,Exam exam) {
        super();
        this.id = id;
        this.oblitagionType = oblitagionType;
        this.dateOfObligation = dateOfObligation;
        this.points = points;
        this.subject = subject;
        this.exam=exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOblitagionType() {
		return oblitagionType;
	}

	public void setOblitagionType(String oblitagionType) {
		this.oblitagionType = oblitagionType;
	}

    public Integer getpoints() {
        return points;
    }

    public void setpoints(Integer points) {
        this.points = points;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return dateOfObligation;
    }

    public void setDate(Date dateOfObligation) {
        this.dateOfObligation = dateOfObligation;
    }

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
    

}

