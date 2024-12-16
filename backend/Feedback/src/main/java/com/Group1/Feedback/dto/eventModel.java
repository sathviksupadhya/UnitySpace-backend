package com.Group1.Feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class eventModel {
    private String eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventImg;
    private LocalDateTime eventDate;
    private String eventType;
    private String userId;
}
