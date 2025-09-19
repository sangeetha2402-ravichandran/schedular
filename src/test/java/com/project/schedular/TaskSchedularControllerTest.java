package com.project.schedular;

import com.project.schedular.controller.DocumentController;
import com.project.schedular.controller.ScheduledTasksController;
import com.project.schedular.model.Documents;
import com.project.schedular.model.ScheduledTasks;
import com.project.schedular.service.DocumentService;
import com.project.schedular.service.TaskSchedularService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskSchedularControllerTest {

    private MockMvc mockMvc;
    private TaskSchedularService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(TaskSchedularService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ScheduledTasksController(service)).build();
    }

    @Test
    void testGetAllTasks() throws Exception {
        when(service.getScheduledTasks()).thenReturn(Arrays.asList(
                new ScheduledTasks("1", "Task1", LocalDateTime.now(), "PENDING"),
                new ScheduledTasks("2", "Task2", LocalDateTime.now(),"PENDING")));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Task1"));
    }

    @Test
    void testExecuteTasks() throws Exception {
        ScheduledTasks scheduledTasks = new ScheduledTasks("1", "Task1", LocalDateTime.now(), "PENDING");
        doAnswer(invocation -> {
            ScheduledTasks t = invocation.getArgument(0);
            t.setStatus("COMPLETED"); // simulate business logic
            return null;
        }).when(service).executeTasks(any(ScheduledTasks.class));

        mockMvc.perform(post("/api/tasks/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                  "id": "1",
                  "name": "Task1",
                  "scheduledTime": "2025-09-19T21:27",
                  "status": "PENDING"
                }
                """))
            .andExpect(status().isOk());
        verify(service, times(1)).executeTasks(scheduledTasks);

    }
}
