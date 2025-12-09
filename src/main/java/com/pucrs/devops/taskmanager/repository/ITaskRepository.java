package com.pucrs.devops.taskmanager.repository;

import com.pucrs.devops.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Long> {
}
