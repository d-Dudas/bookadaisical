package com.bookadaisical.service;

import com.bookadaisical.service.interfaces.IUserService;
import com.bookadaisical.utils.Hasher;

import java.util.List;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.UserTokenDto;
import com.bookadaisical.dto.responses.interfaces.IUserDto;
import com.bookadaisical.mapper.UserMapper;
import com.bookadaisical.model.LoginToken;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.LoginTokenRepository;
import com.bookadaisical.repository.UserRepository;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final LoginTokenRepository loginTokenRepository;

    public UserService(UserRepository userRepository,
                       UserMapper mapper,
                       LoginTokenRepository loginTokenRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.loginTokenRepository = loginTokenRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(UserRegisterDto userRegisterDto) throws Exception {
        User user = mapper.toUser(userRegisterDto);
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new Exception(String.format("User with username \"%s\" is already registered", user.getUsername()));
        }
        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception(String.format("User with email \"%s\" is already registered", user.getEmail()));
        }
        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername()).get();
    }

    private void saveLoginToken(User user, UserTokenDto userTokenDto)
    {
        LoginToken loginToken = new LoginToken(user, userTokenDto.getToken(), userTokenDto.getKey());
        loginTokenRepository.save(loginToken);
    }

    private UserTokenDto createUserTokenDto(User user)
    {
        UserTokenDto userTokenDto = mapper.toUserTokenDto(user);
        String formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        userTokenDto.setToken(Hasher.hash(userTokenDto.getUsername() + formattedTime));
        userTokenDto.setKey(Hasher.hash(formattedTime + userTokenDto.getToken()));

        saveLoginToken(user, userTokenDto);

        return userTokenDto;
    }

    @Override
    public IUserDto loginUser(UserLoginDto userLoginDto) throws Exception {
        Optional<User> user = userRepository.findByUsernameOrEmailAndPassword(userLoginDto.getIdentifier(), userLoginDto.getPassword());
        if (user.isPresent()) {
            return userLoginDto.isRememberMe() ?
                    createUserTokenDto(user.get()) :
                    mapper.toUserSlimDto(user.get());
        }

        Optional<User> checkIdentifier = userRepository.findByUsernameOrEmail(userLoginDto.getIdentifier(), userLoginDto.getIdentifier());
        if (checkIdentifier.isPresent() && !checkIdentifier.get().getPassword().equals(userLoginDto.getPassword())) {
            throw new Exception("Invalid password");
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public UserSlimDto loginUserWithToken(String token, String key) throws Exception
    {
        Optional<LoginToken> loginToken = loginTokenRepository.findByTokenAndKey(token, key);

        if(loginToken.isPresent())
        {
            // TODO: revalidate token
            UserSlimDto userSlimDto = mapper.toUserSlimDto(loginToken.get().getUser());
            return userSlimDto;
        }
        throw new Exception("invalid_token");
    }

    @Override
    public User getUserDetails(int userId) throws Exception
    {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
        {
            return user.get();
        }
        throw new Exception("user_not_found");
    }

    @Transactional
    @Override
    public User changeUsername(int userId, String newUsername) throws Exception
    {
        Optional<User> user;
        System.out.println("Id: " + userId + " | newUsername: " + newUsername);

        user = userRepository.findByUsername(newUsername);
        if(user.isPresent())
        {
            throw new Exception("username_already_exists");
        }

        user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.updateUsername(userId, newUsername);
            user.get().setUsername(newUsername);
            return user.get();
        }
        throw new Exception("user_not_found");
    }
}
