package ftn.project.eObrazovanje.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor extends User {

    @Id
    @GeneratedValue
    private Integer id;
    private String gender;
    private Date dateOfBirth;
    private String address;
    private String JMBG;
    private String title;
    private String picturePath;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<ProfessorRole> roles = new HashSet<ProfessorRole>();

    public Professor() {

    }

    public Professor(String gender, Date dateOfBirth, String address, String picturePath, String jMBG, String title,
                     Set<ProfessorRole> roles) {
        super();
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.picturePath = picturePath;
        JMBG = jMBG;
        this.title = title;
        this.roles = roles;
    }

    public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Set<ProfessorRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ProfessorRole> roles) {
        this.roles = roles;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

