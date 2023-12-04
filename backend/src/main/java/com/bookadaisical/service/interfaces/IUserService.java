package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.interfaces.IUserDto;
import com.bookadaisical.model.User;

public interface IUserService {
    List<User> getAllUsers();
    IUserDto registerUser(UserRegisterDto userRegisterDto) throws Exception;
    IUserDto loginUser(UserLoginDto userLoginDto) throws Exception;
    IUserDto loginUserWithToken(String token, String key) throws Exception;
    IUserDto getUserDetails(int userId) throws Exception;
    UserSlimDto changeUsername(int userId, String newUsername) throws Exception;
    UserSlimDto changeEmail(int userId, String newEmail) throws Exception;
    UserSlimDto changePassword(int userId, String newPassword) throws Exception;
}
