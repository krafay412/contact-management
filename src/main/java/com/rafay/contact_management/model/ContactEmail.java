package com.rafay.contact_management.model;

import com.rafay.contact_management.enums.EmailLabel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ContactEmail  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "label")
    private EmailLabel label;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

}
