package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserTask;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void addTaskToCategory(Long categoryId, UserTask task, User user);
    void updateTask(UserTask task);
    // void changeTaskStatus (Long taskId, String status);
    void removeTask (Long taskId);

    Optional<UserTask> getTaskById (Long taskId);
    List<UserTask> getTasksByCategoryId(Long categoryId);
}
