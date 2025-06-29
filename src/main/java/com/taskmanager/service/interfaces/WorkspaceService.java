package com.taskmanager.service.interfaces;

import com.taskmanager.model.User;
import com.taskmanager.model.Workspace;

import java.util.List;

public interface WorkspaceService {
    Workspace createWorkspace(String name, User creator);
    void addMembersByEmail(Long workspaceId, List<String> emails);
    Workspace getWorkspaceById(Long id);
}
