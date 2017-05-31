package ftn.project.eObrazovanje.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue
	private Integer id;
	private String purpose;
	private String bankAccount;
	private double price;
	private String recipient;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;

	public Payment() {
		super();
	}

	public Payment(Integer id, String purpose, String bankAccount, double price, String recipient, Student student) {
		super();
		this.id = id;
		this.purpose = purpose;
		this.bankAccount = bankAccount;
		this.price = price;
		this.recipient = recipient;
		this.student = student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public double getPrice() {
		return price;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
