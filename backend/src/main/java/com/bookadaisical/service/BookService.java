package com.bookadaisical.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookadaisical.model.Book;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.service.interfaces.IBookService;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public List<Book> getTopTenBooks() throws Exception {
        List<Book> topTenMonthlyBooks = bookRepository.findTopTenBooks(PageRequest.of(0, 10));

        return topTenMonthlyBooks;
    }
}
