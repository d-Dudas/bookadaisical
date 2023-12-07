package com.bookadaisical.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.Data;

@Data
@Entity
@IdClass(ImageBookId.class)
@Table(schema = "bookadaisical", name = "images_books")
public class ImageBook {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_images_books_books"))
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false,
    foreignKey = @ForeignKey(name = "fk_images_books_images"))
    private Image image;
}
