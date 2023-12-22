package com.youcode.taskflow.service.impl;


import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.repository.TaskRepository;
import com.youcode.taskflow.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public Task createTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
