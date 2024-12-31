package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);

    User findUserByUsername(String username);

    List<UserDTO> findAllUsers();

    User getCurrentUser();

    boolean checkUserCredentials(User user, String rawPassword);
}
