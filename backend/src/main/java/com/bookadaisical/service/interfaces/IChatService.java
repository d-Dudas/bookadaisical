package com.bookadaisical.service.interfaces;

import org.springframework.data.domain.Page;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.dto.requests.UsernameDto;
import com.bookadaisical.dto.responses.UserChatsDto;
import com.bookadaisical.exceptions.UserNotFoundException;

public interface IChatService {
    public Page<MessageDto> getChatHistoryOfTwoUsers(String senderUsername, String receiverUsername, int page, int pageSize) throws UserNotFoundException;
    public UserChatsDto getUserChats(UsernameDto usernameDto) throws UserNotFoundException;
}
