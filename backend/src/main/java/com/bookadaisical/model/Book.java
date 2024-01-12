package com.bookadaisical.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "bookadaisical", name = "books")
public class Book {

    // only for developement purposes
    public Book(User uploader, String title, String author)
    {
        this.uploader = uploader;
        this.title = title;
        this.author = author;
        this.createdOn = LocalDateTime.now();
        this.lastModified = createdOn;
        this.isActive = true;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private User uploader;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private int numViews;
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    private LocalDateTime lastModified;
    private int yearOfPublication;

    @Enumerated(EnumType.STRING)
    private ArtisticMovement artisticMovement;

    @Enumerated(EnumType.STRING)
    private TargetAudience targetAudience;

    @Enumerated(EnumType.STRING)
    private Condition bookCondition;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "book", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "genres_books", joinColumns = @JoinColumn(name = "book_id"))
    private Set<Genre> genres = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = TradingOption.class)
    @CollectionTable(name = "trading_options_books", joinColumns = @JoinColumn(name = "book_id"))
    private Set<TradingOption> tradingOptions = new HashSet<>();

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        images.add(image);
        image.setBook(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setBook(null);
    }

    @ManyToMany(mappedBy = "initiatorSelectedBooks")
    Set<NegotiationOffer> selectedInNegotiationsByInitiator;

    @ManyToMany(mappedBy = "responderSelectedBooks")
    Set<NegotiationOffer> selectedInNegotiationsByResponder;
}
