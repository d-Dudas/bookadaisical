package com.bookadaisical.service;

import com.bookadaisical.service.interfaces.IUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.mapper.UserMapper;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.UserRepository;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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

    @Override
    public UserSlimDto loginUser(UserLoginDto userLoginDto) throws Exception {
        System.out.println("Expected line no.2");
        Optional<User> user = userRepository.findByUsernameOrEmailAndPassword(userLoginDto.getIdentifier(), userLoginDto.getPassword());
        if (user.isPresent()) {
            System.out.println("Expected line no.3");
            return mapper.toUserSlimDto(user.get());
        }
        Optional<User> checkIdentifier = userRepository.findByUsernameOrEmail(userLoginDto.getIdentifier(), userLoginDto.getIdentifier());
        if (checkIdentifier.isPresent() && !checkIdentifier.get().getPassword().equals(userLoginDto.getPassword())) {
            throw new Exception("Invalid password");
        } else {
            throw new Exception("User not found");
        }
    }
}
