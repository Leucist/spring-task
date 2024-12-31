package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class TaskDTO {
    private Long id;
    @NotEmpty
    private String title;
    private String description;
    @NotEmpty
    private String status;

    // - Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(@NotEmpty String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(@NotEmpty String status) {
        this.status = status;
    }
}
