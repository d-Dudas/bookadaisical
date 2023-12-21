package com.bookadaisical.dto.responses;

import com.bookadaisical.dto.responses.interfaces.IUserDto;

import lombok.Data;

@Data
public class UserSlimDto implements IUserDto {
    String username;
}
