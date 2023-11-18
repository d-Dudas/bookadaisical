package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.model.Book;

public interface IBookService {

    List<Book> getTopTenBooks() throws Exception;
}