package com.Group1.event.Model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class eventModel {

    @Id
    private String eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventImg;
    private LocalDateTime eventDate;
    private String eventType;
    private String userId;

}
