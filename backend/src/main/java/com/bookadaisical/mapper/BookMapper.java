package com.bookadaisical.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    BookSearchFiltersDto toBookSearchFiltersDto(Book book);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uploader", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "numViews", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "yearOfPublication", ignore = true)
    Book toBook(BookSearchFiltersDto bookSearchFiltersDto);

}
