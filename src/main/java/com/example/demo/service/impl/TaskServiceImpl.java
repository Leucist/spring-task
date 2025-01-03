package com.example.demo.service.impl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTaskToCategory(Long categoryId, UserTask task, User user) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null && category.getUser().equals(user)) {
            taskRepository.save(task);
        }
    }
}
