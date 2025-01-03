package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(max = 255, message = "Username cannot exceed 255 characters.")
    private String username;

    @Column(nullable = false)
    @Size(max = 255, message = "Password cannot exceed 255 characters.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // - Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Role> getRoles() { return roles; }
    public List<Category> getCategories() { return categories; }

    // - Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
    public void setCategories(List<Category> categories) { this.categories = categories; }
}
