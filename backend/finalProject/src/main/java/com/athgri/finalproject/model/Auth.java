package com.athgri.finalproject.model;

/**
 * The Auth class represents user authentication information
 */
public class Auth {
    private String username;
    private String password;

    public Auth(String username, String password, String role) {
        this.username = username;
        this.password = password;
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
