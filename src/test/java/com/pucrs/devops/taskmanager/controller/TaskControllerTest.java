package com.pucrs.devops.taskmanager.controller;

import com.pucrs.devops.taskmanager.config.BaseIntegrationTest;
import com.pucrs.devops.taskmanager.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest extends BaseIntegrationTest {

    @Test
    void shouldCreateTaskSuccessfully() throws Exception {
        TaskDTO task = new TaskDTO(null, "New Task", "Task description", false);

        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task description"));
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        TaskDTO task = new TaskDTO(null, "Task for list", "List description", false);

        String response = mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TaskDTO createdTask = objectMapper.readValue(response, TaskDTO.class);

        mockMvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].title", is(createdTask.title())));
    }

    @Test
    void shouldGetTaskById() throws Exception {
        TaskDTO task = new TaskDTO(null, "Task to get by id", "Description by id", false);

        String response = mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TaskDTO createdTask = objectMapper.readValue(response, TaskDTO.class);

        mockMvc.perform(get("/api/v1/task/{id}", createdTask.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(createdTask.id().intValue())))
                .andExpect(jsonPath("$.title", is("Task to get by id")));
    }

    @Test
    void shouldUpdateTaskSuccessfully() throws Exception {
        TaskDTO task = new TaskDTO(null,"Task before update", "Old description", false);

        String response = mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TaskDTO createdTask = objectMapper.readValue(response, TaskDTO.class);

        TaskDTO update = new TaskDTO(null, "Task after update", "New description", false);

        mockMvc.perform(put("/api/v1/task/{id}", createdTask.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Task after update")))
                .andExpect(jsonPath("$.description", is("New description")));
    }

    @Test
    void shouldDeleteTaskSuccessfully() throws Exception {
        TaskDTO task = new TaskDTO(null, "Task to delete", "Delete description", false);

        String response = mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        TaskDTO createdTask = objectMapper.readValue(response, TaskDTO.class);

        mockMvc.perform(delete("/api/v1/task/{id}", createdTask.id()))
                .andExpect(status().isNoContent());

        // Verifica que o recurso foi removido
        mockMvc.perform(get("/api/v1/task/{id}", createdTask.id()))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenUpdateNonExistingTask() throws Exception {
        TaskDTO update = new TaskDTO(null, "Updated", "Desc", false);

        mockMvc.perform(put("/api/v1/task/{id}", 9999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenGetNonExistingTask() throws Exception {
        mockMvc.perform(get("/api/v1/task/{id}", 9999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenDeleteNonExistingTask() throws Exception {
        mockMvc.perform(delete("/api/v1/task/{id}", 9999L))
                .andExpect(status().isNotFound());
    }
}
