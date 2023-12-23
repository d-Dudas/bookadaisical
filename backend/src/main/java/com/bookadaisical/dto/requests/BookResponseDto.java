package com.bookadaisical.dto.requests;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

import lombok.Data;

@Data
public class BookResponseDto {
    UUID bookId;
    String title;
    String author;
    UUID userId;
    String username;
    Integer numViews;
    TargetAudience targetAudience;
    Condition condition;
    ArtisticMovement artisticMovement;
    Float currency;
    Integer points;
    String description;
    LocalDateTime createdOn;
}
