package com.bookadaisical.dto.responses;

import com.bookadaisical.dto.responses.interfaces.IUserDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto implements IUserDto {
    private int id;
    private String username;
    private String email;
    private int currentPoints;
    private int totalPoints;
    private int specialCurrency;
}
