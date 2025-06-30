package com.taskmanager.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SectionWithTasksResponse {
    private Long sectionId;
    private String sectionName;
    private List<TaskResponse> tasks;
}
