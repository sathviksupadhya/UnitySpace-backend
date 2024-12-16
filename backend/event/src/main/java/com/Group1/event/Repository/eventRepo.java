package com.Group1.event.Repository;

import com.Group1.event.Model.eventModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface eventRepo extends MongoRepository<eventModel, String>{
    List<eventModel> findByUserId(String userId);
}
