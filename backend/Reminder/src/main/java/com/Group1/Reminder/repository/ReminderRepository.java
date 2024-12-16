package com.Group1.Reminder.repository;

import com.Group1.Reminder.model.Reminder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReminderRepository extends MongoRepository<Reminder,String> {
    List<Reminder> findByUserId(String userId);
}
