package com.bookadaisical.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {
    Integer senderId;
    Integer receiverId;
    String message;
    LocalDateTime sentAt;
}
