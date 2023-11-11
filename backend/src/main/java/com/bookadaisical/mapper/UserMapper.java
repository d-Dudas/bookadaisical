package com.bookadaisical.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookadaisical.dto.UserLoginDto;
import com.bookadaisical.dto.UserRegisterDto;
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
    
}
