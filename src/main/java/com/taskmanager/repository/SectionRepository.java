package com.taskmanager.repository;

import com.taskmanager.model.Section;
import com.taskmanager.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByWorkspace(Workspace workspace);
    List<Section> findByWorkspaceId(Long workspaceId);
}
