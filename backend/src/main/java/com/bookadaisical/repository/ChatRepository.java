package com.bookadaisical.repository;

import java.util.List;
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
    @Query("SELECT c FROM Chat c WHERE (c.sender.username = :senderUsername AND c.receiver.username = :receiverUsername) OR (c.sender.username = :receiverUsername AND c.receiver.username = :senderUsername) ORDER BY c.sentAt DESC")
    Page<Chat> findChatsBetweenUsers(@Param("senderUsername") String senderUsername, @Param("receiverUsername") String receiverUsername, Pageable pageable);

    // @Query("SELECT c FROM Chat c WHERE c.sentAt IN (SELECT MAX(c2.sentAt) FROM Chat c2 WHERE (c2.sender.id = :userId OR c2.receiver.id = :userId) AND (c2.sender.id = c.sender.id AND c2.receiver.id = c.receiver.id OR c2.sender.id = c.receiver.id AND c2.receiver.id = c.sender.id) GROUP BY FUNCTION('COALESCE', c2.sender, c2.receiver), FUNCTION('COALESCE', c2.receiver, c2.sender)) ORDER BY c.sentAt DESC")
    @Query("SELECT c FROM Chat c WHERE (c.sender.id = :userId OR c.receiver.id = :userId) AND c.id IN (SELECT MAX(c2.id) FROM Chat c2 GROUP BY c2.sender, c2.receiver HAVING (c2.sender = c.sender AND c2.receiver = c.receiver) OR (c2.sender = c.receiver AND c2.receiver = c.sender)) ORDER BY c.sentAt DESC")
    // @Query("SELECT c FROM Chat c WHERE c.id IN (SELECT c2.id FROM Chat c2 WHERE (c2.sender.id = :userId OR c2.receiver.id = :userId) GROUP BY CASE WHEN c2.sender.id < c2.receiver.id THEN c2.sender.id ELSE c2.receiver.id END, CASE WHEN c2.sender.id < c2.receiver.id THEN c2.receiver.id ELSE c2.sender.id END HAVING c2.sentAt = MAX(c2.sentAt)) ORDER BY c.sentAt DESC")
    List<Chat> findUserChat(@Param("userId") UUID userId);
}
