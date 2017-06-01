package ftn.project.eObrazovanje.web.dto;

import ftn.project.eObrazovanje.model.User;

public class UserDTO {
	private Long id;
	private String role;
	private String  name;
	private String lastName;
	private String userName;
	private String password;
	
	
	public UserDTO(User user){
		this.id = user.getId();
		this.role = user.getRole();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.userName = user.getUserName();
		this.password = user.getPassword();
	}
	
	public UserDTO(){}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
