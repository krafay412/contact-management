package com.rafay.contact_management.service;

import java.util.ArrayList;


import com.rafay.contact_management.exception.DuplicateResourceException;
import com.rafay.contact_management.exception.ResourceNotFoundException;
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
            throw new DuplicateResourceException("Email Already Exist" + request.getEmail());
        }
        User user = mappingUtil.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return mappingUtil.toUserDTO(savedUser);

    }
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found: "+ username));
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities(new ArrayList<>()).build();
    }

    @Override
    public AuthResponse loginUser(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getEmail()));
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse response = AuthResponse.builder().accessToken(token).tokenType("Bearer").username(user.getUsername()).build();
        return response;
    }
    @Override
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not Found: " + email));
        return user;
    }
    @Override
    public void changePassword(Long id,String newPassword){
           User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found: " + id));
           user.setPassword(passwordEncoder.encode(newPassword));
           userRepository.save(user);
    }
}
