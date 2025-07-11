package com.taskmanager.repository;

import com.taskmanager.model.Task;
import com.taskmanager.model.Section;
import com.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySection(Section section);
    List<Task> findByAssignee(User assignee);

}
