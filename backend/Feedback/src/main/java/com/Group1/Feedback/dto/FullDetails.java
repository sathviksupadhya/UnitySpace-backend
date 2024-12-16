package com.Group1.Feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullDetails {
    private String feedbackId;
    private String feedbackMessage;
    private String eventId;
    private String userId;
    private String userName;
    private String role;
    private String email;
    private String phoneNumber;
    private String image;
    private String status;
    private String eventTitle;
    private String eventDescription;
    private String eventImg;
    private LocalDateTime eventDate;
}
