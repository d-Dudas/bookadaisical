package com.bookadaisical.dto.requests;

import java.time.LocalDateTime;

import com.bookadaisical.dto.responses.UserSlimDto;
import com.bookadaisical.dto.responses.interfaces.UserSlimDtoProjection;
import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

public interface BookResponseProjection {
    
    Long getBookId();
    String getTitle();
    String getAuthor();
    UserSlimDtoProjection getUserSlimDto();
    Integer getNumViews();
    TargetAudience getTargetAudience();
    Condition getCondition();
    ArtisticMovement getArtisticMovement();
    Float getCurrency();
    Integer getPoints();
    String getDescription();
    LocalDateTime getCreatedOn();

}
