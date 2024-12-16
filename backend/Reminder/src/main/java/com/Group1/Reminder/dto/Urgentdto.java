package com.Group1.Reminder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urgentdto {
    private String phoneNumber;
    private String message;
}
