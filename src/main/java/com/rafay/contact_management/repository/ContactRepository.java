package com.rafay.contact_management.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rafay.contact_management.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByUserId(Long id);
    Page<Contact> findByUserId(Long id, Pageable pageable);
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId AND " + "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR " + "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Contact>searchContact(@Param("userId")Long userId,@Param("query")String query,Pageable pageable);
    
} 
