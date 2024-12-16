package com.Group1.Reminder.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Reminder {
    @Id
    private String remId;
    private String eventId;
    private String userId;
    private boolean needSms;
    private boolean needCall;
    private boolean needEmail;
}
