package com.ecommerce.ecommercemvcapplication.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "User name should not be empty")
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "User email should not be empty")
    private String email;
    @NotEmpty(message = "User mobile-number should not be empty")
    private String mobileNumber;
    @NotEmpty(message = "User username should not be empty")
    private String username;
    @NotEmpty(message = "User password should not be empty")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
