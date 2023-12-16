package com.bookadaisical.exceptions;

public class UriNotFoundException extends IllegalStateException {
    public UriNotFoundException(){
        super("uri_not_found");
    }
}
