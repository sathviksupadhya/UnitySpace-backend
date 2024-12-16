package com.Group1.Feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackDto {

    private String feedbackId;
    private String feedbackMessage;
    private String eventId;
    private String userId;

}
