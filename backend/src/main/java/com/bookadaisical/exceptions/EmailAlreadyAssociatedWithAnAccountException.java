package com.bookadaisical.exceptions;

public class EmailAlreadyAssociatedWithAnAccountException extends Exception {
    public EmailAlreadyAssociatedWithAnAccountException(){
        super("email_already_associated_with_an_account");
    }
}
