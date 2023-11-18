package com.bookadaisical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookadaisical.model.Book;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.service.interfaces.IBookService;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getTopTenBooks() throws Exception {
        List<Book> topTenMonthlyBooks = bookRepository.findTopTenBooks();
        return topTenMonthlyBooks;
    }
}
