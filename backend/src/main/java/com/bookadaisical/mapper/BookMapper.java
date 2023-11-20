package com.bookadaisical.mapper;

import org.mapstruct.Mapper;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    BookSearchFiltersDto toBookSearchFiltersDto(Book book);
    Book toBook(BookSearchFiltersDto bookSearchFiltersDto);
    
}
