package com.bookadaisical.dto.requests;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSearchFiltersDto {
    private Genre genre;
    private TargetAudience targetAudience;
    private ArtisticMovement artisticMovement;
    private Condition condition;
    private TradingOption tradingOption;
    private int yearOfPublicationNotLessThen;
    private int yearOfPublicationNotBiggerThen;
    private String contains;
}
