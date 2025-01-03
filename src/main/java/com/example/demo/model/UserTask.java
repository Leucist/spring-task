package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 255, message = "Title cannot exceed 255 characters.")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 25, nullable = false)
    @Size(max = 25, message = "Status cannot exceed 25 characters.")
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public UserTask() {}
    public UserTask(String title, String description, String status, Category category) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
    }

    // - Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
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
    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
