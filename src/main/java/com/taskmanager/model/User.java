package com.taskmanager.model;

import com.taskmanager.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Workspaces created by user
    @Builder.Default
    @OneToMany(mappedBy = "createdBy")
    private Set<Workspace> createdWorkspaces = new HashSet<>();

    // Workspaces user is a member of
    @Builder.Default
    @ManyToMany(mappedBy = "members")
    private Set<Workspace> workspaces = new HashSet<>();
}
