package com.rafay.contact_management.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<ContactDTO>> getAllContacts(Authentication auth, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok().body(contactService.getAllContacts(user.getId(),pageable));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<ContactDTO>> searchContacts(Authentication auth, @RequestParam String query,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        String email = auth.getName();
        User user = userService.findByEmail(email);
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok().body(contactService.searchContacts(user.getId(),query,pageable));
    }



}
