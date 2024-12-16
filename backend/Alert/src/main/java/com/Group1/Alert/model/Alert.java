package com.Group1.Alert.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Alert {
    @Id
    private String alertId;
    private String eventId;
    private String userId;
    private boolean hasSeen=false;
}
