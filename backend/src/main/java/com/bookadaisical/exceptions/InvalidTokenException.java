package com.bookadaisical.exceptions;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(){
        super("invalid_token");
    }
}
