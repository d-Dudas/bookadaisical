package com.bookadaisical.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.exceptions.UserNotFoundException;

public interface IChatService {
    public Page<MessageDto> getChatHistoryOfTwoUsers(UUID user1Id, UUID user2Id, int page, int pageSize) throws UserNotFoundException;
}
