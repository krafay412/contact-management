package com.rafay.contact_management.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Contact {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name  = "last_name")
    private String lastName;

    @Column(name = "title")
    private String title;


    @OneToMany(mappedBy = "contact",cascade= CascadeType.ALL,orphanRemoval = true)
    private List<ContactEmail> contactEmail = new ArrayList<>();

    @OneToMany(mappedBy = "contact",cascade= CascadeType.ALL,orphanRemoval = true)
    private List<ContactPhone> contactPhone = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
