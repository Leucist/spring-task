package com.example.demo.repository;

import com.example.demo.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<UserTask, Long> {
    // List<UserTask> findAllByUserId(Long user_id);
    List<UserTask> findAllByCategoryId(Long category_id);
}
