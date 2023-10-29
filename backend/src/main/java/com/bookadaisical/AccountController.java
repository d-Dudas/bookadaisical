package com.bookadaisical;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @PostMapping("/register-account")
    public ResponseEntity<String> receiveDataFromRegisterAccountForm(@RequestBody String data)
    {
        return ResponseEntity.ok("Data received successfully");
    }
}
