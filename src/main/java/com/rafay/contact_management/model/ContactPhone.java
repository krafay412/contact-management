package com.rafay.contact_management.model;

import com.rafay.contact_management.enums.PhoneLabel;

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
public class ContactPhone{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "label")
    private PhoneLabel label;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

}