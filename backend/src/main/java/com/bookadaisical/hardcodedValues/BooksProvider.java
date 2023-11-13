package com.bookadaisical.hardcodedValues;

import java.util.ArrayList;
import java.util.List;

import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.model.Book;

public class BooksProvider {
    public static List<Book> getHardcodedBooksList(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, 1, "book1", "author1", "description1", TargetAudience.ADULTS));
        books.add(new Book(2, 1, "book2", "author2", "description2", TargetAudience.CHILDREN));
        books.add(new Book(3, 1, "book3", "author3", "description3", TargetAudience.ADULTS));
        books.add(new Book(4, 1 , "book4", "author4", "description4", TargetAudience.CHILDREN));
        books.add(new Book(5, 1, "book5", "author5", "description5", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(6, 1, "book6", "author6", "description6", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(7, 2, "book7", "author7", "description7", TargetAudience.ADULTS));
        books.add(new Book(8, 2, "book8", "author8", "description8", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(9, 2, "book9", "author9", "description9", TargetAudience.CHILDREN));
        books.add(new Book(10, 2, "book10", "author10", "description10", TargetAudience.ADULTS));
        books.add(new Book(11, 2, "book11", "author11", "description11", TargetAudience.YOUNG_ADULTS));
        books.add(new Book(12, 2, "book12", "author12", "description12", TargetAudience.ADULTS));
        books.add(new Book(13, 2, "book13", "author13", "description13", TargetAudience.CHILDREN));

        return books;
    }
}
