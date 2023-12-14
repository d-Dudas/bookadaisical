package com.bookadaisical.dto.responses;

import java.util.UUID;

import com.bookadaisical.dto.responses.interfaces.IUserDto;

import lombok.Data;

@Data
public class UserSlimDto implements IUserDto {
    UUID id;
    String username;
}
