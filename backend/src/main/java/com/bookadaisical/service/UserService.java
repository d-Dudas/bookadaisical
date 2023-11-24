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
import com.bookadaisical.exceptions.EmailAlreadyAssociatedWithAnAccountException;
import com.bookadaisical.exceptions.InvalidPasswordException;
import com.bookadaisical.exceptions.InvalidTokenException;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.exceptions.UsernameAlreadyAssociatedWithAnAccountException;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public IUserDto registerUser(UserRegisterDto userRegisterDto) throws Exception {

        User user = mapper.toUser(userRegisterDto);
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyAssociatedWithAnAccountException();
        }

        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyAssociatedWithAnAccountException();
        }

        userRepository.save(user);

        existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser.isPresent())
        {

            return userRegisterDto.isRememberMe() ?
                    createUserTokenDto(existingUser.get()) :
                    mapper.toUserSlimDto(existingUser.get());
        }
        throw new UnknownError();
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
            throw new InvalidPasswordException();
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    @Override
    public UserSlimDto loginUserWithToken(String token, String key) throws Exception
    {
        Optional<LoginToken> loginToken = loginTokenRepository.findByTokenAndKey(token, key);

        if(loginToken.isPresent())
        {
            loginTokenRepository.revalidateToken(token);
            return mapper.toUserSlimDto(loginToken.get().getUser());
        }
        throw new InvalidTokenException();
    }

    @Override
    public User getUserDetails(int userId) throws Exception
    {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent())
        {
            return user.get();
        }
        throw new UserNotFoundException();
    }

    @Transactional
    @Override
    public UserSlimDto changeUsername(int userId, String newUsername) throws Exception
    {
        Optional<User> user;

        user = userRepository.findByUsername(newUsername);
        if(user.isPresent())
        {
            throw new UsernameAlreadyAssociatedWithAnAccountException();
        }

        user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.updateUsername(userId, newUsername);
            user.get().setUsername(newUsername);
            return mapper.toUserSlimDto(user.get());
        }
        throw new UserNotFoundException();
    }

    @Transactional
    @Override
    public UserSlimDto changeEmail(int userId, String newEmail) throws Exception
    {
        Optional<User> user;

        user = userRepository.findByEmail(newEmail);
        if(user.isPresent())
        {
            throw new EmailAlreadyAssociatedWithAnAccountException();
        }

        user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.updateEmail(userId, newEmail);
            user.get().setEmail(newEmail);
            return mapper.toUserSlimDto(user.get());
        }
        throw new UserNotFoundException();
    }

    @Transactional
    @Override
    public UserSlimDto changePassword(int userId, String newPassword) throws Exception
    {
        Optional<User> user;

        user = userRepository.findById(userId);
        if(user.isPresent())
        {
            userRepository.updatePassword(userId, newPassword);
            return mapper.toUserSlimDto(user.get());
        }
        throw new UserNotFoundException();
    }
}
