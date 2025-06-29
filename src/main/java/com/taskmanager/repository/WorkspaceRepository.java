package com.taskmanager.repository;

import com.taskmanager.model.Workspace;
import com.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findByCreatedBy(User user);
}
