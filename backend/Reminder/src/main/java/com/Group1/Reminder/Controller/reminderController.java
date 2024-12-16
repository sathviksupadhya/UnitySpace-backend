package com.Group1.Reminder.Controller;

import com.Group1.Reminder.dto.*;
import com.Group1.Reminder.feign.eventClient;
import com.Group1.Reminder.feign.userClient;
import com.Group1.Reminder.model.Reminder;
import com.Group1.Reminder.repository.ReminderRepository;
import com.Group1.Reminder.service.ReminderService;
import com.netflix.discovery.converters.Auto;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reminder")
public class reminderController{

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private eventClient eventclient;

    @Autowired
    private userClient userclient;

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private JavaMailSender  javaMailSender;

    @PostMapping("/create")
    public Response<ReminderDTO> createReminder(@RequestBody ReminderDTO reminderDTO) {
        return reminderService.createReminder(reminderDTO);
    }

    @GetMapping("/sendsms/{remid}")
    public String SendSms(@PathVariable("remid") String remId) {
        Optional<Reminder> r = reminderRepository.findById(remId);
        if(r.isEmpty()){
            return "Reminder not found";
        }
        Reminder  reminder = r.get();
        eventModel event = eventclient.getEvent(reminder.getEventId());
        if(event == null){
            return "Event not found";
        }
        User user = userclient.getResidentById(reminder.getUserId());
        String message = "we have an "+ event.getEventTitle() +" event on " + reminderService.formatDateTime(event.getEventDate());
        reminder.setNeedSms(false);
        reminderRepository.save(reminder);
        return reminderService.SendSms(user.getPhoneNumber(), message);
    }

    @GetMapping("/sendcall/{remid}")
    public String SendCall(@PathVariable("remid") String remId) {
        Optional<Reminder> r = reminderRepository.findById(remId);
        if(r.isEmpty()){
            return "Reminder not found";
        }
        Reminder  reminder = r.get();
        eventModel event = eventclient.getEvent(reminder.getEventId());
        if(event == null){
            return "Event not found";
        }
        User user = userclient.getResidentById(reminder.getUserId());
        String message = "we have an "+ event.getEventTitle() +" event on " + reminderService.formatDateTime(event.getEventDate());
        reminder.setNeedCall(false);
        reminderRepository.save(reminder);
        return reminderService.SendCall(user.getPhoneNumber(), message);
    }

    @GetMapping("/getbyUserId/{userId}")
    public List<fullDetails> getReminderByUserId(@PathVariable("userId") String userId) {
        return reminderService.getReminderByUserId(userId);
    }

    @Scheduled(fixedRate = 60000)
    @GetMapping("/call")
    public void Call() {
       reminderRepository.findAll().forEach(reminder -> {
           eventModel event = eventclient.getEvent(reminder.getEventId());
           if(event == null){
               return;
           }
           LocalDateTime eventDate = event.getEventDate();
           LocalDateTime currentTime = LocalDateTime.now();
           if (ChronoUnit.MINUTES.between(currentTime, eventDate) <= 30) {
               User user = userclient.getResidentById(reminder.getUserId());
               String message = "we have an " + event.getEventTitle() + " event on " + reminderService.formatDateTime(event.getEventDate());
               if (reminder.isNeedSms()) {
                   reminderService.SendSms(user.getPhoneNumber(), message);
                   Reminder r = reminderRepository.findById(reminder.getRemId()).get();
                   r.setNeedSms(false);
                   reminderRepository.save(r);
               }
               if (reminder.isNeedCall()) {
                   Reminder r = reminderRepository.findById(reminder.getRemId()).get();
                   r.setNeedCall(false);
                   reminderRepository.save(r);
                   System.out.println(user.getPhoneNumber());
                   reminderService.SendCall(user.getPhoneNumber(), message);
               }
               if (reminder.isNeedEmail()) {
                   Reminder r = reminderRepository.findById(reminder.getRemId()).get();
                   r.setNeedEmail(false);
                   reminderRepository.save(r);
                   reminderService.SendEmail(user.getEmail(), message);
               }
               reminderRepository.deleteById(reminder.getRemId());
           }
       });
    }

    @GetMapping("/sendEmail")
    public String SendEmail(@RequestParam String email, @RequestParam String message) {
        reminderService.SendEmail(email, message);
        return "you may receive an email now!!!";
    }
//
    @GetMapping("/sendUrgentsmsAndCall")
    public String SendUrgentSmsAndCall(@RequestParam String Message) {
        List<User> users = userclient.getAllUsers();
        for(User i: users) {
            reminderService.SendSms(i.getPhoneNumber(), Message);
            reminderService.SendCall(i.getPhoneNumber(), Message);
            reminderService.SendEmail(i.getEmail(), Message);
        }
        return "you may receive a message and call now!!!";
    }
}
