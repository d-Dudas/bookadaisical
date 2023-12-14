package com.bookadaisical.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MessageDto> getChatHistory(@RequestParam int user1Id, @RequestParam int user2Id) {
        List<Chat> chats = chatRepository.findChatsBetweenUsers(user1Id, user2Id);
        return chats.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
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
