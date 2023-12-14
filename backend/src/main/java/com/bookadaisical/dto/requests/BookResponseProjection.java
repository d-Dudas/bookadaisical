package com.bookadaisical.dto.requests;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

public interface BookResponseProjection {
    
    UUID getBookId();
    String getTitle();
    String getAuthor();
    UUID getUserId();
    Integer getNumViews();
    TargetAudience getTargetAudience();
    Condition getCondition();
    ArtisticMovement getArtisticMovement();
    Float getCurrency();
    Integer getPoints();
    String getDescription();
    LocalDateTime getCreatedOn();

}
