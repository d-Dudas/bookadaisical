package com.bookadaisical.dto.responses;

import com.bookadaisical.dto.responses.interfaces.IUserDto;
import com.bookadaisical.dto.responses.interfaces.UserSlimDtoProjection;

import lombok.Data;

@Data
public class UserSlimDto implements UserSlimDtoProjection, IUserDto {
    private Long userId;
    private String username;
}
