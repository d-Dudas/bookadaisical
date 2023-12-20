package com.bookadaisical.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bookadaisical.model.User;
import com.bookadaisical.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        addUsers();
    }

    private void addUsers()
    {
        List<User> users = new ArrayList<>(
            Arrays.asList(
                new User("michaelJackson", "michaelJackson@mail.com", "hello"),
                new User("scarlettJohansson", "scarlettJohansson@mail.com", "hello"),
                new User("robertDowney", "robertDowney@mail.com", "hello"),
                new User("tomHanks", "tomHanks@mail.com", "hello"),
                new User("willSmith", "willSmith@mail.com", "hello"),
                new User("tomCruise", "tomCruise@mail.com", "hello"),
                new User("mattDamon", "mattDamon@mail.com", "hello"),
                new User("liamNeeson", "liamNeeson@mail.com", "hello")
            )
        );
        for(User user : users) {
            userRepository.save(user);
        }
    }
}
