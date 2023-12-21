package com.bookadaisical.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.model.Book;

public interface IBookService {

    List<Book> getTopTenBooks() throws Exception;
    List<Book> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception;
    List<BookDto> getAllBooks() throws Exception;
    List<Book> getBooksByUserId(UUID userId);
}