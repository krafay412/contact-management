package com.rafay.contact_management.service;

import java.util.List;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;

public interface ContactService {
    ContactDTO createContact(Long userId,ContactRequest request);
    ContactDTO updateContact(Long userId,Long id, ContactRequest newContact);
    void deleteContact(Long userId,Long id);
    ContactDTO getContact(Long userId,Long id);
    List<ContactDTO> getAllContacts(Long userId);
}
