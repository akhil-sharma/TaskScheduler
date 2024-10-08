package com.samurai.scheduler;

import com.samurai.scheduler.services.TaskService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulerApplication {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    @PostConstruct
    public void scheduleTasksOnStartup() {
        taskService.schedulePendingTasks();
    }
}
