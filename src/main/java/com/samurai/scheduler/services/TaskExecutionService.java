package com.samurai.scheduler.services;

import com.samurai.scheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/*
This service is only responsible for executing the task.
For now this means that it prints the task name and marks
the task as completed.
 */
@Service
public class TaskExecutionService {
    @Autowired
    private TaskService taskService;

    public void executeTask(Task task) {
        System.out.println("Executing task " + task.getTaskName() + " at " + LocalDateTime.now());
        taskService.completeTask(task);
    }
}
