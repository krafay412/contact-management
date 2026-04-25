package com.rafay.contact_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;
import com.rafay.contact_management.model.User;
import com.rafay.contact_management.service.ContactService;
import com.rafay.contact_management.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ContactDTO> createContact(Authentication auth,@RequestBody ContactRequest request){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();    
        ContactDTO dto = contactService.createContact(userId, request);
        return ResponseEntity.status(201).body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(Authentication auth,@RequestBody ContactRequest request,@PathVariable Long id){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();
        ContactDTO contact = contactService.updateContact(userId, id, request);
        return ResponseEntity.ok().body(contact);

    }
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteContact(Authentication auth,@PathVariable Long id){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();
        contactService.deleteContact(userId, id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContact(Authentication auth,@PathVariable Long id){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();
        ContactDTO contact = contactService.getContact(userId, id);
        return ResponseEntity.ok().body(contact);
    }
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts(Authentication auth){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Long userId = user.getId();
        List<ContactDTO> contacts = contactService.getAllContacts(userId);
        return ResponseEntity.ok().body(contacts);
    }



}
