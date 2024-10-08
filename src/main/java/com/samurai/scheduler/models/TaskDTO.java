package com.samurai.scheduler.models;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    @NotBlank(message = "This field is required.")
    private String taskName;

    @NotBlank(message = "This field is required.")
    private String status;

    @NotBlank(message = "This field is required.")
    private String scheduledTime;

    public Task toTask() {
        Task newTask = new Task();
        newTask.setTaskName(taskName);
        newTask.setStatus("PENDING");
        newTask.setScheduledTime(LocalDateTime.parse(this.scheduledTime));
        return newTask;
    }
}
