package com.bookadaisical.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
    @Query("SELECT c FROM Chat c WHERE (c.sender.id = :user1Id AND c.receiver.id = :user2Id) OR (c.sender.id = :user2Id AND c.receiver.id = :user1Id) ORDER BY c.sentAt DESC")
    Page<Chat> findChatsBetweenUsers(@Param("user1Id") UUID user1Id, @Param("user2Id") UUID user2Id, Pageable pageable);
}
