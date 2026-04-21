package com.rafay.contact_management.dto;


import lombok.Data;

@Data
public class UserDTO {

    private String email;
    private String cnic;
    private String username;

    private String firstName;
    private String lastName;
}
