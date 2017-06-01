package ftn.project.eObrazovanje.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String role;
    private String name;
    private String lastName;
    private String userName;
    private String password;

    public User() {

    }

    public User(Long id, String role, String name, String lastName, String userName, String password) {
        super();
        this.id = id;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

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

