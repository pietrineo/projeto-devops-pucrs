package com.pucrs.devops.taskmanager.controller;

import com.pucrs.devops.taskmanager.dto.TaskDTO;
import com.pucrs.devops.taskmanager.service.ITaskService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final ITaskService service;

    public TaskController(ITaskService service) {
        this.service = service;
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task) {
        TaskDTO created = service.create(task);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/task")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskDTO> getAllTasks(@PathVariable Long id) {
        TaskDTO taskDTO = service.findById(id);
        return ResponseEntity.ok(taskDTO);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTaskDTO = service.update(id, taskDTO);
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
