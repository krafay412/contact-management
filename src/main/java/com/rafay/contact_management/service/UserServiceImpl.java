package com.rafay.contact_management.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafay.contact_management.dto.AuthResponse;
import com.rafay.contact_management.dto.LoginRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.model.User;
import com.rafay.contact_management.repository.UserRepository;
import com.rafay.contact_management.security.JwtUtil;
import com.rafay.contact_management.util.MappingUtil;

import lombok.Builder;


@Service
@Builder
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MappingUtil mappingUtil;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,MappingUtil mappingUtil, JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.mappingUtil = mappingUtil;
    }

    @Override
    public UserDTO createUser(RegisterRequest request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email Already Exist");
        }
        User user = mappingUtil.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return mappingUtil.toUserDTO(savedUser);

    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities(new ArrayList<>()).build();
    }

    @Override
    public AuthResponse loginUser(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(user.getUsername());
        AuthResponse response = AuthResponse.builder().accessToken(token).tokenType("Bearer").username(user.getUsername()).build();
        return response;
    }
}
