package com.bookadaisical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.BookResponseDto;
import com.bookadaisical.dto.requests.BookSearchFiltersDto;
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
    public List<BookResponseDto> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception {

        int idIndex = 0;
        int titleIndex = 1;
        int authorIndex = 2;
        int numViewsIndex = 3;
        int descriptionIndex = 4;
        int createdOnIndex = 5;

        //List<BookResponseDto> filteredBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
        //List<BookResponseDto> filteredBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
        //return filteredBooks;

        //List<BookResponseDto> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());

        List<BookResponseDto> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
        
        /*List<BookResponseDto> filteredByGenreBooksFin = filteredByGenreBooks.stream().map(fullDetailBookRaw -> new BookResponseDto(
            (int) fullDetailBookRaw[idIndex],
            (String) fullDetailBookRaw[titleIndex],
            (String) fullDetailBookRaw[authorIndex],
            (int) fullDetailBookRaw[numViewsIndex],
            (String) fullDetailBookRaw[descriptionIndex],
            ((java.sql.Timestamp) fullDetailBookRaw[createdOnIndex]).toLocalDateTime()  
        )).collect(Collectors.toList());
        return filteredByGenreBooksFin;*/
        return filteredByGenreBooks;

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
