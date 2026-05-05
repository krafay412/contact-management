package com.rafay.contact_management.dto;

import com.rafay.contact_management.enums.PhoneLabel;

import lombok.Data;

@Data
public class ContactPhoneDTO {
    private String phone;
    private PhoneLabel label;
}
