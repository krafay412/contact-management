package com.rafay.contact_management.dto;

import java.util.List;

import lombok.Data;

@Data
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String title;
    private List<ContactPhoneDTO> phones;
    private List<ContactEmailDTO> emails;
}
