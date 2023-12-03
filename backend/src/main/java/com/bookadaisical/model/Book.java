package com.bookadaisical.model;

import java.time.LocalDateTime;
import java.util.List;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "bookadaisical", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "uploader", referencedColumnName = "id", nullable = false)
    private User uploader;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "num_views")
    private int numViews;

    @Column
    private String description;
    
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @Enumerated(EnumType.STRING)
    @Column(name = "artistic_movement")
    private ArtisticMovement artisticMovement;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_audience")
    private TargetAudience targetAudience;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_condition")
    private Condition bookCondition;

    @OneToMany(mappedBy = "activeBook", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActiveBook> activeBooks;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GenreBook> genreBooks;

}
