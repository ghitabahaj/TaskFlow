package com.youcode.taskflow.dto;

import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JetonDto {
    private Long id;
    private int dailyReplacementTokens;
    private int monthlyDeletionTokens;
    private Long userId;
    private Integer modificationRequests;
    private LocalDateTime lastModificationResponse;
}
