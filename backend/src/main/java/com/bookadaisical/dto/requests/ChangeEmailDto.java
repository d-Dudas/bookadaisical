package com.bookadaisical.dto.requests;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeEmailDto {
    UUID userId;
    String newEmail;
}
