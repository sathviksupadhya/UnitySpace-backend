package com.Group1.Feedback.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Feedback {
    @Id
    private String feedbackId;
    private String feedbackMessage;
    private String eventId;
    private String userId;
}
