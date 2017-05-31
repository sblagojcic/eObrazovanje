package ftn.project.eObrazovanje.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer semester;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Obligation> obligations = new HashSet<Obligation>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<ProfessorRole> professorRole = new HashSet<ProfessorRole>();

    @ManyToMany(mappedBy = "subjects",targetEntity = Student.class, fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<Student>();

    public Subject() {

    }

    public Subject(Integer id, String name, Integer semester, Set<Obligation> obligations,
			Set<ProfessorRole> professorRole, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.obligations = obligations;
		this.professorRole = professorRole;
		this.students = students;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Set<Obligation> getObligations() {
		return obligations;
	}

	public void setObligations(Set<Obligation> obligations) {
		this.obligations = obligations;
	}

	public Set<ProfessorRole> getProfessorRole() {
        return professorRole;
    }

    public void setProfessorRole(Set<ProfessorRole> professorRole) {
        this.professorRole = professorRole;
    }

    @JsonIgnore
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

}

