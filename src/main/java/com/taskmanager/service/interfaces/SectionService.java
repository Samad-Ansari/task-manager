package com.taskmanager.service.interfaces;

import com.taskmanager.model.Section;

public interface SectionService {
    Section createSection(String name, Integer order, Long workspaceId);
}
