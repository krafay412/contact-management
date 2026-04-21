package com.rafay.contact_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Wrong Format")
    private String email;

    @Size(min = 8, message = "Password must be atleast 8 characters")
    private String password;
}
