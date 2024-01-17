package com.bookadaisical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.dto.requests.UsernameDto;
import com.bookadaisical.dto.responses.UserChatsDto;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.service.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @GetMapping("/history")
    public Page<MessageDto> getChatHistory(@RequestParam String senderUsername, @RequestParam String receiverUsername,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return chatService.getChatHistoryOfTwoUsers(senderUsername, receiverUsername, page, size);
    }

    @PostMapping("/chats")
    public ResponseEntity<?> getUserChats(@RequestBody UsernameDto usernameDto)
    {
        try {
            UserChatsDto userChatsDto = chatService.getUserChats(usernameDto);
            return ResponseEntity.ok(userChatsDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
