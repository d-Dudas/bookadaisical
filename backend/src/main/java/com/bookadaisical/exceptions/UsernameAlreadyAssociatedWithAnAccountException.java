package com.bookadaisical.exceptions;

public class UsernameAlreadyAssociatedWithAnAccountException extends Exception {
    public UsernameAlreadyAssociatedWithAnAccountException(){
        super("username_already_associated_with_an_account");
    }
}
