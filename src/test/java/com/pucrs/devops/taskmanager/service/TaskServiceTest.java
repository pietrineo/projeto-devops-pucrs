package com.pucrs.devops.taskmanager.service;

import com.pucrs.devops.taskmanager.dto.TaskDTO;
import com.pucrs.devops.taskmanager.exception.NotFoundException;
import com.pucrs.devops.taskmanager.model.Task;
import com.pucrs.devops.taskmanager.repository.ITaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private ITaskRepository repository;

    @InjectMocks
    private TaskService service;

    private Task task;
    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        task = new Task(1L, "Título", "Descrição", false);
        taskDTO = new TaskDTO(1L, "Título", "Descrição", false);
    }

    @Test
    void givenValidTaskDTO_whenCreate_thenReturnsSavedTaskDTO() {
        // Arrange
        when(repository.save(any(Task.class))).thenReturn(task);

        // Act
        TaskDTO result = service.create(taskDTO);

        // Assert
        assertNotNull(result);
        assertEquals(task.getId(), result.id());
        assertEquals(task.getTitle(), result.title());
        assertEquals(task.getDescription(), result.description());
        assertEquals(task.isCompleted(), result.completed());

        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void whenFindAll_thenReturnsListOfTaskDTOs() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(task));

        // Act
        List<TaskDTO> result = service.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(task.getId(), result.get(0).id());
    }

    @Test
    void givenExistingTaskId_whenFindById_thenReturnsTaskDTO() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(task));

        TaskDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(task.getId(), result.id());
    }

    @Test
    void givenNonExistingTaskId_whenFindById_thenThrowsNotFoundException() {
        String expectedMessageError = "Tarefa com ID 1 não encontrada";

        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException err = assertThrows(NotFoundException.class, () -> service.findById(1L));
        assertEquals(expectedMessageError, err.getMessage());
    }

    @Test
    void givenExistingTaskIdAndValidData_whenUpdate_thenReturnsUpdatedTaskDTO() {
        TaskDTO updatedDTO = new TaskDTO(1L, "Novo Título", "Nova Descrição", true);
        Task updatedTask = new Task(1L, "Novo Título", "Nova Descrição", true);

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenReturn(updatedTask);

        TaskDTO result = service.update(1L, updatedDTO);

        assertEquals(updatedDTO.title(), result.title());
        assertEquals(updatedDTO.description(), result.description());
        assertTrue(result.completed());
    }

    @Test
    void givenNonExistingTaskId_whenUpdate_thenThrowsNotFoundException() {
        String expectedMessageError = "Tarefa com ID 99 não encontrada";
        when(repository.findById(99L)).thenReturn(Optional.empty());

        TaskDTO updatedDTO = new TaskDTO(99L, "Inexistente", "---", true);

        NotFoundException err = assertThrows(NotFoundException.class, () -> service.update(99L, updatedDTO));
        assertEquals(expectedMessageError, err.getMessage());
    }

    @Test
    void givenExistingTaskId_whenDelete_thenRemovesTask() {
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        service.delete(1L);

        verify(repository, times(1)).delete(task);
    }

    @Test
    void givenNonExistingTaskId_whenDelete_thenThrowsNotFoundException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.delete(99L));
    }
}
