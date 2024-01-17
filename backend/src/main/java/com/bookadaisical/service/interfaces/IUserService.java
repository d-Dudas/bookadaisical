package com.bookadaisical.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.bookadaisical.dto.requests.ChangeProfilePictureDto;
import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.interfaces.IUserDto;
import com.bookadaisical.model.User;

public interface IUserService {
    public List<User> getAllUsers();
    public IUserDto registerUser(UserRegisterDto userRegisterDto) throws Exception;
    public IUserDto loginUser(UserLoginDto userLoginDto) throws Exception;
    public IUserDto loginUserWithToken(String token, String key) throws Exception;
    public UserDto getUserDetails(String userId) throws Exception;
    public UserSlimDto changeUsername(UUID userId, String newUsername) throws Exception;
    public UserSlimDto changeEmail(UUID userId, String newEmail) throws Exception;
    public UserSlimDto changePassword(UUID userId, String newPassword) throws Exception;
    public void changeProfilePicture(ChangeProfilePictureDto changeProfilePictureDto) throws Exception;
}
