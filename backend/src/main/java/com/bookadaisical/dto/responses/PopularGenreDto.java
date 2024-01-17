package com.bookadaisical.dto.responses;

import com.bookadaisical.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PopularGenreDto {
    Genre genre;
    BookDto book;
}
