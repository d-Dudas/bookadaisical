package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.BookResponseDto;
import com.bookadaisical.dto.requests.BookResponseProjection;
import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;

public interface IBookService {

    List<Book> getTopTenBooks() throws Exception;
    List<BookResponseProjection> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception;
}