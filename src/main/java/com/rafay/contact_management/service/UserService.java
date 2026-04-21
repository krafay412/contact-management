package com.rafay.contact_management.service;

import com.rafay.contact_management.dto.AuthResponse;
import com.rafay.contact_management.dto.LoginRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;

public interface UserService {
    UserDTO createUser(RegisterRequest request);
    AuthResponse  loginUser(LoginRequest request);
    void changePassword(Long id,String newPassword);

    
}