package com.taskmanager.controller;


import com.taskmanager.dto.request.AddMemberRequest;
import com.taskmanager.dto.request.CreateWorkspaceRequest;
import com.taskmanager.dto.response.WorkspaceResponse;
import com.taskmanager.model.User;
import com.taskmanager.model.Workspace;
import com.taskmanager.service.interfaces.UserService;
import com.taskmanager.service.interfaces.WorkspaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<WorkspaceResponse> create(@RequestBody CreateWorkspaceRequest request) {
        User creator = userService.getUserById(request.getCreatorId());
        Workspace workspace = workspaceService.createWorkspace(request.getName(), creator);
        return new ResponseEntity<>(mapToResponse(workspace), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<Void> addMembersByEmail(@PathVariable Long id,
                                                  @RequestBody @Valid AddMemberRequest request) {
        workspaceService.addMembersByEmail(id, request.getEmails());
        return ResponseEntity.ok().build();
    }

    private WorkspaceResponse mapToResponse(Workspace w) {
        return WorkspaceResponse.builder()
                .id(w.getId())
                .name(w.getName())
                .createdBy(w.getCreatedBy().getName())
                .members(w.getMembers().stream().map(User::getName).toList())
                .build();
    }
}
