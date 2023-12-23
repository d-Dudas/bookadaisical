package com.bookadaisical.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;
import com.bookadaisical.model.Book;
import com.bookadaisical.model.Image;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.ImageRepository;
import com.bookadaisical.repository.UserRepository;

import org.apache.commons.io.IOUtils;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("tomHanks", "tomHanks@mail.com", "hello");
        Book book = new Book(user, "Metamorphosis", "Kafka");
        book.setDescription("hello world");
        book.setYearOfPublication(1982);
        book.getGenres().add(Genre.ACADEMIC);
        book.setArtisticMovement(ArtisticMovement.ANCIENT_LITERATURE);
        book.setBookCondition(Condition.ACCEPTABLE);
        book.setTargetAudience(TargetAudience.ADULTS);
        book.getTradingOptions().add(TradingOption.ALL);
        byte[] imageData = loadImage("metamorphosis.jpeg");
        Image image = new Image();
        image.setImageData(imageData);
        image.setImageName("metamorphosis");
        book.addImage(image);
        imageData = loadImage("the_plague.jpg");
        image = new Image();
        image.setImageData(imageData);
        image.setImageName("the_plague");
        book.addImage(image);
        userRepository.save(user);
        bookRepository.save(book);
        imageRepository.save(image);

        book = new Book(user, "Metamorphosis2", "Kafka2");
        book.setDescription("hello world");
        book.setYearOfPublication(1987);
        book.getGenres().add(Genre.COOKBOOK);
        book.setBookCondition(Condition.GOOD);
        book.setTargetAudience(TargetAudience.YOUNG_ADULTS);
        book.getTradingOptions().add(TradingOption.CURRENCY);
        imageData = loadImage("metamorphosis.jpeg");
        image = new Image();
        image.setImageData(imageData);
        image.setImageName("metamorphosis2");
        book.addImage(image);
        bookRepository.save(book);
        imageRepository.save(image);

        user = new User("camus", "camus@mail.com", "hello");
        book = new Book(user, "The Plague", "Camus");
        book.setDescription("hello world");
        book.setYearOfPublication(1998);
        book.getGenres().add(Genre.CLASSICS);
        book.setBookCondition(Condition.LIKE_NEW);
        book.setTargetAudience(TargetAudience.CHILDREN);
        book.getTradingOptions().add(TradingOption.SWAP);
        imageData = loadImage("the_plague.jpg");
        image = new Image();
        image.setImageData(imageData);
        image.setImageName("the_plague");
        book.addImage(image);
        userRepository.save(user);
        bookRepository.save(book);
        imageRepository.save(image);
    }

    private byte[] loadImage(String resourceName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:images/" + resourceName);
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
