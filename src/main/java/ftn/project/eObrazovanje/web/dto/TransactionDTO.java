package ftn.project.eObrazovanje.web.dto;

import ftn.project.eObrazovanje.model.Transaction;

public class TransactionDTO {
	
	private Long id;
	private String purpose;
	private String bankAcc;
	private double price;
	private String recipient;
	private StudentDTO studentDTO;
	
	public TransactionDTO(Transaction transaction){
		this.id = transaction.getId();
		this.purpose = transaction.getPurpose();
		this.bankAcc = transaction.getBankAccount();
		this.price = transaction.getPrice();
		this.recipient = transaction.getRecipient();
		this.studentDTO = new StudentDTO(transaction.getStudent());
	}
	
	public TransactionDTO(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getBankAcc() {
		return bankAcc;
	}
	public void setBankAcc(String bankAcc) {
		this.bankAcc = bankAcc;
	}
	public double getPrice() {
		return price;
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

	public StudentDTO getStudentDTO() {
		return studentDTO;
	}
	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}
	
	

}
