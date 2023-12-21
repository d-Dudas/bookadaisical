package com.bookadaisical.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.specifications.BookSpecification;
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
        List<Book> topTenMonthlyBooks = bookRepository.findTopTenBooks();
        return topTenMonthlyBooks;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> books = new ArrayList<>();
        for(Book book : bookRepository.findAll()) {
            books.add(new BookDto(book));
        }

        return books;
    }

    // @Override
    // public List<Book> getFilteredBooks(BookSearchFiltersDto bookSearchFiltersDto) throws Exception {
    //     System.out.println("FILTER DTO: " + bookSearchFiltersDto.toString() );

    //     // int idIndex = 0;
    //     // int titleIndex = 1;
    //     // int authorIndex = 2;
    //     // int numViewsIndex = 3;
    //     // int descriptionIndex = 4;
    //     // int createdOnIndex = 5;

    //     //List<BookResponseDto> filteredBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
    //     //List<BookResponseDto> filteredBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());
    //     //return filteredBooks;

    //     //List<BookResponseDto> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());

    //     // List<Book> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery();
    //     List<Book> filteredByGenreBooks = bookRepository.findAll();
    //     //List<Book> filteredByGenreBooks = bookRepository.findAllByGenreNativeQuery(bookSearchFiltersDto.getGenre().toString());

    //     return filteredByGenreBooks;

    //     /*List<Book> filteredBooks =
    //     bookRepository.findFilteredBooks(bookSearchFiltersDto.getGenre().toString(),
    //                                     bookSearchFiltersDto.getTargetAudience(),
    //                                     bookSearchFiltersDto.getArtisticMovement(),
    //                                     bookSearchFiltersDto.getCondition().toString(),
    //                                     bookSearchFiltersDto.getYearOfPublicationNotBiggerThen(),
    //                                     bookSearchFiltersDto.getYearOfPublicationNotLessThen(),
    //                                     bookSearchFiltersDto.getContains());*/
    //     //return filteredBooks;
    // }

    public List<Book> getFilteredBooks(BookSearchFiltersDto filters) {
        BookSpecification spec = new BookSpecification(filters);
        return bookRepository.findAll(spec);
    }

    @Override
    public List<Book> getBooksByUserId(UUID userId) {
        return bookRepository.findAllBooksByUploaderId(userId);
    }

}
