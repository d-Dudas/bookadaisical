package com.bookadaisical.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.interfaces.IUserDto;
import com.bookadaisical.dto.responses.interfaces.UserSlimDtoProjection;
import com.bookadaisical.model.User;

public interface IUserService {
    List<User> getAllUsers();
    IUserDto registerUser(UserRegisterDto userRegisterDto) throws Exception;
    IUserDto loginUser(UserLoginDto userLoginDto) throws Exception;
    UserSlimDto loginUserWithToken(String token, String key) throws Exception;
    UserDto getUserDetails(UUID userId) throws Exception;
    UserSlimDto changeUsername(UUID userId, String newUsername) throws Exception;
    UserSlimDto changeEmail(UUID userId, String newEmail) throws Exception;
    UserSlimDto changePassword(UUID userId, String newPassword) throws Exception;
}
