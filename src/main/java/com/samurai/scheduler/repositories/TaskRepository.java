package com.samurai.scheduler.repositories;

import com.samurai.scheduler.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusAndScheduledTimeAfter(String status, LocalDateTime time);
}
