package com.bookadaisical.dto.requests;

import com.bookadaisical.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    String title;
    String author;
    int numViews;
    String description;
    int yearOfPublication;
    Genre genre;
}
