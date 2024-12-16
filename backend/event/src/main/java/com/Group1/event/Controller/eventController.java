package com.Group1.event.Controller;

import com.Group1.event.Model.eventModel;
import com.Group1.event.Service.eventService;
import com.Group1.event.dto.eventdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class eventController {

    @Autowired
    private eventService service;

    @PostMapping("/add")
    public ResponseEntity<eventModel> addEvent(@RequestBody eventdto event) {
        return new ResponseEntity<>(service.addEvent(event), HttpStatus.CREATED);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<eventModel>> getAllEvents() {
        return new ResponseEntity<>(service.getAllEvents(), HttpStatus.OK);
    }

    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<eventModel> updateEvent(@PathVariable String eventId, @RequestBody eventModel event) {
        eventModel updatedEvent = service.update(eventId, event);
        if (updatedEvent != null) {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // If event not found
        }
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        service.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getEvent/{eventId}")
    public eventModel getEvent(@PathVariable String eventId) {
        return service.getEvent(eventId);
    }

    @GetMapping("/getbyUserId/{userId}")
    public ResponseEntity<List<eventModel>> getEventsByUserId(@PathVariable String userId) {
        List<eventModel> events = service.getEventsByUserId(userId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }


}
