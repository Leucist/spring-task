package com.example.demo.service.impl;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<UserTask> getTaskById (Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public void addTaskToCategory(Long categoryId, UserTask task, User user) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null && category.getUser().equals(user)) {
            taskRepository.save(task);
        }
    }

    @Override
    public void updateTask(UserTask task) {
        taskRepository.save(task);
    }

    @Override
    public void removeTask (Long taskId) {
        // Remove the task if it's found
        taskRepository.findById(taskId).ifPresent(taskRepository::delete);
    }
}
