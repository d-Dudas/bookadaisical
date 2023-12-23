package com.bookadaisical.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
    @Query("SELECT c FROM Chat c WHERE (c.sender.username = :senderUsername AND c.receiver.username = :receiverUsername) OR (c.sender.username = :receiverUsername AND c.receiver.username = :senderUsername) ORDER BY c.sentAt DESC")
    Page<Chat> findChatsBetweenUsers(@Param("senderUsername") String senderUsername, @Param("receiverUsername") String receiverUsername, Pageable pageable);
}
