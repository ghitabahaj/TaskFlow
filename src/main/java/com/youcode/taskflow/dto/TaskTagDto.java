package com.youcode.taskflow.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTagDto {
    private Long id;
    private Long taskId;
    private Long tagId;
}
