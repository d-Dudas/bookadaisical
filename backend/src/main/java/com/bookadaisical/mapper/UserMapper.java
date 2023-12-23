package com.bookadaisical.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.UserTokenDto;
import com.bookadaisical.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentPoints", ignore = true)
    @Mapping(target = "totalPoints", ignore = true)
    @Mapping(target = "specialCurrency", ignore = true)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "uploadedBooks", ignore = true)
    User toUser(UserRegisterDto userRegisterDto);

    UserSlimDto toUserSlimDto(User user);

    @Mapping(target = "token", ignore = true)
    @Mapping(target = "key", ignore = true)
    UserTokenDto toUserTokenDto(User user);

    UserDto toUserDto(User user);
}
