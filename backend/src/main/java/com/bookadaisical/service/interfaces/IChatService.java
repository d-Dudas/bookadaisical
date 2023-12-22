package com.bookadaisical.service.interfaces;

import org.springframework.data.domain.Page;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.exceptions.UserNotFoundException;

public interface IChatService {
    public Page<MessageDto> getChatHistoryOfTwoUsers(String senderUsername, String receiverUsername, int page, int pageSize) throws UserNotFoundException;
}
