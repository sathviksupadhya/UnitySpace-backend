package com.Group1.authentication.Repository;

import com.Group1.authentication.Model.authentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface authenticationRepo extends MongoRepository<authentication,String> {

    Optional<authentication> findByUserName(String username);

    authentication findByUserId(String userid);
}
