package com.rafay.contact_management.dto;

import com.rafay.contact_management.enums.EmailLabel;

import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private EmailLabel label;
}
