package com.bookadaisical;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private void populateList(List<Book> books)
    {
        books.add(new Book("book1", "author1", "description1", TargetAudience.ADULTS));
        books.add(new Book("book2", "author2", "description2", TargetAudience.CHILDREN));
        books.add(new Book("book3", "author3", "description3", TargetAudience.ADULTS));
        books.add(new Book("book4", "author4", "description4", TargetAudience.CHILDREN));
        books.add(new Book("book5", "author5", "description5", TargetAudience.YOUNG_ADULTS));
        books.add(new Book("book6", "author6", "description6", TargetAudience.YOUNG_ADULTS));
        books.add(new Book("book7", "author7", "description7", TargetAudience.ADULTS));
        books.add(new Book("book8", "author8", "description8", TargetAudience.YOUNG_ADULTS));
        books.add(new Book("book9", "author9", "description9", TargetAudience.CHILDREN));
        books.add(new Book("book10", "author10", "description10", TargetAudience.ADULTS));
        books.add(new Book("book11", "author11", "description11", TargetAudience.YOUNG_ADULTS));
        books.add(new Book("book12", "author12", "description12", TargetAudience.ADULTS));
        books.add(new Book("book13", "author13", "description13", TargetAudience.CHILDREN));
    }

    @GetMapping("/top-ten-books")
    public ResponseEntity<List<Book>> getTopTenBooks()
    {
        List<Book> books = new ArrayList<>();
        populateList(books);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/filtered-search")
    public ResponseEntity<List<Book>> getFilteredBooks(@RequestBody SearchFilters searchFilters)
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
}
