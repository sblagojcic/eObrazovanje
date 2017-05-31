package ftn.project.eObrazovanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Document {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String path;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Student student;

	public Document() {
		super();
	}

	public Document(Integer id, String name, String path,Student student) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.student=student;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Student getstudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
