package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        if (isUserAuthenticated()) {
            // Redirect to the profile page as default
            return "redirect:/profile";
        } else {
            return "redirect:/login";
        }
    }

    private boolean isUserAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
}
