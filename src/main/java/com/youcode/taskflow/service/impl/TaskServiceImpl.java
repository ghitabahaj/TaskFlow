package com.youcode.taskflow.service.impl;


import com.youcode.taskflow.entities.Tag;
import com.youcode.taskflow.entities.Task;
import com.youcode.taskflow.entities.TaskTag;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.repository.TagRepository;
import com.youcode.taskflow.repository.TaskRepository;
import com.youcode.taskflow.repository.TaskTagRepository;
import com.youcode.taskflow.repository.UserRepository;
import com.youcode.taskflow.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TagRepository tagRepository;

    private final TaskTagRepository taskTagRepository;


    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));
    }

    @Override
    public Task createTask(Task task, Long userId , List<String> tagNames) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé."));

        if (task.getStartDate() != null && task.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de début de la tâche ne peut pas être dans le passé.");
        }

        if (task.getStartDate() != null && task.getStartDate().isAfter(LocalDate.now().plusDays(3))) {
            throw new IllegalArgumentException("La planification de la tâche est limitée à 3 jours à l'avance.");
        }

        if (tagNames == null || tagNames.isEmpty()) {
            throw new IllegalArgumentException("Au moins un tag est requis pour la tâche.");
        }

        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByTagName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setTagName(tagName);
                tagRepository.save(tag);
            }
            tags.add(tag);
        }

        for (Tag tag : tags) {
            TaskTag taskTag = new TaskTag();
            taskTag.setTask(task);
            taskTag.setTag(tag);
            taskTagRepository.save(taskTag);
        }

        task.setAssignedUser(user);
        return taskRepository.save(task);
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
