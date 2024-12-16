package com.Group1.Alert.repository;

import com.Group1.Alert.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends MongoRepository<Alert,String> {

    List<Alert> findByUserId(String userId);


    Optional<Alert> findByUserIdAndEventId(String userId, String eventid);
}
