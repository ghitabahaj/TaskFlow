package com.youcode.taskflow.service;


import com.youcode.taskflow.enums.RequestStatus;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    void respondToTaskModificationRequest(Long requestId, RequestStatus status, Long managerId , Long userId);

}
