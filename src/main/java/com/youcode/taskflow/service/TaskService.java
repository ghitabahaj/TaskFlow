package com.youcode.taskflow.service;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.enums.RequestStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    Task createTask(Task task, Long userId , List<String> tagNames);

    Task updateTask(Long id, Task updatedTask);

     void deleteTask(Long id);

    void requestTaskModification(Long taskId, Long userId);

    void respondToTaskModificationRequest(Long requestId, RequestStatus status);

    void replaceTask(Task task, User newUser);


}
