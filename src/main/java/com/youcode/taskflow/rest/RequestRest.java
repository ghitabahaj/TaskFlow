package com.youcode.taskflow.rest;

import com.youcode.taskflow.enums.RequestStatus;
import com.youcode.taskflow.service.RequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requests")
public class RequestRest {

    private final RequestService requestService;

    public RequestRest(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/respond-to-modification-request/{requestId}/{status}/{managerId}")
    public ResponseEntity<String> respondToTaskModificationRequest(
            @PathVariable Long requestId,
            @PathVariable RequestStatus status,
            @PathVariable Long managerId) {
        try {
            requestService.respondToTaskModificationRequest(requestId, status, managerId);
            return new ResponseEntity<>("Response to modification request processed successfully.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Request not found.", HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
