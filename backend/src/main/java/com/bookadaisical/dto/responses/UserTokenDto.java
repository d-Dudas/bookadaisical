package com.bookadaisical.dto.responses;

import java.util.UUID;

import com.bookadaisical.dto.responses.interfaces.IUserDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTokenDto implements IUserDto {
    private UUID id;
    private String username;
    private String token;
    private String key;
}
