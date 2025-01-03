package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategoriesByUser (User user);
    List<UserTask> getTasksByCategory (Category category);
    Optional<Category> getCategoryById(Long id);

    void addCategory(User user, String name);
    void removeCategory(Long categoryId);
}
