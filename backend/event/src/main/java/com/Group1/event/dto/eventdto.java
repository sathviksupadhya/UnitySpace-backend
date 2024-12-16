package com.Group1.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class eventdto {
    private String eventTitle;
    private String eventDescription;
    private String eventImg;
    private LocalDateTime eventDate;
    private String eventType = "EVENT";
    private String userId;
}
