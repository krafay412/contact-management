package com.rafay.contact_management.service;

import java.util.List;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    ContactDTO createContact(Long userId,ContactRequest request);
    ContactDTO updateContact(Long userId,Long id, ContactRequest newContact);
    void deleteContact(Long userId,Long id);
    ContactDTO getContact(Long userId,Long id);
    Page<ContactDTO> getAllContacts(Long userId, Pageable pageable);
    Page<ContactDTO>searchContacts(Long userId,String query,Pageable pageable);
}
