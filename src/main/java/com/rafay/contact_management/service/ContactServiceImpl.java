package com.rafay.contact_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rafay.contact_management.dto.EmailRequest;
import com.rafay.contact_management.dto.PhoneRequest;
import com.rafay.contact_management.exception.GlobalExceptionHandler;
import com.rafay.contact_management.exception.ResourceNotFoundException;
import com.rafay.contact_management.exception.UnauthorizedAccessException;
import com.rafay.contact_management.model.ContactEmail;
import com.rafay.contact_management.model.ContactPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;
import com.rafay.contact_management.model.Contact;
import com.rafay.contact_management.model.User;
import com.rafay.contact_management.repository.ContactRepository;
import com.rafay.contact_management.repository.UserRepository;
import com.rafay.contact_management.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final MappingUtil mappingUtil;

    @Override
    public ContactDTO createContact(Long userId,ContactRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + userId));
        log.info("Creating new contact for {}",user.getEmail());
        Contact contact = mappingUtil.toContact(request);
        List<ContactEmail> emails = new ArrayList<>();
        for (EmailRequest email : request.getEmails()){
            ContactEmail contactEmail = mappingUtil.toContactEmail(email);
            contactEmail.setContact(contact);
            emails.add(contactEmail);
        }
        contact.setContactEmail(emails);

        List<ContactPhone> phones = new ArrayList<>();
        for (PhoneRequest phone : request.getPhones()){
            ContactPhone contactPhone = mappingUtil.toContactPhone(phone);
            contactPhone.setContact(contact);
            phones.add(contactPhone);
        }
        contact.setContactPhone(phones);



        contact.setUser(user);
        Contact savedContact = contactRepository.save(contact);
        log.info("Contact created for user {}",savedContact.getUser().getEmail());
        return mappingUtil.toContactDTO(savedContact);

    }
    @Override 
    public ContactDTO updateContact(Long userId,Long id,ContactRequest request){
        log.info("Updating Contact for {}",userId);
        Contact contact = contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found: "+ id));
        if (!contact.getUser().getId().equals(userId)){
            throw new UnauthorizedAccessException("Invalid Request");
        }
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setTitle(request.getTitle());
        contact.getContactEmail().clear();
        List<ContactEmail> emails = new ArrayList<>();
        for (EmailRequest email : request.getEmails()){
            ContactEmail contactEmail = mappingUtil.toContactEmail(email);
            contactEmail.setContact(contact);
            emails.add(contactEmail);
        }
        contact.getContactEmail().addAll(emails);

        contact.getContactPhone().clear();
        List<ContactPhone> phones = new ArrayList<>();
        for (PhoneRequest phone : request.getPhones()){
            ContactPhone contactPhone = mappingUtil.toContactPhone(phone);
            contactPhone.setContact(contact);
            phones.add(contactPhone);
        }

        contact.getContactPhone().addAll(phones);

        Contact savedContact = contactRepository.save(contact);
        log.info("Contact Updated for {}",savedContact.getUser().getEmail());
        return mappingUtil.toContactDTO(savedContact);

    }

    @Override
    public void deleteContact(Long userId,Long id){
        log.info("Deleting Contact of {}",userId);
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Not Found: " + id));
        if (!contact.getUser().getId().equals(userId)){
            throw new UnauthorizedAccessException("Invalid Request");

        }
        log.info("Contact Deleted of {}",userId);
        contactRepository.delete(contact);
    }

    @Override
    public ContactDTO getContact(Long userId,Long id){
        log.info("Getting Contact of {}",userId);
        Contact contact = contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found: " + id));
        if (!contact.getUser().getId().equals(userId)){
            throw new UnauthorizedAccessException("Invalid Request");
        }
        log.info("Retreived Contact for {}",userId);
        return mappingUtil.toContactDTO(contact);

    }
    @Override
    public Page<ContactDTO> getAllContacts(Long userId, Pageable pageable){
        log.info("Getting Contact of {}",userId);
        Page<Contact> contacts = contactRepository.findByUserId(userId,pageable);
        log.info("Retreived Contact for {}",userId);
        return contacts.map(mappingUtil::toContactDTO);
    }
    @Override
    public Page<ContactDTO>searchContacts(Long userId, String query, Pageable pageable){
        log.info("Searching Contacts: {}",userId);
        Page<Contact> contacts = contactRepository.searchContact(userId,query,pageable);
        log.info("Retreived Contacts of {}",userId);
        return contacts.map(mappingUtil::toContactDTO);
    }
}
