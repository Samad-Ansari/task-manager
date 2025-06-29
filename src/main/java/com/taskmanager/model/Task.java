package com.taskmanager.model;

import com.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "DATETIME", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "DATETIME")
    private LocalDateTime updatedDate;

    @Column(name = "due_date", columnDefinition = "DATETIME")
    private LocalDateTime dueDate;

}
