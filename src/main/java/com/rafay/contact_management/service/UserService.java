package com.rafay.contact_management.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rafay.contact_management.dto.AuthResponse;
import com.rafay.contact_management.dto.LoginRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.model.User;

public interface UserService extends UserDetailsService{
    UserDTO createUser(RegisterRequest request);
    AuthResponse  loginUser(LoginRequest request);
    //void changePassword(Long id,String newPassword);
    User findByEmail(String Email);

    
}