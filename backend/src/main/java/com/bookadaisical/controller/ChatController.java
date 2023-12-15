package com.bookadaisical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.model.Chat;
import com.bookadaisical.repository.ChatRepository;

@RestController
public class ChatController {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping("/chat/history")
    public Page<MessageDto> getChatHistory(@RequestParam int user1Id, @RequestParam int user2Id,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Chat> chatPage = chatRepository.findChatsBetweenUsers(user1Id, user2Id, pageable);
        return chatPage.map(this::convertToDto);
    }

    private MessageDto convertToDto(Chat chat) {
        MessageDto dto = new MessageDto();
        dto.setSenderId(chat.getSender().getId());
        dto.setReceiverId(chat.getReceiver().getId());
        dto.setMessage(chat.getMessage());
        dto.setSentAt(chat.getSentAt());
        return dto;
    }
}
