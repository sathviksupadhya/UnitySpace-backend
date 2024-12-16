package com.Group1.event.Service;

import com.Group1.event.Model.eventModel;
import com.Group1.event.Repository.eventRepo;
import com.Group1.event.dto.eventdto;
import com.Group1.event.feign.alertClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class eventService {

    @Autowired
    private eventRepo repo;

    @Autowired
    private alertClient alertclient;


    public eventModel addEvent(eventdto event) {
        eventModel e = new eventModel();
        e.setEventTitle(event.getEventTitle());
        e.setEventDescription(event.getEventDescription());
        e.setEventDate(event.getEventDate());
        e.setEventImg(event.getEventImg());
        e.setEventType(event.getEventType());
        e.setUserId(event.getUserId());
        eventModel a = repo.save(e);
        alertclient.createAlert(a.getEventId());
        return a;
    }

    public List<eventModel> getAllEvents() {
        return repo.findAll();
    }

    public eventModel update(String eventId, eventModel event) {
        eventModel existingEvent = repo.findById(eventId).orElse(null);
        if (existingEvent != null) {
            existingEvent.setEventTitle(event.getEventTitle());
            existingEvent.setEventDescription(event.getEventDescription());
            existingEvent.setEventDate(event.getEventDate());
            existingEvent.setEventImg(event.getEventImg());
            existingEvent.setEventType(event.getEventType());
            return repo.save(existingEvent);
        } else {
            return null;
        }
    }

    public void deleteEvent(String eventId) {
        if (repo.existsById(eventId)) {
            repo.deleteById(eventId);
        }
    }

    public eventModel getEvent(String eventId) {
        return repo.findById(eventId).orElse(null);
    }

    public List<eventModel> getEventsByUserId(String userId) {
        return repo.findByUserId(userId);
    }
}
