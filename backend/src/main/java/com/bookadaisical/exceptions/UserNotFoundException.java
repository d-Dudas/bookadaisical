package com.bookadaisical.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("user_not_found");
    }
}
