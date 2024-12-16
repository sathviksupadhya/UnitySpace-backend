package com.Group1.Reminder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String role;
    private String email;
    private String phoneNumber;
    private String image;
    private String status;
}
