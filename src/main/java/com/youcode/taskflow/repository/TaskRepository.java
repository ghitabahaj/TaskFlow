package com.youcode.taskflow.repository;

import com.youcode.taskflow.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
