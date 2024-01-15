package com.bookadaisical.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

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
    private Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("tomHanks", "tomHanks@mail.com", "hello");
        addBook(user1, "Metamorphosis", "Franz Kafka", "A surreal story of transformation", "metamorphosis.jpeg", "metamorphosis");
        addBook(user1, "Moby Dick", "Herman Melville", "A tale of obsession and a white whale", "moby_dick.jpg", "moby_dick");
        addBook(user1, "Pride and Prejudice", "Jane Austen", "A classic novel of manners", "pride_and_prejudice.jpg", "pride_prejudice");
        addBook(user1, "1984", "George Orwell", "A dystopian novel about totalitarianism", "1984.jpg", "1984");
        addBook(user1, "To Kill a Mockingbird", "Harper Lee", "A novel about racial injustice", "to_kill_a_mockingbird.jpg", "to_kill_mockingbird");

        User user2 = new User("camus", "camus@mail.com", "hello");
        addBook(user2, "The Plague", "Albert Camus", "A novel about a plague epidemic", "the_plague.jpg", "the_plague");
        addBook(user2, "War and Peace", "Leo Tolstoy", "A sweeping epic of Russian life", "war_and_peace.jpeg", "war_peace");
        addBook(user2, "The Great Gatsby", "F. Scott Fitzgerald", "A story of the Jazz Age", "the_great_gatsby.jpg", "great_gatsby");
        addBook(user2, "Crime and Punishment", "Fyodor Dostoevsky", "A psychological novel about crime", "crime_and_punishment.jpg", "crime_punishment");
        addBook(user2, "Wuthering Heights", "Emily Bronte", "A tragic tale of love and revenge", "wuthering_heights.jpg", "wuthering_heights");
    }

    private void addBook(User user, String title, String author, String description, String imageFilename, String imageName) throws Exception
    {
        byte[] imageData = loadImage(imageFilename);
        Image image = new Image();
        image.setImageData(imageData);

        Book book = new Book(user, title, author);
        book.setDescription(description);
        book.setYearOfPublication(random.nextInt(2023-1900+1) + 1900);
        book.getGenres().add(randomEnum(Genre.class));
        book.setBookCondition(randomEnum(Condition.class));
        book.setTargetAudience(randomEnum(TargetAudience.class));
        book.getTradingOptions().add(randomEnum(TradingOption.class));
        book.setArtisticMovement(randomEnum(ArtisticMovement.class));
        book.addImage(image);

        userRepository.save(user);

        byte[] userImageData = loadImage("default_profile_picture.png");
        Image userImage = new Image();
        userImage.setImageData(userImageData);
        userImage.setUser(user);
        imageRepository.save(userImage);

        bookRepository.save(book);
        imageRepository.save(image);
    }

    private <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private byte[] loadImage(String resourceName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:images/" + resourceName);
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
