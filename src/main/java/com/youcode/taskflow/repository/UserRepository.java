package com.youcode.taskflow.repository;

import com.youcode.taskflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}