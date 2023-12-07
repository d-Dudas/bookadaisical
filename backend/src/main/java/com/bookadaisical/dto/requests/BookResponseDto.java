package com.bookadaisical.dto.requests;

import java.time.LocalDateTime;

import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

import lombok.Data;

@Data
public class BookResponseDto implements BookResponseProjection {
    
    Long bookId;
    String title;
    String author;
    UserSlimDto userSlimDto;
    Integer numViews;
    TargetAudience targetAudience;
    Condition condition;
    ArtisticMovement artisticMovement;
    Float currency;
    Integer points;
    String description;
    LocalDateTime createdOn;

}
