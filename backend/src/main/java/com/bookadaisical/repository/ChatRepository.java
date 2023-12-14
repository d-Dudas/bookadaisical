package com.bookadaisical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
    
}
