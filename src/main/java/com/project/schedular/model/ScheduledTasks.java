package com.project.schedular.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "scheduled_tasks")
public class ScheduledTasks {
    @Id
    private String id;
    private String name;
    private LocalDateTime scheduledTime;
    private String status; // PENDING or COMPLETED
}
