package com.bookadaisical;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @PostMapping("/register")
    public ResponseEntity<User> receiveDataFromRegisterAccountForm(@RequestBody RegisterRequest registerRequest)
    {
        User user = new User(0, registerRequest.getUsername(), null, null, 123, 0, 0);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getIdentificator().equals("username") && loginRequest.getPassword().equals("password")) {
            User user = new User(0, "Boogiedue", null, null, 123, 0, 0);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
