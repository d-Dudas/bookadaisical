package com.bookadaisical.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {
    String senderUsername;
    String receiverUsername;
    String message;
    LocalDateTime sentAt;
}
