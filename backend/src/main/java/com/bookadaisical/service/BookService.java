package com.bookadaisical.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.specifications.BookSpecification;
import com.bookadaisical.service.interfaces.IBookService;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getTopTenBooks() {
        List<Book> books = new ArrayList<>();
        int daysBack = 30;
        Pageable topTen = PageRequest.of(0, 10);

        while (books.size() < 10 && daysBack <= 180) {
            LocalDateTime startDate = LocalDateTime.now().minusDays(daysBack);
            books = bookRepository.findBooksByUploadDateAndViews(startDate, topTen);
            daysBack *= 2;
        }

        return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
    }

    public List<BookDto> getRecentlyAddedBooks(){
        List<Book> books = bookRepository.findTop10ByIsActiveOrderByCreatedOnDesc(
            true,
            PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdOn"))
        );

        return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
    }

    public List<BookDto> getFilteredBooks(BookSearchFiltersDto filters) {
        BookSpecification spec = new BookSpecification(filters);
        List<Book> books = bookRepository.findAll(spec);

        return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByUploaderUsername(String username) {
        List<Book> books = bookRepository.findAllBooksByUploaderUsername(username);

        return books.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
    }
}
