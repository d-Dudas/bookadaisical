package com.bookadaisical.utils;

import com.bookadaisical.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreCount {
    private Genre genre;
    private Long totalViews;

    public GenreCount(Genre genre, Long totalViews) {
        this.genre = genre;
        this.totalViews = totalViews;
    }
}