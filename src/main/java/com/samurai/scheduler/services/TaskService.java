package com.samurai.scheduler.services;

import com.samurai.scheduler.components.DynamicTaskScheduler;
import com.samurai.scheduler.models.Task;
import com.samurai.scheduler.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DynamicTaskScheduler dynamicTaskScheduler;

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void schedulePendingTasks() {
        List<Task> pendingTasks = taskRepository.findByStatusAndScheduledTimeAfter("PENDING", LocalDateTime.now());
        for (Task task: pendingTasks) {
            dynamicTaskScheduler.scheduleTask(task);
        }
    }

    public void scheduleTask(Task task) {
        taskRepository.save(task);
        dynamicTaskScheduler.scheduleTask(task);
    }

    public void completeTask(Task task) {
        task.setStatus("COMPLETED");
        taskRepository.save(task);
    }

    public void cancelTask(Task task) {
        task.setStatus("CANCELLED");
        taskRepository.save(task);
    }
}
