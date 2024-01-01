package com.youcode.taskflow.service.impl;


import com.youcode.taskflow.dto.TaskDto;
import com.youcode.taskflow.entities.*;
import com.youcode.taskflow.enums.RequestStatus;
import com.youcode.taskflow.enums.Role;
import com.youcode.taskflow.enums.TaskStatus;
import com.youcode.taskflow.repository.*;
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

    private final RequestRepository requestRepository;



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

        validateTaskDate(task);

        if (tagNames == null || tagNames.isEmpty()) {
            throw new IllegalArgumentException("Au moins un tag est requis pour la tâche.");
        }

        task.setAssignedUser(user);
        task.setCreatedBy(user);
        Task savedTask = taskRepository.save(task);

        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByTagName(tagName);
            if (tag == null) {
                tag = new Tag();
                tag.setTagName(tagName);
                tagRepository.save(tag);
            }

            TaskTag taskTag = new TaskTag();
            taskTag.setTask(savedTask);
            taskTag.setTag(tag);
            taskTagRepository.save(taskTag);
        }

        if (user.getRole() == Role.MANAGER && task.getAssignedUser() == null) {
            throw new IllegalArgumentException("Le manager ne peut pas laisser une tâche sans être affecté.");
        }

        return savedTask;
    }

    private void validateTaskDate(Task task) {
        if (task.getStartDate() != null && task.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de début de la tâche ne peut pas être dans le passé.");
        }

        if (task.getStartDate() != null && task.getStartDate().isAfter(LocalDate.now().plusDays(3))) {
            throw new IllegalArgumentException("La planification de la tâche est limitée à 3 jours à l'avance.");
        }
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));

        if (isTaskUpdateAllowed(existingTask, updatedTask)) {
            if (taskDeadlineNotExceeded(existingTask)) {
                existingTask.setTaskstatus(TaskStatus.DONE);
            } else {
                throw new IllegalStateException("La mise à jour de la tâche n'est pas autorisée car la date limite est dépassée.");
            }
            return taskRepository.save(existingTask);
        } else {
            throw new IllegalStateException("La mise à jour de la tâche n'est pas autorisée selon les contraintes spécifiées.");
        }
    }

    private boolean taskDeadlineNotExceeded(Task task) {
        return task.getEndDate() == null || task.getEndDate().isAfter(LocalDate.now());
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

    @Override
    public void requestTaskModification(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Tâche non trouvée."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé."));

        Request request = new Request();
        request.setUser(user);
        request.setTask(task);
        request.setStatus(RequestStatus.PENDING);

        requestRepository.save(request);
    }

    @Override
    public void respondToTaskModificationRequest(Long requestId, RequestStatus status) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Demande de modification non trouvée."));


        request.setStatus(status);
        requestRepository.save(request);


        if (status == RequestStatus.ACCEPTED) {

        }
    }

    private boolean isTaskUpdateAllowed(Task existingTask, Task updatedTask) {

        return true;
    }


    private boolean isTaskDeletionAllowed(Task taskToDelete) {

        return true;
    }



}
