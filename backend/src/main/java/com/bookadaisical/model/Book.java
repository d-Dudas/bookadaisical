package com.bookadaisical.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.TargetAudience;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "bookadaisical", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

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

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "book", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

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

}