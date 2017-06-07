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
    private String obligationType; //test kolokvijum usmeni 
    private Date dateOfObligation;

    private Integer points;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JsonBackReference
    private Subject subject;

    public Obligation() {

    }

    public Obligation(Long id, String obligationType, Date dateOfObligation, Integer points, Subject subject) {
        super();
        this.id = id;
        this.obligationType = obligationType;
        this.dateOfObligation = dateOfObligation;
        this.points = points;
        this.subject = subject;

    }
    public Obligation(String obligationType, Date dateOfObligation, Integer points, Subject subject) {
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


	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}

