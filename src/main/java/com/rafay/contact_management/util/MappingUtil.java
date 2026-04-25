package com.rafay.contact_management.util;

import org.springframework.stereotype.Component;

import com.rafay.contact_management.dto.ContactDTO;
import com.rafay.contact_management.dto.ContactRequest;
import com.rafay.contact_management.dto.RegisterRequest;
import com.rafay.contact_management.dto.UserDTO;
import com.rafay.contact_management.model.Contact;
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
        dto.setEmail(contact.getEmail());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setPhoneNumber(contact.getPhoneNumber());
        dto.setTitle(contact.getTitle());
        return dto;
    }
    public Contact toContact(ContactRequest request){
        Contact cont = new Contact();
        cont.setEmail(request.getEmail());
        cont.setFirstName(request.getFirstName());
        cont.setLastName(request.getLastName());
        cont.setPhoneNumber(request.getPhoneNumber());
        cont.setTitle(request.getTitle());
        return cont;
    }
}
