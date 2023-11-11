package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.model.User;

public interface IUserService {
    
    List<User> getAllUsers();
    User registerUser(UserRegisterDto userRegisterDto) throws Exception;
    UserSlimDto loginUser(UserLoginDto userLoginDto) throws Exception;
}
