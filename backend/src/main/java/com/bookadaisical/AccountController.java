package com.bookadaisical;

import org.springframework.http.HttpStatus;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Handle the login logic here and return a response.
        // You can use the loginRequest object to access the loginFormData.

        // Example response:
        if (loginRequest.getAccountIdentificator().equals("username") && loginRequest.getAccountPassword().equals("password")) {
            return ResponseEntity.ok("{\"message\": \"Login successful\"}");
        } else {
            return ResponseEntity.ok("{\"message\": \"Login failed\"}");
        }
    }
}
