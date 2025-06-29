package com.taskmanager.service.interfaces;

import com.taskmanager.dto.request.CreateTaskRequest;
import com.taskmanager.enums.TaskStatus;
import com.taskmanager.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
    Task getTaskById(Long id);
    void updateTaskStatus(Long taskId, TaskStatus status);
    List<Task> getAllTasks();

}
