package com.taskmanager.controller;

import com.taskmanager.dto.request.CreateSectionRequest;
import com.taskmanager.dto.response.SectionResponse;
import com.taskmanager.model.Section;
import com.taskmanager.service.interfaces.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionResponse> create(@RequestBody CreateSectionRequest request) {
        Section section = sectionService.createSection(request.getName(), request.getOrderIndex(), request.getWorkspaceId());
        return new ResponseEntity<>(mapToResponse(section), HttpStatus.CREATED);
    }

    private SectionResponse mapToResponse(Section s) {
        return SectionResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .orderIndex(s.getOrderIndex())
                .workspaceId(s.getWorkspace().getId())
                .build();
    }
}
