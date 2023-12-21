package com.bookadaisical.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.bookadaisical.enums.Genre;
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
        // addUsers();
        User user = new User("tomHanks", "tomHanks@mail.com", "hello");
        Book book = new Book(user, "Metamorphosis", "Kafka");
        book.getGenres().add(Genre.ACADEMIC);
        byte[] imageData = loadImage("metamorphosis.jpeg");
        Image image = new Image();
        // image.setBook(book);
        image.setImageData(imageData);
        image.setImageName("metamorphosis");
        book.addImage(image);
        userRepository.save(user);
        bookRepository.save(book);
        imageRepository.save(image);


        book = new Book(user, "Metamorphosis2", "Kafka2");
        imageData = loadImage("metamorphosis.jpeg");
        image = new Image();
        image.setImageData(imageData);
        image.setImageName("metamorphosis2");
        book.addImage(image);
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
