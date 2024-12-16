package com.Group1.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class authdto {
    @Field(name = "userName")
    @Indexed(unique = true)
    private String userName;
    private String password;
    @Pattern(regexp = "^(RESIDENT|ADMIN)$", message = "Role must be either 'Resident' or 'Admin'")
    private String role;
}
