package com.bookadaisical.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookadaisical.dto.UserRegisterDto;
import com.bookadaisical.model.User;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserRegisterDto toUserRegisterDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentPoints", ignore = true)
    @Mapping(target = "totalPoints", ignore = true)
    @Mapping(target = "specialCurrency", ignore = true)
    User toUser(UserRegisterDto userRegisterDto);
    
}
