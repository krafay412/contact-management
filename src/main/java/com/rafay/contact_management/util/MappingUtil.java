package com.rafay.contact_management.util;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactEmailDTO;
import com.rafay.contact_management.dto.ContactPhoneDTO;
import com.rafay.contact_management.dto.ContactRequest;
import com.rafay.contact_management.dto.EmailRequest;
import com.rafay.contact_management.dto.PhoneRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.model.Contact;
import com.rafay.contact_management.model.ContactEmail;
import com.rafay.contact_management.model.ContactPhone;
import com.rafay.contact_management.model.User;

@Component
public class MappingUtil {
    public UserDTO toUserDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setCnic(user.getCnic());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        return dto;
    }
    public User toUser(RegisterRequest request){
        User user = new User();
        user.setCnic(request.getCnic());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        return user;
    }
    public ContactDTO toContactDTO(Contact contact){
        ContactDTO dto = new ContactDTO();
        dto.setEmails(contact.getContactEmail().stream().map(this::toContactEmailDTO).collect(Collectors.toList()));
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setPhones(contact.getContactPhone().stream().map(this::toContactPhoneDTO).collect(Collectors.toList()));
        dto.setTitle(contact.getTitle());
        return dto;
    }
    public Contact toContact(ContactRequest request){
        Contact cont = new Contact();
        cont.setFirstName(request.getFirstName());
        cont.setLastName(request.getLastName());
        cont.setTitle(request.getTitle());
        return cont;
    }
    public ContactEmailDTO toContactEmailDTO(ContactEmail contactEmail){
        ContactEmailDTO dto = new ContactEmailDTO();
        dto.setEmail(contactEmail.getEmail());
        dto.setLabel(contactEmail.getLabel());
        return dto;
    }
    public ContactEmail toContactEmail(EmailRequest request){
        ContactEmail contact = new ContactEmail();
        contact.setEmail(request.getEmail());
        contact.setLabel(request.getLabel());
        return contact;
    }

    public ContactPhoneDTO toContactPhoneDTO(ContactPhone contactPhone){
        ContactPhoneDTO dto = new ContactPhoneDTO();
        dto.setLabel(contactPhone.getLabel());
        dto.setPhone(contactPhone.getPhone());
        return dto;
    }
    public ContactPhone toContactPhone(PhoneRequest request){
        ContactPhone contactPhone = new ContactPhone();
        contactPhone.setPhone(request.getPhone());
        contactPhone.setLabel(request.getLabel());
        return contactPhone;

    }
}
