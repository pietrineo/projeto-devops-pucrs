package com.pucrs.devops.taskmanager.service;

import com.pucrs.devops.taskmanager.dto.TaskDTO;

import java.util.List;

public interface ITaskService {
    TaskDTO create(TaskDTO task);
    List<TaskDTO> findAll();
    TaskDTO findById(Long id);
    TaskDTO update(Long id, TaskDTO updatedTask);
    void delete(Long id);
}
