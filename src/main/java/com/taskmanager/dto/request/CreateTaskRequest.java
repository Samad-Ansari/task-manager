package com.taskmanager.dto.request;

import com.taskmanager.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Assignee ID is required")
    private Long assigneeId;

    @NotNull(message = "Section ID is required")
    private Long sectionId;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    private LocalDateTime dueDate;
}
