package com.rafay.contact_management.model;

import java.util.ArrayList;
import java.util.List; 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;
    
    @Column (name = "last_name")
    private String lastName;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "cnic",unique = true)
    private String cnic;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
