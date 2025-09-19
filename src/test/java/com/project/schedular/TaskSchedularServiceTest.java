package com.project.schedular;

import com.project.schedular.model.ScheduledTasks;
import com.project.schedular.repository.ScheduledTasksRepository;
import com.project.schedular.service.TaskSchedularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskSchedularServiceTest {

    private ScheduledTasksRepository repository;
    private TaskSchedularService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ScheduledTasksRepository.class);
        service = new TaskSchedularService(repository);
    }


    @Test
    void testExecuteTasks() {
        ScheduledTasks task1 = ScheduledTasks.builder()
                .id("1")
                .name("Task 1")
                .scheduledTime(LocalDateTime.now().minusMinutes(1))
                .status("PENDING")
                .build();

        ScheduledTasks task2 = ScheduledTasks.builder()
                .id("2")
                .name("Task 2")
                .scheduledTime(LocalDateTime.now().plusMinutes(5))
                .status("PENDING")
                .build();
        when(repository.findAll()).thenReturn(Arrays.asList(task1,task2));
        service.executeTasks(task1);
        assertEquals("COMPLETED", task1.getStatus());
        assertEquals("PENDING", task2.getStatus());
        verify(repository, times(1)).save(task1);
       verify(repository, never()).save(task2);


    }

    @Test
    void testgetAllTasks() throws IOException {
        List<ScheduledTasks> tasks = Arrays.asList(new ScheduledTasks("1", "Task1", LocalDateTime.now(), "COMPLETED"),
                new ScheduledTasks("2", "Task2", LocalDateTime.now(), "PENDING"));

        when(repository.findAll()).thenReturn(tasks);
        service.getScheduledTasks();
        assertEquals("COMPLETED", tasks.get(0).getStatus());
        assertEquals("PENDING", tasks.get(1).getStatus());
        verify(repository, times(1)).findAll();


    }


    }
