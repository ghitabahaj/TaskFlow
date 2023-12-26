package com.youcode.taskflow.service.impl;


import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.repository.TaskRepository;
import com.youcode.taskflow.repository.UserRepository;
import com.youcode.taskflow.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));
    }

    @Override
    public Task createTask(Task task, Long userId) {
      return null;

    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));
        if (isTaskUpdateAllowed(existingTask, updatedTask)) {


            return taskRepository.save(existingTask);
        } else {
            throw new IllegalStateException("La mise à jour de la tâche n'est pas autorisée selon les contraintes spécifiées.");
        }

    }

    @Override
    public void deleteTask(Long id) {
        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));


        if (isTaskDeletionAllowed(taskToDelete)) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalStateException("La suppression de la tâche n'est pas autorisée selon les contraintes spécifiées.");
        }
    }

    private boolean isTaskUpdateAllowed(Task existingTask, Task updatedTask) {

        return true;
    }


    private boolean isTaskDeletionAllowed(Task taskToDelete) {

        return true;
    }


}
