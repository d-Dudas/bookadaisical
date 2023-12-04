package com.bookadaisical.exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(){
        super("invalid_password");
    }
}
