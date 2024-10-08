package com.samurai.scheduler.controllers;

import com.samurai.scheduler.models.Task;
import com.samurai.scheduler.models.TaskDTO;
import com.samurai.scheduler.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(@Autowired TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/schedule")
    public String scheduleTask(@Valid @RequestBody TaskDTO taskDto) {
        Task newTask = taskDto.toTask();
        taskService.scheduleTask(newTask);
        return "Task scheduled to run at " + newTask.getScheduledTime() + ".";
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }
}
