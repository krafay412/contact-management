package com.rafay.contact_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.rafay.contact_management.dto.EmailRequest;
import com.rafay.contact_management.dto.PhoneRequest;
import com.rafay.contact_management.exception.GlobalExceptionHandler;
import com.rafay.contact_management.exception.ResourceNotFoundException;
import com.rafay.contact_management.exception.UnauthorizedAccessException;
import com.rafay.contact_management.model.ContactEmail;
import com.rafay.contact_management.model.ContactPhone;
import org.springframework.stereotype.Service;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;
import com.rafay.contact_management.model.Contact;
import com.rafay.contact_management.model.User;
import com.rafay.contact_management.repository.ContactRepository;
import com.rafay.contact_management.repository.UserRepository;
import com.rafay.contact_management.util.MappingUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final MappingUtil mappingUtil;

    @Override
    public ContactDTO createContact(Long userId,ContactRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + userId));
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
        return mappingUtil.toContactDTO(savedContact);

    }
    @Override 
    public ContactDTO updateContact(Long userId,Long id,ContactRequest request){
        Contact contact = contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found: "+ id));
        if (!contact.getUser().getId().equals(userId)){
            throw new RuntimeException("Invalid Request");
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
        return mappingUtil.toContactDTO(savedContact);

    }

    @Override
    public void deleteContact(Long userId,Long id){
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact Not Found: " + id));
        if (!contact.getUser().getId().equals(userId)){
            throw new UnauthorizedAccessException("Invalid Request");

        }
        contactRepository.delete(contact);
    }

    @Override
    public ContactDTO getContact(Long userId,Long id){
        Contact contact = contactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found: " + id));
        if (!contact.getUser().getId().equals(userId)){
            throw new UnauthorizedAccessException("Invalid Request");
        }
        return mappingUtil.toContactDTO(contact);

    }
    @Override
    public List<ContactDTO> getAllContacts(Long userId){
        List<Contact> contacts = contactRepository.findByUserId(userId);
        return contacts.stream().map(mappingUtil::toContactDTO).collect(Collectors.toList());
    }
}
