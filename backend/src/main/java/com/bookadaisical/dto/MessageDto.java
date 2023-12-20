package com.bookadaisical.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class MessageDto {
    UUID senderId;
    UUID receiverId;
    String message;
    LocalDateTime sentAt;
}
