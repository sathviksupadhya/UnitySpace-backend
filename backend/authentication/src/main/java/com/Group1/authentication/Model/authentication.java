package com.Group1.authentication.Model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class authentication {

    @Id
    private String userId;
    @Indexed(unique = true)
    private String userName;
    private String password;
    private String role = "RESIDENT";
}
