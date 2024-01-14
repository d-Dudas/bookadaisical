package com.bookadaisical.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.dto.requests.UsernameDto;
import com.bookadaisical.dto.responses.UserChatsDto;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.model.Chat;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.ChatRepository;
import com.bookadaisical.repository.UserRepository;
import com.bookadaisical.service.interfaces.IChatService;

import jakarta.annotation.Nonnull;

@Service
public class ChatService implements IChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<MessageDto> getChatHistoryOfTwoUsers(String senderUsername, String receiverUsername, int page, int pageSize) throws UserNotFoundException{
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

    @Override
    public UserChatsDto getUserChats(UsernameDto usernameDto) throws UserNotFoundException
    {
        Optional<User> user = userRepository.findByUsername(usernameDto.getUsername());
        if(user.isEmpty()) throw new UserNotFoundException();

        List<Chat> chats = chatRepository.findUserChat(user.get().getId());

        UserChatsDto userChatsDto = new UserChatsDto(user.get().getUsername(), new ArrayList<>());
        Set<Pair<String, String>> seenPairs = new HashSet<>();

        for (Chat chat : chats) {
            String senderUsername = chat.getSender().getUsername();
            String receiverUsername = (String) chat.getReceiver().getUsername();
            Pair<String, String> pair = senderUsername.compareTo(receiverUsername) < 0 ? Pair.of(senderUsername, receiverUsername) : Pair.of(receiverUsername, senderUsername);
        
            if (!seenPairs.contains(pair)) {
                seenPairs.add(pair);
                userChatsDto.getLastMessages().add(convertToDto(chat));
            }
        }

        return userChatsDto;
    }
}
