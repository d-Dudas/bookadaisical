package com.bookadaisical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookadaisical.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
    @Query("SELECT c FROM Chat c WHERE (c.sender.id = :user1Id AND c.receiver.id = :user2Id) OR (c.sender.id = :user2Id AND c.receiver.id = :user1Id) ORDER BY c.sentAt")
    List<Chat> findChatsBetweenUsers(@Param("user1Id") int user1Id, @Param("user2Id") int user2Id);
}
