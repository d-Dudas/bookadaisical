package com.bookadaisical.exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException()
    {
        super("book_not_found");
    }
}
