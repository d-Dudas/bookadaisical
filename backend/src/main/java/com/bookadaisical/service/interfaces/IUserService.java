package com.bookadaisical.service.interfaces;

import java.util.List;

import org.hibernate.internal.ExceptionConverterImpl;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.model.User;

public interface IUserService {
    List<User> getAllUsers();
    User registerUser(UserRegisterDto userRegisterDto) throws Exception;
    UserSlimDto loginUser(UserLoginDto userLoginDto) throws Exception;
    UserSlimDto loginUserWithToken(String token, String key) throws Exception;
    User getUserDetails(int userId) throws Exception;
    User changeUsername(int userId, String newUsername) throws Exception;
}
