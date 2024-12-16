package com.Group1.Feedback.controller;

import com.Group1.Feedback.dto.FeedbackDto;
import com.Group1.Feedback.dto.FullDetails;
import com.Group1.Feedback.model.Feedback;
import com.Group1.Feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/feedback")
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/create-feedback")
    public ResponseEntity<FeedbackDto> postFeedback(@RequestBody FeedbackDto feedbackDto) {
        FeedbackDto createdFeedback = feedbackService.addFeedback(feedbackDto);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }


    @GetMapping("/get-feedbacks")
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @DeleteMapping("/delete{feedbackId}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("feedbackId") String feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok("Feedback with ID: " + feedbackId + " successfully deleted!");
    }

    @GetMapping("/get-feedback-by-eventId/{eventId}")
    public ResponseEntity<List<FullDetails>> getFeedbackByEventId(@PathVariable("eventId") String eventId) {
        List<FullDetails> feedbacks = feedbackService.getFeedbackByEventId(eventId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @GetMapping("/get-feedback-by-userId/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbackByUserId(@PathVariable("userId") String userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByUserId(userId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @GetMapping("/get-feedback-by-UserId-EventId/{eventId}/{userId}")
    public ResponseEntity<FeedbackDto> getFeedbackByEventIdAndUserId(@PathVariable("eventId") String eventId, @PathVariable("userId") String userId) {
        FeedbackDto feedback = feedbackService.getFeedbackByEventIdAndUserId(eventId, userId);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

}
