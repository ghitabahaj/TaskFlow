package com.youcode.taskflow.service.impl;

import com.youcode.taskflow.entities.Request;
import com.youcode.taskflow.entities.User;
import com.youcode.taskflow.enums.RequestStatus;
import com.youcode.taskflow.enums.Role;
import com.youcode.taskflow.repository.RequestRepository;
import com.youcode.taskflow.repository.UserRepository;
import com.youcode.taskflow.service.JetonService;
import com.youcode.taskflow.service.RequestService;
import com.youcode.taskflow.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;
    private final JetonService jetonService;

    private final TaskService taskService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, JetonService jetonService , UserRepository userRepository , TaskService taskService) {
        this.requestRepository = requestRepository;
        this.jetonService = jetonService;
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void checkUnansweredRequests() {
        try {
            List<Request> unansweredRequests = requestRepository
                    .findByStatusAndCreationTimeBefore(RequestStatus.PENDING, LocalDateTime.now().minusHours(12));

            for (Request request : unansweredRequests) {
                jetonService.doubleModificationTokens(request.getUser().getId());

                request.setStatus(RequestStatus.AUTO_PROCESSED);
                requestRepository.save(request);

                System.out.println("Unanswered request (ID: " + request.getId() + ") processed successfully.");
            }
        } catch (Exception e) {

            System.err.println("An error occurred: " + e.getMessage());
        }
    }


    @Override
    public void respondToTaskModificationRequest(Long requestId, RequestStatus status, Long managerId , Long userId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Demande de modification non trouvée."));

        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager non trouvé."));

        User User = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé."));



        if (manager.getRole() == Role.MANAGER) {
            if (request.getStatus() == RequestStatus.PENDING) {
                request.setStatus(status);
                request.setProcessedBy(manager);
                request.setProcessedTime(LocalDateTime.now());
                requestRepository.save(request);

                if (status == RequestStatus.ACCEPTED) {
                    taskService.replaceTask(request.getTask(),User);
                }
            } else {
                throw new IllegalStateException("La demande de modification a déjà été traitée.");
            }
        } else {
            throw new IllegalStateException("Seuls les managers peuvent répondre aux demandes de modification.");
        }
    }

}
