package com.project.schedular.repository;

import com.project.schedular.model.ScheduledTasks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduledTasksRepository extends MongoRepository<ScheduledTasks, String> {
}
