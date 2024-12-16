package com.Group1.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class authentication {
    private String userId;
    private String userName;
    private String password;
    private String role;
}
