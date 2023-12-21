package com.bookadaisical.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.model.Chat;
import com.bookadaisical.repository.ChatRepository;
import com.bookadaisical.repository.UserRepository;
import com.bookadaisical.service.interfaces.IChatService;

@Service
public class ChatService implements IChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository,
                       UserRepository userRepository){
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<MessageDto> getChatHistoryOfTwoUsers(String senderUsername, String receiverUsername, int page, int pageSize) throws UserNotFoundException{
        System.out.println("In chat service");
        validateUserExists(senderUsername);
        validateUserExists(receiverUsername);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Chat> chatPage = chatRepository.findChatsBetweenUsers(senderUsername, receiverUsername, pageable);
        return chatPage.map(this::convertToDto);
    }

    private void validateUserExists(String username) throws UserNotFoundException {
        if(!userRepository.findByUsername(username).isPresent()) {
            throw new UserNotFoundException();
        }
    }

    private MessageDto convertToDto(Chat chat) {
        MessageDto dto = new MessageDto();
        dto.setSenderUsername(chat.getSender().getUsername());
        dto.setReceiverUsername(chat.getReceiver().getUsername());
        dto.setMessage(chat.getMessage());
        dto.setSentAt(chat.getSentAt());
        return dto;
    }
}
