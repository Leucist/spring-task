package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty(message = "Password should be empty")
    private String password;

    // - Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // - Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}
