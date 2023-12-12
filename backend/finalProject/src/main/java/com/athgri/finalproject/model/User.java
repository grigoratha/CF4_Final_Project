package com.athgri.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * The User class represents user information including
 * an identifier (ID), username, password, email, and role.
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String username;
    private String password;
    private String email;
    private String role;

    public User() {}

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        String userDetails = "";

        userDetails += "ID: " + id + "\n";
        userDetails += "Username: " + username + "\n";
        userDetails += "Password: " + password + "\n";
        userDetails += "email: " + email + "\n";
        userDetails += "Role: " + role;

        return userDetails;
    }
}