package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoriesByUser (User user);
    List<UserTask> getTasksByCategory (Category category);

    void addCategory(User user, String name);
    void removeCategory(Category category);
}
