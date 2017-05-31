package ftn.project.eObrazovanje.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Obligation {

    @Id
    @GeneratedValue
    private Integer id;
    private String oblitagionType; //test kolokvijum usmeni 
    private Date date;

    private Integer points;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Subject subject;

    public Obligation() {

    }

    public Obligation(Integer id, String oblitagionType, Date date, Integer points, Subject subject) {
        super();
        this.id = id;
        this.oblitagionType = oblitagionType;
        this.date = date;
        this.points = points;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

