package com.Group1.Alert.controller;


import com.Group1.Alert.dto.eventModel;
import com.Group1.Alert.model.Alert;
import com.Group1.Alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alert")
public class AlertController {
    @Autowired
    private AlertService alertService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<eventModel>> getAlertByUserId(@PathVariable("userId") String userId) {
        List<eventModel> event = alertService.getAlertByUserId(userId);
        return event.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(event);
    }


    @PostMapping("/createAlert/{eventid}")
    public ResponseEntity<String> createAlert(@PathVariable String eventid) {
        return new ResponseEntity<>(alertService.createAlert(eventid), HttpStatus.CREATED);
    }

    @PostMapping("/createAlertforUsers/{userId}")
    public ResponseEntity<String> createAlertByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(alertService.createAlertByUserId(userId), HttpStatus.CREATED);
    }

    @PutMapping("/seen/{userid}/{eventid}")
    public ResponseEntity<Alert> updateAlert(@PathVariable("userid") String userId, @PathVariable("eventid") String eventid) {
        alertService.updateAlert(userId, eventid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}
