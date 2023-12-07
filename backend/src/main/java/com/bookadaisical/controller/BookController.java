package com.bookadaisical.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.service.BookService;

// Temporary import
import com.bookadaisical.hardcodedValues.BooksProvider;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/top-ten-books")
    public ResponseEntity<?> getTopTenBooks() {
        try {
            return new ResponseEntity<>(bookService.getTopTenBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @PostMapping("/get-filtered-books")
    public ResponseEntity<?> getFilteredBooks(@RequestBody BookSearchFiltersDto searchFilters)
    {
        try {
            return new ResponseEntity<>(bookService.getFilteredBooks(searchFilters), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    @PostMapping("/get-book-id")
    public ResponseEntity<Book> getBookById(@RequestBody int uniqueId)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        for(Book book : books)
        {
            if(book.getId() == uniqueId)
                return ResponseEntity.ok(book);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/get-user-books/{userId}")
    public ResponseEntity<List<Book>> getUserBooks(@PathVariable("userId") int userId)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        books = books.stream()
                    .filter(book -> book.getUploader().getId() == userId)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
}
