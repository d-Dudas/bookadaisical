package com.bookadaisical.service;

import com.bookadaisical.service.interfaces.IUserService;
import com.bookadaisical.utils.Hasher;

import java.util.List;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.grammars.hql.HqlParser.DateTimeContext;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
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

    private UserSlimDto createUserSlimDto(User user)
    {
        UserSlimDto userSlimDto = mapper.toUserSlimDto(user);
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        userSlimDto.setToken(Hasher.hash(userSlimDto.getUsername() + formattedTime));
        userSlimDto.setKey(Hasher.hash(formattedTime + userSlimDto.getToken() + userSlimDto.getUsername()));
        System.out.println("Key:" + userSlimDto.getKey());

        return userSlimDto;
    }

    @Override
    public UserSlimDto loginUser(UserLoginDto userLoginDto) throws Exception {
        Optional<User> user = userRepository.findByUsernameOrEmailAndPassword(userLoginDto.getIdentifier(), userLoginDto.getPassword());
        if (user.isPresent()) {
            UserSlimDto userSlimDto = this.createUserSlimDto(user.get());
            LoginToken loginToken = new LoginToken(user.get(), userSlimDto.getToken(), userSlimDto.getKey());
            loginTokenRepository.save(loginToken);
            return userSlimDto;
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
            // UserSlimDto userSlimDto = this.createUserSlimDto(loginToken.get().getUser());
            UserSlimDto userSlimDto = mapper.toUserSlimDto(loginToken.get().getUser());
            // userSlimDto.setToken(loginToken.get().getToken());
            // userSlimDto.setKey(loginToken.get().getKey());
            return userSlimDto;
        }
        throw new Exception("invalid_token");
    }
}
