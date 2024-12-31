package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    // - Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // - Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public void trimSpacesInCredentials() {
        username = username.trim();
        password = password.trim();
    }

    public boolean hasNulls() {
        return hasEmptyUsername() || hasEmptyPassword();
    }
    public boolean hasEmptyUsername() {
        return username == null || username.isEmpty();
    }
    public boolean hasEmptyPassword() {
        return password == null || password.isEmpty();
    }
}
