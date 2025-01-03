package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserTask;

import java.util.List;

public interface TaskService {
    void addTaskToCategory(Long categoryId, UserTask task, User user);
    // void removeTask (Long taskId);
    //
    // List<TaskDTO> getTasksByUser (Long userId);
    // UserTask getTask (Long taskId);
    //
    // void changeStatus (Long taskId, String status);
}
