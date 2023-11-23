package com.bookadaisical.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.UserTokenDto;
import com.bookadaisical.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegisterDto toUserRegisterDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentPoints", ignore = true)
    @Mapping(target = "totalPoints", ignore = true)
    @Mapping(target = "specialCurrency", ignore = true)
    User toUser(UserRegisterDto userRegisterDto);

    UserLoginDto toUserLoginDto(User user);
    User toUser (UserLoginDto userLoginDto);

    UserSlimDto toUserSlimDto(User user);
    User toUser(UserSlimDto userSlimDto);

    @Mapping(target = "token", ignore = true)
    @Mapping(target = "key", ignore = true)
    UserTokenDto toUserTokenDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "currentPoints", ignore = true)
    @Mapping(target = "totalPoints", ignore = true)
    @Mapping(target = "specialCurrency", ignore = true)
    User toUser(UserTokenDto userTokenDto);

}
