package com.bookadaisical.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeEmailDto {
    Integer userId;
    String newEmail;
}
