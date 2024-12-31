package com.example.demo.controller;

import jakarta.validation.Valid;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model)
    {
        // Double-check for the empty data
        if (userDTO.hasNulls()) {
            if (userDTO.hasEmptyUsername())
                result.rejectValue("username", null, "Username must not be empty");
            if (userDTO.hasEmptyPassword())
                result.rejectValue("password", null, "Password must not be empty");
        }

        // Trim user input login and password
        userDTO.trimSpacesInCredentials();

        User existingUser = userService.findUserByUsername(userDTO.getUsername());

        if (existingUser != null) {
            result.rejectValue("username", null,
                    "There is already an account registered with the same username");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/login";
    }

    // handler method to display login page
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return "redirect:/profile";
        }
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    // handler method to handle login request
    @PostMapping("/login")
    public String loginOnPost(@Valid @ModelAttribute("user") UserDTO userDTO,
                              BindingResult result,
                              Model model) {
        userDTO.trimSpacesInCredentials();
        User existingUser = userService.findUserByUsername(userDTO.getUsername());

        if (existingUser == null) {
            result.rejectValue("username", null, "User with the given username does not exist");
        } else if (!userService.checkUserCredentials(existingUser, userDTO.getPassword())) {
            result.rejectValue("password", null, "Password is incorrect");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "login";
        }

        return "redirect:/profile";
    }
}
