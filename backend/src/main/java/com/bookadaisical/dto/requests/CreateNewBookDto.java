package com.bookadaisical.dto.requests;

import java.util.List;
import java.util.Set;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewBookDto {
    private String title;
    private String author;
    private String description;
    private Integer yearOfPublication;
    private ArtisticMovement artisticMovement;
    private TargetAudience targetAudience;
    private Condition condition;
    private List<String> images;
    private Set<Genre> genres;
    private Set<TradingOption> tradingOptions;
    private Double priceCurrency;
    private Double pricePoints;
    private String uploaderUsername;
}
