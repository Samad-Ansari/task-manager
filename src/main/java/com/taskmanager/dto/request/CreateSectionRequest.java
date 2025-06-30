package com.taskmanager.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSectionRequest {
    @NotNull
    private String name;

    @NotNull
    private Integer orderIndex;

    @NotNull
    private Long workspaceId;
}
