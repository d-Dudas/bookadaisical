package com.bookadaisical.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.UserLoginDto;
import com.bookadaisical.model.User;

@RestController
public class AccountController {

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto loginRequest) {
        if (loginRequest.getIdentificator().equals("username") && loginRequest.getPassword().equals("password")) {
            User user = new User(0, "Boogiedue", null, null, 123, 0, 0);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
