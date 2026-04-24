package com.rafay.contact_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafay.contact_management.dto.AuthResponse;
import com.rafay.contact_management.dto.LoginRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterRequest request){
        UserDTO userDTO = userService.createUser(request);
        return ResponseEntity.status(201).body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest request){
        AuthResponse response = userService.loginUser(request);
        return ResponseEntity.ok().body(response);
    }
}
