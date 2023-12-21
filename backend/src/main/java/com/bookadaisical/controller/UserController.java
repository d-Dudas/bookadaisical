package com.bookadaisical.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.ChangeEmailDto;
import com.bookadaisical.dto.requests.ChangePasswordDto;
import com.bookadaisical.dto.requests.ChangeUsernameDto;
import com.bookadaisical.dto.requests.UserLoginDto;
import com.bookadaisical.dto.requests.UserLoginTokenDto;
import com.bookadaisical.dto.requests.UserRegisterDto;
import com.bookadaisical.hardcodedValues.BooksProvider;
import com.bookadaisical.model.Book;
import com.bookadaisical.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            return new ResponseEntity<>(userService.registerUser(userRegisterDto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) {
        try {
            return new ResponseEntity<>(userService.loginUser(userLoginDto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-book-owner-id/{bookId}")
    public ResponseEntity<?> getBookOwnerId(@PathVariable("bookId") String bookId)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        for (Book book : books) {
            if(book.getId().equals(bookId))
                return ResponseEntity.ok(book.getUploader());
        }

        return ResponseEntity.badRequest().body("book_not_found");
    }

    @PostMapping(value = "/login-with-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginWithToken(@RequestBody UserLoginTokenDto userLoginTokenDto)
    {
        try {
            return ResponseEntity.ok(userService.loginUserWithToken(userLoginTokenDto.getToken(), userLoginTokenDto.getKey()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-user-details/{username}")
    public ResponseEntity<?> getUserDeatils(@PathVariable("username") String username)
    {
        try {
            return ResponseEntity.ok(userService.getUserDetails(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/change-username")
    public ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameDto changeUsernameDto)
    {
        try {
            return ResponseEntity.ok(userService.changeUsername(changeUsernameDto.getUserId(), changeUsernameDto.getNewUsername()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/change-email")
    public ResponseEntity<?> changeEmail(@RequestBody ChangeEmailDto changeEmailDto)
    {
        try {
            return ResponseEntity.ok(userService.changeEmail(changeEmailDto.getUserId(), changeEmailDto.getNewEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto)
    {
        try {
            return ResponseEntity.ok(userService.changePassword(changePasswordDto.getUserId(), changePasswordDto.getNewPassword()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
