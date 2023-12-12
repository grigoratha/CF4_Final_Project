package com.athgri.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * The UserSession class represents a user session including
 * an identifier (ID), username, role, authentication token, and timestamp.
 */
@Entity
@Table(name = "session")
public class UserSession implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private Long id;
    private String username;
    private String role;
    @Column(nullable = false)
    private String token;
    private String timestamp;

    public UserSession() {
        this.token = UUID.randomUUID().toString();
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( new Date() );
        this.role = "user";
    }

    public UserSession(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.token = UUID.randomUUID().toString();
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format( new Date() );
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        String userSessionDetails = "";

        userSessionDetails += "ID: " + id + "\n";
        userSessionDetails += "Token: " + token;

        return userSessionDetails;
    }
}