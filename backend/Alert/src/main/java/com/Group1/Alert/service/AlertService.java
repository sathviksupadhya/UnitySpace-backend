package com.Group1.Alert.service;


import com.Group1.Alert.dto.eventModel;
import com.Group1.Alert.feign.eventClient;
import com.Group1.Alert.feign.feignClient;
import com.Group1.Alert.model.Alert;
import com.Group1.Alert.dto.User;
import com.Group1.Alert.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private feignClient client;
    @Autowired
    private eventClient eventclient;

    public List<eventModel> getAlertByUserId(String userId) {
        List<Alert> alerts = alertRepository.findByUserId(userId);  // Now, this returns a List<Alert>
        alerts = alerts.stream()
                .filter(i -> !i.isHasSeen())
                .collect(Collectors.toList());
        List<eventModel> event = new ArrayList<>();
        for(Alert i : alerts){
            event.add(eventclient.getEvent(i.getEventId()));
        }
        return event;
    }



    public String createAlert(String eventid) {
        List<User> users = client.getAllUsers();
        for(User user : users){
            Alert alert = new Alert();
            alert.setUserId(user.getUserId());
            alert.setEventId(eventid);
            alertRepository.save(alert);
        }
        return "saved all";

    }


    public Alert updateAlert(String userId, String eventid) {
        // Find the alert by userId and eventId
        Optional<Alert> alertOptional = alertRepository.findByUserIdAndEventId(userId, eventid);
        if (alertOptional.isPresent()) {
            Alert alert = alertOptional.get();
            alert.setHasSeen(true);
            return alertRepository.save(alert);
        } else {
            // Handle the case where the alert is not found
            return null;
        }
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public String createAlertByUserId(String userId) {
        List<eventModel> events =  eventclient.getAllEvents().getBody();

        for(eventModel event : events){
            if (event.getEventDate().isAfter(LocalDateTime.now())) {
                Alert alert = new Alert();
                alert.setUserId(userId);
                alert.setEventId(event.getEventId());
                alertRepository.save(alert);
            }
        }
        return "saved all";
    }
}