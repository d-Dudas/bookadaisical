package com.bookadaisical.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.BookDto;
import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.model.Book;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.service.interfaces.IBookService;

import jakarta.transaction.Transactional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;

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

    @Override
    public List<BookDto> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception {

        //List<Book> filteredByGenreBooks = bookRepository.findAllByGenre(testGenre.toString());
        List<Object[]> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
        List<BookDto> filteredByGenreBooksFin = filteredByGenreBooks.stream().map(objects -> new BookDto(
            (String) objects[0],
            (String) objects[1],
            (int) objects[2],
            (String) objects[3],
            (int) objects[4],
            Genre.valueOf((String) objects[5])
        )).collect(Collectors.toList());
        return filteredByGenreBooksFin;

        /*List<Book> filteredBooks = 
        bookRepository.findFilteredBooks(bookSearchFiltersDto.getGenre().toString(), 
                                        bookSearchFiltersDto.getTargetAudience(),
                                        bookSearchFiltersDto.getArtisticMovement(), 
                                        bookSearchFiltersDto.getCondition().toString(),  
                                        bookSearchFiltersDto.getYearOfPublicationNotBiggerThen(), 
                                        bookSearchFiltersDto.getYearOfPublicationNotLessThen(),
                                        bookSearchFiltersDto.getContains());*/                                   
        //return filteredBooks;
        
    }

}
