package com.example.demo.service.impl;

import com.example.demo.Roles;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        // Create User object with name and pass
        User user = new User(
                userDTO.getUsername(),
                // encrypt the password using spring security
                passwordEncoder.encode(userDTO.getPassword())
        );

        // Configure Role and add to the User
        String rolename = Roles.ROLE_USER.toString();
        Role role = roleRepository.findByName(rolename);
        if (role == null) {
            role = checkRoleExist(rolename);
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertToUserDto(User user){
        UserDTO userDto = new UserDTO();
        String username = user.getUsername();
        userDto.setUsername(username);
        return userDto;
    }

    private Role checkRoleExist(String rolename){
        Role role = new Role();
        role.setName(rolename);
        return roleRepository.save(role);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @Override
    public boolean checkUserCredentials(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Override
    public boolean userHasCategory(User user, Category category) {
        return user.getCategories().contains(category);
    }
}
