package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.TaskService;
import com.example.demo.service.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
