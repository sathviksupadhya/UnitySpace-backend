package com.Group1.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
    private String alertId;
    private String eventId;
    private String userId;
    private boolean hasSeen=false;
}
