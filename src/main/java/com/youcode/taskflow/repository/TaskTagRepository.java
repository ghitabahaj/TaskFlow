package com.youcode.taskflow.repository;

import com.youcode.taskflow.entities.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
