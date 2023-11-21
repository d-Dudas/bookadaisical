package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.BookDto;
import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;

public interface IBookService {

    List<Book> getTopTenBooks() throws Exception;
    List<BookDto> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception;
}