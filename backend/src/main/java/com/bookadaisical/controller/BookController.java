package com.bookadaisical.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.requests.SearchFiltersDto;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.model.Book;

// Temporary import
import com.bookadaisical.hardcodedValues.BooksProvider;

@RestController
public class BookController {
    @GetMapping("/top-ten-books")
    public ResponseEntity<List<Book>> getTopTenBooks()
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/get-filtered-books")
    public ResponseEntity<List<Book>> getFilteredBooks(@RequestBody SearchFiltersDto searchFilters)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        books = books.stream()
            .filter(b -> (searchFilters.getTargetAudience() == b.getTargetAudience()
                      || searchFilters.getTargetAudience() == TargetAudience.ALL)
                      && (b.getTitle().contains(searchFilters.getContains())
                      || searchFilters.getContains().equals(""))
            ).toList();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/get-book-id")
    public ResponseEntity<Book> getBookById(@RequestBody int uniqueId)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        for(Book book : books)
        {
            if(book.getUniqueId() == uniqueId)
                return ResponseEntity.ok(book);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping(value = "/get-user-books/{userId}")
    public ResponseEntity<List<Book>> getUserBooks(@PathVariable("userId") int userId)
    {
        List<Book> books = BooksProvider.getHardcodedBooksList();
        books = books.stream()
                    .filter(book -> book.getUploader() == userId)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }
}
