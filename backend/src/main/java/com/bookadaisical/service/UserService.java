package com.bookadaisical.service;

import com.bookadaisical.service.interfaces.IUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.UserRegisterDto;
import com.bookadaisical.mapper.MapStructMapper;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.UserRepository;

@Service
public class UserService implements IUserService {
    
    private final UserRepository userRepository;
    private final MapStructMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, MapStructMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(UserRegisterDto userRegisterDto) {
        User user = mapper.toUser(userRegisterDto);
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            try {
                throw new Exception(String.format("User with username \"%s\" is already registered", user.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            try {
                throw new Exception(String.format("User with email \"%s\" is already registered", user.getEmail()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername()).get();
    }

}
