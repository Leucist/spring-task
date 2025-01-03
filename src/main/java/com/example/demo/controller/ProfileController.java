package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;
import com.example.demo.service.UserService;
import com.example.demo.service.TaskService;
import com.example.demo.service.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    private final UserService userService;
    private final TaskService taskService;
    private final CategoryService categoryService;

    public ProfileController(UserService userService, TaskService taskService, CategoryService categoryService) {
        this.userService = userService;
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            // Get tasks within their categories of the current user
            model.addAttribute("categories", categoryService.getCategoriesByUser(currentUser));
        }
        return "profile";
    }

    @PostMapping("/profile/addCategory")
    public String addCategory(@RequestParam("name") String name,
                              Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            categoryService.addCategory(currentUser, name);
        }

        showProfile(model);
        return "redirect:/profile";
    }

    @PostMapping("/profile/addTask")
    public String addTask(@RequestParam("categoryId") Long categoryId,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("status") String status,
                          Model model) {
        // Check the current user
        User currentUser = userService.getCurrentUser();
        Optional<Category> currentCategory = categoryService.getCategoryById(categoryId);
        if (currentUser != null && currentCategory.isPresent()) {
            UserTask userTask = new UserTask();
            userTask.setTitle(title);
            userTask.setDescription(description);
            userTask.setStatus(status);
            userTask.setCategory(currentCategory.get());

            taskService.addTaskToCategory(categoryId, userTask, currentUser);
        }

        return "redirect:/" + showProfile(model);
    }

    @PostMapping("/profile/editTaskStatus")
    public String editTaskStatus(@RequestParam("taskId") Long taskId,
                                 @RequestParam("status") String status) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            Optional<UserTask> taskOptional = taskService.getTaskById(taskId);
            if (taskOptional.isPresent()) {
                UserTask task = taskOptional.get();
                task.setStatus(status);
                taskService.updateTask(task);
            }
        }
        return "redirect:/profile";
    }

    @PostMapping("/profile/removeTask/{taskId}")
    public String removeTask(@PathVariable Long taskId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            taskService.removeTask(taskId);
        }
        return "redirect:/profile";
    }

    @PostMapping("/removeCategory/{categoryId}")
    public String removeCategory(@PathVariable Long categoryId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            categoryService.removeCategory(categoryId);
        }
        return "redirect:/profile";
    }
}
