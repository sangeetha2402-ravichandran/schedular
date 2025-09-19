package com.project.schedular.service;

import com.project.schedular.model.ScheduledTasks;
import com.project.schedular.repository.ScheduledTasksRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskSchedularService {

    private final ScheduledTasksRepository scheduledTasksRepository;

    public TaskSchedularService(ScheduledTasksRepository scheduledTasksRepository) {
        this.scheduledTasksRepository = scheduledTasksRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void executeTasks(ScheduledTasks scheduledTasks ) {
        LocalDateTime now = LocalDateTime.now();
        scheduledTasksRepository.findAll().forEach(task ->{
            if (scheduledTasks.getScheduledTime().isBefore(now) && "PENDING".equals(scheduledTasks.getStatus()))
            {
                scheduledTasks.setStatus("COMPLETED");
                scheduledTasksRepository.save(task);
                System.out.println("SCHEDULED TASK DETAILS " + task.getName()  +  task.getStatus());
            }
        });

    }


    public List<ScheduledTasks> getScheduledTasks() {
           return scheduledTasksRepository.findAll();

    }

}
