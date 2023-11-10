package com.bookadaisical.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookadaisical.dto.SearchFiltersDto;
import com.bookadaisical.model.Book;
import com.bookadaisical.utils.TargetAudience;

@RestController
public class BookController {

    private void populateList(List<Book> books)
    {
        books.add(new Book(1, "book1", "author1", "description1", TargetAudience.ADULTS));
        books.add(new Book(2, "book2", "author2", "description2", TargetAudience.CHILDREN));
        books.add(new Book(3, "book3", "author3", "description3", TargetAudience.ADULTS));
        books.add(new Book(4, "book4", "author4", "description4", TargetAudience.CHILDREN));
        books.add(new Book(5, "book5", "author5", "description5", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(6, "book6", "author6", "description6", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(7, "book7", "author7", "description7", TargetAudience.ADULTS));
        books.add(new Book(8, "book8", "author8", "description8", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(9, "book9", "author9", "description9", TargetAudience.CHILDREN));
        books.add(new Book(10, "book10", "author10", "description10", TargetAudience.ADULTS));
        books.add(new Book(11, "book11", "author11", "description11", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(12, "book12", "author12", "description12", TargetAudience.ADULTS));
        books.add(new Book(13, "book13", "author13", "description13", TargetAudience.CHILDREN));
    }

    @GetMapping("/top-ten-books")
    public ResponseEntity<List<Book>> getTopTenBooks()
    {
        List<Book> books = new ArrayList<>();
        populateList(books);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/get-filtered-books")
    public ResponseEntity<List<Book>> getFilteredBooks(@RequestBody SearchFiltersDto searchFilters)
    {
        List<Book> books = new ArrayList<>();
        populateList(books);
        books = books.stream()
            .filter(b -> (searchFilters.getTargetAudience() == b.getTargetAudience()
                      || searchFilters.getTargetAudience() == TargetAudience.ALL)
                      && (b.getTitle().contains(searchFilters.getContains())
                      || searchFilters.getContains().equals(""))
            ).toList();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/get-book")
    public ResponseEntity<Book> getBookById(@RequestBody int uniqueId)
    {
        List<Book> books = new ArrayList<>();
        populateList(books);
        for(Book book : books)
        {
            if(book.getUniqueId() == uniqueId)
                return ResponseEntity.ok(book);
        }

        return ResponseEntity.badRequest().body(null);
    }
}
