package com.youcode.taskflow.dto;

import com.youcode.taskflow.enums.RequestStatus;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto {

    private Long id;
    private Long userId;
    private Long taskId;
    private RequestStatus status;
    private LocalDateTime creationTime;
    private Long processedByUserId;
    private LocalDateTime processedTime;

}
