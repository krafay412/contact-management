package com.rafay.contact_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest{
    @NotBlank(message = "User Name Should Not Be Blank")
    private String username;

    @Email(message = "Invalid Email Format")
    @NotBlank(message = "Email Should Not Be Blank")
    private String email;

    @Size(min = 8, message = "Password must be atleast 8 characters")
    private String password;

    @NotBlank(message = "Cnic cannot be blank")
    private String cnic;

    
    private String firstName;
    private String lastName;
}