package com.bookadaisical.service.interfaces;

import java.util.List;

import com.bookadaisical.dto.requests.BookIdDto;
import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.requests.CreateNewBookDto;
import com.bookadaisical.dto.requests.UsernameDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.dto.responses.PopularGenreDto;

public interface IBookService {
    public List<BookDto> getTopTenBooks() throws Exception;
    public List<BookDto> getRecentlyAddedBooks() throws Exception;
    public List<BookDto> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception;
    public List<BookDto> getAllBooks() throws Exception;
    public List<BookDto> getBooksByUploaderUsername(String username);
    public List<PopularGenreDto> getMostPopularCategories() throws Exception;
    public void uploadNewBook(CreateNewBookDto createNewBookDto) throws Exception;
    public List<BookDto> getRecommendedBooks(UsernameDto usernameDto) throws Exception;
    public void updateView(BookIdDto bookIdDto) throws Exception;
}