package com.youcode.taskflow.service.impl;

import com.youcode.taskflow.entities.Request;
import com.youcode.taskflow.enums.RequestStatus;
import com.youcode.taskflow.repository.RequestRepository;
import com.youcode.taskflow.service.JetonService;
import com.youcode.taskflow.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final JetonService jetonService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, JetonService jetonService) {
        this.requestRepository = requestRepository;
        this.jetonService = jetonService;
    }

    @Scheduled(cron = "0 0 * * * ?")
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

}
