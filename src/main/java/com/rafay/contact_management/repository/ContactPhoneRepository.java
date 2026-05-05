package com.rafay.contact_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafay.contact_management.model.ContactPhone;

@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhone,Long> {
    
}
