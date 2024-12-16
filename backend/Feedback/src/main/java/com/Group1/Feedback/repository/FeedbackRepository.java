package com.Group1.Feedback.repository;

import com.Group1.Feedback.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {
    List<Feedback> findByEventId(String eventId);

    List<Feedback> findByUserId(String userId);

    Feedback findByEventIdAndUserId(String eventId, String userId);
}
