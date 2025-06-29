package com.taskmanager.util;

import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.model.Task;

public class TaskMapper {

    public static TaskResponse toDto(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .assigneeName(task.getAssignee().getName())
                .dueDate(task.getDueDate())
                .createdDate(task.getCreatedDate())
                .updatedDate(task.getUpdatedDate())
                .build();
    }
}
