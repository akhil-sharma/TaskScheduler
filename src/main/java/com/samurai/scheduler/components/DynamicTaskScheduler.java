package com.samurai.scheduler.components;

import com.samurai.scheduler.models.Task;
import com.samurai.scheduler.services.TaskExecutionService;
import com.samurai.scheduler.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class DynamicTaskScheduler {
    private final TaskScheduler taskScheduler;
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>(); // may be used later.
    private final TaskExecutionService taskExecutionService;

    public DynamicTaskScheduler(@Autowired TaskScheduler taskScheduler, @Autowired TaskExecutionService taskExecutionService) {
        this.taskScheduler = taskScheduler;
        this.taskExecutionService = taskExecutionService;
    }

    public void scheduleTask(Task task) {
        Runnable taskRunnable = () -> {
            taskExecutionService.executeTask(task);
            scheduledTasks.remove(task.getId());
        };
        Instant scheduleTime = task.getScheduledTime().atZone(ZoneId.systemDefault()).toInstant();
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(taskRunnable, scheduleTime);
        scheduledTasks.put(task.getId(), scheduledFuture);
    }

    public void cancelTask(Long taskId) {
        ScheduledFuture<?> scheduledTask = scheduledTasks.get(taskId);
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
            scheduledTasks.remove(taskId);
            System.out.println("Cancelled task " + taskId);
        }
    }
}
