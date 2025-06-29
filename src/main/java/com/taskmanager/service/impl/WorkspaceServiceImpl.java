package com.taskmanager.service.impl;

import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.User;
import com.taskmanager.model.Workspace;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.repository.WorkspaceRepository;
import com.taskmanager.service.interfaces.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Workspace createWorkspace(String name, User creator) {
        Workspace workspace = Workspace.builder()
                .name(name)
                .createdBy(creator)
                .build();

        workspace.getMembers().add(creator); // Add creator as manager/member
        return workspaceRepository.save(workspace);
    }

    @Override
    @Transactional
    public void addMembersByEmail(Long workspaceId, List<String> emails) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
            .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        List<User> users = userRepository.findByEmailIn(emails);

        if (users.size() != emails.size()) {
            throw new IllegalArgumentException("Some emails not registered as users");
        }

        workspace.getMembers().addAll(users);
        workspaceRepository.save(workspace);
    }


    @Override
    public Workspace getWorkspaceById(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));
    }
}
