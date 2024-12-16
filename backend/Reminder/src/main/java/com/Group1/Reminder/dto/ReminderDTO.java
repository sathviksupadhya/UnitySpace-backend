package com.Group1.Reminder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReminderDTO {

    @NotNull(message = "Event ID is required.")
    private String eventId;

    @NotNull(message = "User ID is required.")
    private String userId;

    private boolean needSms = false;
    private boolean needCall = false;
    private boolean needEmail = false;
}
