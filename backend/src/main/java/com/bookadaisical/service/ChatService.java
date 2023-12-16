package com.bookadaisical.service;

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

    public Page<MessageDto> getChatHistoryOfTwoUsers(int user1Id, int user2Id, int page, int pageSize) throws UserNotFoundException{
        validateUserExists(user1Id);
        validateUserExists(user2Id);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Chat> chatPage = chatRepository.findChatsBetweenUsers(user1Id, user2Id, pageable);
        return chatPage.map(this::convertToDto);
    }

    private void validateUserExists(int userId) throws UserNotFoundException {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
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
