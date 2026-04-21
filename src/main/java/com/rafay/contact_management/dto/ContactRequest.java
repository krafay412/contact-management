package com.rafay.contact_management.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ContactRequest {
    private String firstName;
    private String lastName;
    @Email(message = "Wrong Format")
    private String email;
    private String title;
    private String phoneNumber;

}
