package com.youcode.taskflow.repository;

import com.youcode.taskflow.entities.Request;
import com.youcode.taskflow.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatusAndCreationTimeBefore(RequestStatus status, LocalDateTime creationTime);
}
