package com.rafay.contact_management.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rafay.contact_management.model.ContactEmail;

@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmail,Long> {
    
}
