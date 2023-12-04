package com.bookadaisical.dto.responses;

import com.bookadaisical.dto.responses.interfaces.IUserDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTokenDto implements IUserDto {
    private int id;
    private String username;
    private String token;
    private String key;
}
