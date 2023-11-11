package com.bookadaisical.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
}
