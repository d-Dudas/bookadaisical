package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.UserLoginDto;
import com.bookadaisical.dto.UserRegisterDto;
import com.bookadaisical.model.User;

public interface IUserService {
    
    List<User> getAllUsers();
    User registerUser(UserRegisterDto userRegisterDto) throws Exception;
    User loginUser(UserLoginDto userLoginDto) throws Exception;
}
