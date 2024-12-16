package com.Group1.Reminder.dto;

import com.Group1.Reminder.model.Reminder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class fullDetails {
    private Reminder rem;
    private eventModel event;
    private User user;
}
