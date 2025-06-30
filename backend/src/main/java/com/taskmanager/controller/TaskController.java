package com.taskmanager.controller;

import com.taskmanager.dto.request.CreateTaskRequest;
import com.taskmanager.dto.request.UpdateTaskStatusRequest;
import com.taskmanager.dto.response.SectionWithTasksResponse;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.model.Task;
import com.taskmanager.service.interfaces.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        Task task = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResponse(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(mapToResponse(task));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Void> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestBody @Valid UpdateTaskStatusRequest request) {

        taskService.updateTaskStatus(taskId, request.getStatus());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<SectionWithTasksResponse>> getTasksByWorkspace(
            @PathVariable Long workspaceId) {
        return ResponseEntity.ok(taskService.getTasksByWorkspaceId(workspaceId));
    }

    private TaskResponse mapToResponse(Task task) {
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
