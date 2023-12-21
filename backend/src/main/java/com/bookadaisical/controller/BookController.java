package com.bookadaisical.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.BookSearchFiltersDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Temporary import
import com.bookadaisical.hardcodedValues.BooksProvider;

@RestController
public class BookController {

    private final BookService bookService;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public ResponseEntity<?> GetAllBooks() {
        try {
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
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
    public ResponseEntity<BookDto> getBookById(@RequestBody String uniqueId)
    {
        UUID bookId = UUID.fromString(uniqueId);
        System.out.println("\n\nGot this UUID: " + uniqueId + "\n\n");
        System.out.println("\n\nGot this UUID: " + bookId + "\n\n");
        // List<Book> books = BooksProvider.getHardcodedBooksList();
        List<BookDto> books = bookService.getAllBooks();
        for(BookDto book : books)
        {
            System.out.println("Book id:" + book.getId());
            if(book.getId().equals(bookId))
                return ResponseEntity.ok(book);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/get-user-books/{userId}")
    public ResponseEntity<List<Book>> getUserBooks(@PathVariable("userId") UUID userId)
    {
        List<Book> userBooks = bookService.getBooksByUserId(userId);
        log.info("User ID: {}", userId);
        return ResponseEntity.ok(userBooks);
    }
}
