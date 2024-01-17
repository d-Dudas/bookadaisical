package com.bookadaisical.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.NegotiationStatus;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;
import com.bookadaisical.model.Book;
import com.bookadaisical.model.Chat;
import com.bookadaisical.model.Image;
import com.bookadaisical.model.NegotiationOffer;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.BookRepository;
import com.bookadaisical.repository.ChatRepository;
import com.bookadaisical.repository.ImageRepository;
import com.bookadaisical.repository.NegotiationOfferRepository;
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
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private NegotiationOfferRepository negotiationOfferRepository;

    @Override
    public void run(String... args) throws Exception {
        User harold = addUser("Harold", "harold@mail.com", "hello", 247, 601, 12, false, "users/user1.png");
        User jane = addUser("JaneDoe", "jane.doe@email.com", "hello", 352, 804, 6, false, "users/user2.png");
        User alex = addUser("Alex", "alex.smith@email.com", "hello", 467, 920, 4, false, "users/user3.jpeg");
        User tomHanks = addUser("tomHanks", "tomHanks@mail.com", "hello", 901, 1145, 300, false, "users/user4.jpg");
        User camus = addUser("camus", "camus@mail.com", "hello", 91, 145, 31, false, "users/user5.jpg");

        List<Chat> chats = new ArrayList<>(Arrays.asList(
            new Chat(harold, jane, "hey jane", LocalDateTime.now().minusDays(1).minusHours(3)),
            new Chat(harold, alex, "sup", LocalDateTime.now().minusDays(2).minusHours(4)),
            new Chat(jane, alex, "Hi Alex!", LocalDateTime.now().minusHours(2)),
            new Chat(alex, tomHanks, "Hello there", LocalDateTime.now().minusDays(3)),
            new Chat(tomHanks, camus, "Good morning", LocalDateTime.now().minusDays(4).minusHours(1)),
            new Chat(camus, harold, "How's it going?", LocalDateTime.now().minusDays(5).minusHours(2)),
            new Chat(jane, tomHanks, "Did you see that email?", LocalDateTime.now().minusDays(1).minusHours(4)),
            new Chat(alex, camus, "Nice work!", LocalDateTime.now().minusDays(6).minusHours(5)),
            new Chat(camus, jane, "Thank you!", LocalDateTime.now().minusHours(6)),
            new Chat(tomHanks, harold, "Have a great day", LocalDateTime.now().minusDays(2))
        ));
        chatRepository.saveAll(chats);

        Book bc =
        addBook2(
            camus,
            "The Plague",
            "Albert Camus",
            9,
            "A novel about a plague epidemic",
            15,
            25,
            2001,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.VERY_GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.DARK, Genre.PHILOSOPHICAL)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("the_plague.jpg"))
        );

        addBook2(
            camus,
            "War and Peace",
            "Leo Tolstoy",
            91,
            "A sweeping epic of Russian life",
            150,
            250,
            1998,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.ALTERNATE_HISTORY, Genre.PHILOSOPHICAL)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("war_and_peace.jpeg"))
        );

        addBook2(
            camus,
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            10,
            "A story of the Jazz Age",
            10,
            20,
            2008,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.LIKE_NEW,
            new HashSet<>(Arrays.asList(Genre.CLASSICS)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("the_great_gatsby.jpg"))
        );

        addBook2(
            camus,
            "Crime and Punishment",
            "Fyodor Dostoevsky",
            110,
            "A psychological novel about crime",
            60,
            90,
            2018,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.LIKE_NEW,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.ADVENTURE, Genre.DARK)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("crime_and_punishment.jpg"))
        );

        addBook2(
            camus,
            "Wuthering Heights",
            "Emily Bronte",
            290,
            "A tragic tale of love and revenge",
            65,
            100,
            2001,
            ArtisticMovement.POSTMODERNISM,
            TargetAudience.ADULTS,
            Condition.ACCEPTABLE,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.INSPIRATIONAL)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("wuthering_heights.jpg"))
        );

        Book bt =
        addBook2(
            tomHanks,
            "Metamorphosis",
            "Franz Kafka",
            12,
            "A surreal story of transformation",
            120,
            30,
            2010,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.NEW,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.DARK)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("metamorphosis.jpeg"))
        );

        addBook2(
            tomHanks,
            "Moby Dick",
            "Herman Melville",
            301,
            "A tale of obsession and a white whale",
            20,
             130,
            2020,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.ADVENTURE)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("moby_dick.jpg"))
        );
        
        addBook2(
            tomHanks,
            "Pride and Prejudice",
            "Jane Austen",
            31,
            "A classic novel of manners",
            123,
            30,
            2012,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.VERY_GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.ADVENTURE)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("pride_and_prejudice.jpg"))
        );

        addBook2(
            tomHanks,
            "1984",
            "George Orwell",
            551,
            "A dystopian novel about totalitarianism",
            50,
            130,
            2007,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.DARK, Genre.DYSTOPIAN)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("1984.jpg"))
        );

        addBook2(
            tomHanks,
            "To Kill a Mockingbird",
            "Harper Lee",
            55,
            "A novel about racial injustice",
            55,
            30,
            2017,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.ACCEPTABLE,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.INSPIRATIONAL)),
            new HashSet<>(Arrays.asList(TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("to_kill_a_mockingbird.jpg"))
        );
        
        Book bh = 
        addBook2(
            harold,
            "The outsider",
            "Albert Camus",
            122,
            "One of those books that marks a reader's life indelibly.",
            120,
            30,
            2012,
            ArtisticMovement.REALISM,
            TargetAudience.ADULTS,
            Condition.ACCEPTABLE,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.CRIME)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("books/the_outsider/the_outsider.jpeg"))
        );
        addBook2(
            harold,
            "1984",
            "George Orwell",
            247,
            "A dystopian novel set in a world of perpetual war, omnipresent government surveillance, and public manipulation.",
            120,
            30,
            1949,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.ACCEPTABLE,
            new HashSet<>(Arrays.asList(Genre.DYSTOPIAN, Genre.PHILOSOPHICAL)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("books/1984/1984_1.jpg", "books/1984/1984_2.jpeg"))
        );
        addBook2(
            harold,
            "To Kill a Mockingbird",
            "Harper Lee",
            321,
            "A novel of warmth and humor despite dealing with serious issues of rape and racial inequality.",
            150,
            40,
            1960,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.VERY_GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.CRIME)),
            new HashSet<>(Arrays.asList(TradingOption.POINTS, TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("books/to_kill_a_mockingbird/to_kill_a_mockingbird.jpg"))
        );
        addBook2(
            harold,
            "Pride and Prejudice",
            "Jane Austen",
            276,
            "A romantic novel of manners that depicts the emotional development of protagonist Elizabeth Bennet.",
            130,
            35,
            1813,
            ArtisticMovement.CLASSICISM,
            TargetAudience.ADULTS,
            Condition.NEW,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.ROMANTIC)),
            new HashSet<>(Arrays.asList(TradingOption.CURRENCY, TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("books/pride_and_prejudice/pride_and_prejudice.jpeg"))
        );
        addBook2(
            harold,
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            376,
            "A tale of mystery and tragedy, set in the Roaring Twenties.",
            110,
            25,
            1925,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.GOOD,
            new HashSet<>(Arrays.asList(Genre.CLASSICS, Genre.TRAGEDY)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("books/the_great_gatsby/the_great_gatsby.jpg"))
        );
        addBook2(
            harold,
            "Brave New World",
            "Aldous Huxley",
            289,
            "A provocative dystopian novel envisioning a technologically advanced future where human emotions are controlled.",
            140,
            45,
            1932,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.LIKE_NEW,
            new HashSet<>(Arrays.asList(Genre.DYSTOPIAN, Genre.SCI_FI)),
            new HashSet<>(Arrays.asList(TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("books/brave_new_world/brave_new_world.jpeg"))
        );

        Book bj = 
        addBook2(
            jane,
            "The Lord of the Rings",
            "J.R.R. Tolkien",
            532,
            "An epic fantasy novel set in Middle-earth, centering on the quest to destroy the One Ring.",
            220,
            70,
            1954,
            ArtisticMovement.GENERATION_2000,
            TargetAudience.ADULTS,
            Condition.LIKE_NEW,
            new HashSet<>(Arrays.asList(Genre.FANTASY, Genre.ADVENTURE)),
            new HashSet<>(Arrays.asList(TradingOption.CURRENCY, TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("books/lord_of_the_rings/lord_of_the_rings.jpeg"))
        );

        addBook2(
            jane,
            "The Diary of a Young Girl",
            "Anne Frank",
            410,
            "The writings from the Dutch language diary kept by Anne Frank while she was in hiding during World War II.",
            180,
            55,
            1947,
            ArtisticMovement.REALISM,
            TargetAudience.YOUNG_ADULTS,
            Condition.NEW,
            new HashSet<>(Arrays.asList(Genre.BIBLIOGRAPHY, Genre.HISTORICAL)),
            new HashSet<>(Arrays.asList(TradingOption.POINTS, TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("books/diary_of_a_young_girl/diary_of_a_young_girl.jpg"))
        );

        addBook2(
            jane,
            "The Alchemist",
            "Paulo Coelho",
            367,
            "A philosophical book telling the story of a young shepherd's journey to find a worldly treasure.",
            140,
            40,
            1988,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.VERY_GOOD,
            new HashSet<>(Arrays.asList(Genre.PHILOSOPHICAL, Genre.ADVENTURE)),
            new HashSet<>(Arrays.asList(TradingOption.CURRENCY, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("books/the_alchemist/the_alchemist.jpg"))
        );

        Book ba = 
        addBook2(
            alex,
            "A Brief History of Time",
            "Stephen Hawking",
            389,
            "A landmark volume in science writing, explaining the nature of the universe.",
            200,
            65,
            1988,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.NEW,
            new HashSet<>(Arrays.asList(Genre.SCIENCE, Genre.ENCYCLOPEDIC)),
            new HashSet<>(Arrays.asList(TradingOption.POINTS, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("books/a_brief_history_of_time/a_brief_history_of_time.jpg"))
        );

        addBook2(
            alex,
            "Beloved",
            "Toni Morrison",
            275,
            "A powerful, Pulitzer Prize-winning novel about a former slave's struggle to find her identity after the Civil War.",
            160,
            50,
            1987,
            ArtisticMovement.POSTMODERNISM,
            TargetAudience.ADULTS,
            Condition.VERY_GOOD,
            new HashSet<>(Arrays.asList(Genre.HISTORICAL, Genre.PSYCHOLOGICAL)),
            new HashSet<>(Arrays.asList(TradingOption.SWAP, TradingOption.CURRENCY)),
            new HashSet<>(Arrays.asList("books/beloved/beloved.jpg"))
        );

        addBook2(
            alex,
            "Sapiens: A Brief History of Humankind",
            "Yuval Noah Harari",
            498,
            "An exploration of the history and impact of Homo sapiens on the planet.",
            210,
            70,
            2014,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.LIKE_NEW,
            new HashSet<>(Arrays.asList(Genre.SCIENCE)),
            new HashSet<>(Arrays.asList(TradingOption.POINTS, TradingOption.SWAP)),
            new HashSet<>(Arrays.asList("books/sapiens/sapiens.jpeg"))
        );

        addBook2(
            alex,
            "The Catch-22",
            "Joseph Heller",
            415,
            "A satirical novel set during World War II, known for its dark humor.",
            180,
            60,
            1961,
            ArtisticMovement.MODERNISM,
            TargetAudience.ADULTS,
            Condition.GOOD,
            new HashSet<>(Arrays.asList(Genre.SATIRE, Genre.MILITARY)),
            new HashSet<>(Arrays.asList(TradingOption.CURRENCY, TradingOption.POINTS)),
            new HashSet<>(Arrays.asList("books/catch_22/catch_22.jpg"))
        );

        List<NegotiationOffer> negotiations = new ArrayList<>(Arrays.asList(
            new NegotiationOffer(harold, jane, new HashSet<>(Arrays.asList(bh)), new HashSet<>(Arrays.asList(bj)), NegotiationStatus.ACCEPTED),
            new NegotiationOffer(harold, camus, new HashSet<>(Arrays.asList(bh)), new HashSet<>(Arrays.asList(bc)), NegotiationStatus.ONGOING),
            new NegotiationOffer(tomHanks, alex, new HashSet<>(Arrays.asList(bt)), new HashSet<>(Arrays.asList(ba)), NegotiationStatus.ONGOING),
            new NegotiationOffer(jane, tomHanks, new HashSet<>(Arrays.asList(bj)), new HashSet<>(Arrays.asList(bt)), NegotiationStatus.ACCEPTED),
            new NegotiationOffer(alex, harold, new HashSet<>(Arrays.asList(ba)), new HashSet<>(Arrays.asList(bh)), NegotiationStatus.ACCEPTED),
            new NegotiationOffer(camus, jane, new HashSet<>(Arrays.asList(bc)), new HashSet<>(Arrays.asList(bj)), NegotiationStatus.ONGOING),
            new NegotiationOffer(tomHanks, camus, new HashSet<>(Arrays.asList(bt)), new HashSet<>(Arrays.asList(bc)), NegotiationStatus.ONGOING),
            new NegotiationOffer(alex, jane, new HashSet<>(Arrays.asList(ba)), new HashSet<>(Arrays.asList(bj)), NegotiationStatus.ACCEPTED)
        ));
        negotiationOfferRepository.saveAll(negotiations);
    }

    private Book addBook2(User uploader, String title, String author, Integer numViews, String description, Integer priceCurrency,
            Integer pricePoints, int yearOfPublication, ArtisticMovement artisticMovement,
            TargetAudience targetAudience, Condition bookCondition, Set<Genre> genres,
            Set<TradingOption> tradingOptions, Set<String> images)
    {
        List<Image> imagesList = new ArrayList<>();
        for(String imagePath : images)
        {
            try
            {
                byte[] imageData = loadImage(imagePath);
                imagesList.add(new Image(imageData));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Book book = new Book(
            uploader,
            title,
            author,
            numViews,
            description,
            priceCurrency,
            pricePoints,
            yearOfPublication,
            artisticMovement,
            targetAudience,
            bookCondition,
            genres,
            tradingOptions
        );

        bookRepository.save(book);

        for(Image image : imagesList)
        {
            book.addImage(image);
            imageRepository.save(image);
        }

        bookRepository.save(book);

        return book;
    }

    public User addUser(String username, String email, String password, int currentPoints, int totalPoints, int specialCurrency,
                        boolean isAdmin, String imagePath)
    {
        Image image = new Image();
        try {
            byte[] imageData = loadImage(imagePath);
            image.setImageData(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageRepository.save(image);

        User user = new User(
            username,
            email,
            password,
            currentPoints,
            totalPoints,
            specialCurrency,
            isAdmin,
            image
        );

        userRepository.save(user);
        image.setUser(user);
        imageRepository.save(image);

        return user;
    }

    private byte[] loadImage(String resourceName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:images/" + resourceName);
        try (InputStream inputStream = resource.getInputStream()) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
