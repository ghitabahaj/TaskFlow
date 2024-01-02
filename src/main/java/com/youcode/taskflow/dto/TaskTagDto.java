package com.youcode.taskflow.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTagDto {
    private Long id;
    private TaskDto task;
    private TagDto tag;
}
