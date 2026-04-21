package com.rafay.contact_management.service;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.model.User;
import com.rafay.contact_management.repository.UserRepository;
import com.rafay.contact_management.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MappingUtil mappingUtil;

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
}
