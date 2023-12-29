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
import com.bookadaisical.dto.requests.CreateNewBookDto;
import com.bookadaisical.dto.responses.BookDto;
import com.bookadaisical.service.BookService;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all-books")
    public ResponseEntity<?> GetAllBooks() {
        try {
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/top-ten-books")
    public ResponseEntity<?> getTopTenBooks() {
        try {
            return new ResponseEntity<>(bookService.getTopTenBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/books/recently-added-books")
    public ResponseEntity<?> getRecentlyAddedBooks(){
        try {
            return ResponseEntity.ok(bookService.getRecentlyAddedBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/books/most-popular-categories")
    public ResponseEntity<?> getMostPopularCategories() {
        try {
            return ResponseEntity.ok(bookService.getMostPopularCategories());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/get-filtered-books")
    public ResponseEntity<?> getFilteredBooks(@RequestBody BookSearchFiltersDto searchFilters)
    {
        try {
            return new ResponseEntity<>(bookService.getFilteredBooks(searchFilters), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/get-book-by-id")
    public ResponseEntity<BookDto> getBookById(@RequestBody String uniqueId)
    {
        UUID bookId = UUID.fromString(uniqueId);
        List<BookDto> books = bookService.getAllBooks();
        for(BookDto book : books)
        {
            if(book.getId().equals(bookId))
                return ResponseEntity.ok(book);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/get-user-books/{username}")
    public ResponseEntity<List<BookDto>> getUserBooks(@PathVariable("username") String username)
    {
        List<BookDto> userBooks = bookService.getBooksByUploaderUsername(username);
        return ResponseEntity.ok(userBooks);
    }

    @PostMapping("/books/upload-new")
    public ResponseEntity<?> uploadNewBook(@RequestBody CreateNewBookDto createNewBookDto)
    {
        try {
            bookService.uploadNewBook(createNewBookDto);
            return ResponseEntity.ok(createNewBookDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
