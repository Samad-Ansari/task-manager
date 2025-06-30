package com.taskmanager.service.impl;

import com.taskmanager.exception.ResourceNotFoundException;
import com.taskmanager.model.Section;
import com.taskmanager.model.Workspace;
import com.taskmanager.repository.SectionRepository;
import com.taskmanager.repository.WorkspaceRepository;
import com.taskmanager.service.interfaces.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    @Transactional
    public Section createSection(String name, Integer order, Long workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        Section section = Section.builder()
                .name(name)
                .orderIndex(order)
                .workspace(workspace)
                .build();

        return sectionRepository.save(section);
    }
}
