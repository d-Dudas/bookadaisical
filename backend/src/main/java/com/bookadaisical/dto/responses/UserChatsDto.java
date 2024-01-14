package com.bookadaisical.dto.responses;

import java.util.List;

import com.bookadaisical.dto.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChatsDto {
    String username;
    // Map<String, String> lastMessages;
    List<MessageDto> lastMessages;
}
