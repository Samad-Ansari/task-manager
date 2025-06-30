package com.taskmanager.service.impl;

import com.taskmanager.dto.request.CreateTaskRequest;
import com.taskmanager.dto.response.SectionWithTasksResponse;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.enums.TaskStatus;
import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Section;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.SectionRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final SectionRepository sectionRepository;

    @Override
    @Transactional
    public Task createTask(CreateTaskRequest request) {
        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignee not found"));

        Section section = sectionRepository.findById(request.getSectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Section not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .assignee(assignee)
                .section(section)
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .build();

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    public List<SectionWithTasksResponse> getTasksByWorkspaceId(Long workspaceId) {
        List<Section> sections = sectionRepository.findByWorkspaceId(workspaceId);

        return sections.stream().map(section ->
            SectionWithTasksResponse.builder()
                .sectionId(section.getId())
                .sectionName(section.getName())
                .tasks(section.getTasks().stream().map(task ->
                    TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .dueDate(task.getDueDate())
                        .assigneeName(task.getAssignee().getName())
                        .build()
                ).collect(Collectors.toList()))
                .build()
        ).collect(Collectors.toList());
    }


}
