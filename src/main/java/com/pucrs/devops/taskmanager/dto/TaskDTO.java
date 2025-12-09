package com.pucrs.devops.taskmanager.dto;

public record TaskDTO( Long id,
         String title,
         String description,
         boolean completed) {
}
