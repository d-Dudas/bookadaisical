package com.bookadaisical.model;

import java.time.LocalDate;

import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "bookadasical", name = "books")
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
    private LocalDate createdOn;

    @Column(name = "last_modified")
    private LocalDate lastModified;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @Column(name = "target_audience")
    private TargetAudience targetAudience;

    @Column(name = "book_condition")
    private Condition bookCondition;

}
