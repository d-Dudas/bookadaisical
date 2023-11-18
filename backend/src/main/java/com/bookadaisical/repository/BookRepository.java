package com.bookadaisical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.User;

@Repository
public interface BookRepository extends JpaRepository<User, String> {
    
    

}
