package com.bookadaisical.dto.requests;

import java.time.LocalDateTime;

import com.bookadaisical.enums.Genre;

public interface BookResponseDto {
    
    ///Integer id; // Book
    ///String title; // Book
    ///String author; // Book
    // UserSlimDto uploader; // User
    // ISBN
    // images
    ///Integer numViews; // Book
    // purpose
    // priceCurrency
    // pricePoints
    ///String description; // Book
    ///LocalDateTime createdOn; // Change Timestamp to LocalDateTime

    Long getId();
    String getTitle();
    String getAuthor();
    Integer getNumViews();
    String getDescription();
    LocalDateTime getCreatedOn();
    Genre getGenre();   

}
