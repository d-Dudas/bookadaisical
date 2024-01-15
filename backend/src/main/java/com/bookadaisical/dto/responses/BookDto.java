package com.bookadaisical.dto.responses;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;
import com.bookadaisical.model.Book;
import com.bookadaisical.model.Image;

import lombok.Data;

// TODO: Add price
@Data
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private String description;
    private Integer yearOfPublication;
    private ArtisticMovement artisticMovement;
    private TargetAudience targetAudience;
    private Condition condition;
    private Boolean isActive;
    private Set<byte[]> images;
    private Set<Genre> genres;
    private Set<TradingOption> tradingOptions;

    private String uploaderUsername;
    private Integer views;
    private LocalDateTime createdOn;
    private LocalDateTime lastModifiedOn;

    private Integer priceCurrency;
    private Integer pricePoints;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.description = book.getDescription();
        this.yearOfPublication = book.getYearOfPublication();
        this.artisticMovement = book.getArtisticMovement();
        this.targetAudience = book.getTargetAudience();
        this.condition = book.getBookCondition();
        this.isActive = book.isActive();
        this.genres = book.getGenres();
        this.tradingOptions = book.getTradingOptions();
        this.uploaderUsername = book.getUploader().getUsername();
        this.views = book.getNumViews();
        this.createdOn = book.getCreatedOn();
        this.lastModifiedOn = book.getLastModified();
        this.priceCurrency = book.getPriceCurrency();
        this.pricePoints = book.getPricePoints();

        this.images = new HashSet<>();
        for(Image image : book.getImages()) {
            images.add(image.getImageData());
        }
    }
}
