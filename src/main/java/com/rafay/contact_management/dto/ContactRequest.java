package com.rafay.contact_management.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ContactRequest {
    private String firstName;
    private String lastName;
    @Email(message = "Wrong Format")
    private List<EmailRequest> emails;
    private String title;
    private List<PhoneRequest> phones;

}
