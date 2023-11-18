package com.bookadaisical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookadaisical.model.User;
import com.bookadaisical.repository.UserRepository;
import com.bookadaisical.service.UserService;

@SpringBootApplication
public class BookadaisicalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookadaisicalApplication.class, args);
        //TODO Add a new DTO class called UserSlimDto which contains: id and username
    }
}
