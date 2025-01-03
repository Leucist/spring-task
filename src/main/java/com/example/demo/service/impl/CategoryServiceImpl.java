package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.UserTask;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategoriesByUser (User user) {
        return categoryRepository.findAllByUser(user);
    }

    @Override
    public List<UserTask> getTasksByCategory (Category category) {
        return category.getTasks();
    }

    @Override
    public void addCategory(User user, String name) {
        Category category = new Category();
        category.setUser(user);
        category.setName(name);
        categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Long categoryId) {
        // Remove the category if it's found
        categoryRepository.findById(categoryId).ifPresent(categoryRepository::delete);
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}
