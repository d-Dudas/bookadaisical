package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.requests.CreateNewBookDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.dto.responses.PopularGenreDto;

public interface IBookService {
    List<BookDto> getTopTenBooks() throws Exception;
    List<BookDto> getRecentlyAddedBooks() throws Exception;
    List<BookDto> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception;
    List<BookDto> getAllBooks() throws Exception;
    List<BookDto> getBooksByUploaderUsername(String username);
    List<PopularGenreDto> getMostPopularCategories() throws Exception;
    void uploadNewBook(CreateNewBookDto createNewBookDto) throws Exception;
}