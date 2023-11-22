package com.bookadaisical.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeUsernameDto {
    int userId;
    String newUsername;
}
