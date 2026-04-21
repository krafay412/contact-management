package com.rafay.contact_management.dto;


import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private String username;
}
