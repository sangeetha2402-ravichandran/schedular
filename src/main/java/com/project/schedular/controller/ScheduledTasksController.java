package com.project.schedular.controller;


import com.project.schedular.model.ScheduledTasks;
import com.project.schedular.service.TaskSchedularService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class ScheduledTasksController {

    private final TaskSchedularService taskSchedularService;

    public ScheduledTasksController(TaskSchedularService taskSchedularService) {
        this.taskSchedularService = taskSchedularService;
    }

    @PostMapping("/create")
    public void createTask(@RequestBody ScheduledTasks scheduledTasks) {
        scheduledTasks.setStatus("PENDING");
        taskSchedularService.executeTasks(scheduledTasks);
    }

    @GetMapping
    public List<ScheduledTasks> getAllTasks() {
        return taskSchedularService.getScheduledTasks();

    }

}
