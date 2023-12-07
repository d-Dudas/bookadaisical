INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition, artistic_movement)
VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 200, 'Classic American novel', 1925, 'ADULTS', 'VERY_GOOD', 'CLASSICISM'),
(1, 'To Kill a Mockingbird', 'Harper Lee', 180, 'Coming-of-age story', 1960, 'ADULTS', 'LIKE_NEW', 'REALISM'),
(1, '1984', 'George Orwell', 250, 'Dystopian novel', 1949, 'ADULTS', 'NEW', 'DADAISM'),
(1, 'The Catcher in the Rye', 'J.D. Salinger', 150, 'Coming-of-age novel', 1951, 'ADULTS', 'GOOD', 'LITERARY_MOVEMENTS_AFTER_1947'),
(1, 'The Hobbit', 'J.R.R. Tolkien', 220, 'Fantasy adventure', 1937, 'ADULTS', 'ACCEPTABLE', 'POPORANISM'),
(1, 'Pride and Prejudice', 'Jane Austen', 190, 'Classic romance', 1813, 'ADULTS', 'NEW', 'SYMBOLISM'),
(1, 'The Lord of the Rings', 'J.R.R. Tolkien', 300, 'Epic fantasy', 1954, 'ADULTS', 'VERY_GOOD', 'INTEGRALISM'),
(1, 'The Alchemist', 'Paulo Coelho', 120, 'Philosophical novel', 1988, 'ADULTS', 'LIKE_NEW', 'EXPRESSIONISM'),
(1, 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 280, 'Fantasy series', 1997, 'ADULTS', 'NEW', 'FEMINISM'),
(1, 'The Shining', 'Stephen King', 170, 'Horror novel', 1977, 'ADULTS', 'GOOD', 'STRUCTURALISM'),
(1, 'The Odyssey', 'Homer', 210, 'Epic poem', 800, 'ADULTS', 'ACCEPTABLE', 'ENLIGHTENMENT'),
(1, 'The Picture of Dorian Gray', 'Oscar Wilde', 160, 'Gothic novel', 1890, 'ADULTS', 'VERY_GOOD', 'CLASSICISM');

INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition, artistic_movement, created_on)
VALUES
(2, 'The Martian', 'Andy Weir', 230, 'Science fiction', 2011, 'ADULTS', 'GOOD', 'BAROQUE', '2023-05-01 08:30:00'),
(2, 'The Girl on the Train', 'Paula Hawkins', 200, 'Mystery thriller', 2015, 'ADULTS', 'ACCEPTABLE', 'DADAISM', '2023-05-05 12:45:00'),
(2, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 180, 'Non-fiction', 2011, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-10 15:20:00'),
(2, 'The Hunger Games', 'Suzanne Collins', 250, 'Dystopian novel', 2008, 'YOUNG_ADULTS', 'VERY_GOOD', 'PROLETKULT', '2023-05-15 18:10:00'),
(2, 'The Road', 'Cormac McCarthy', 150, 'Post-apocalyptic novel', 2006, 'ADULTS', 'NEW', 'LITERARY_MOVEMENTS_AFTER_1947', '2023-05-20 21:30:00'),
(2, 'The Da Vinci Code', 'Dan Brown', 190, 'Mystery thriller', 2003, 'ADULTS', 'GOOD', 'INTEGRALISM', '2023-05-25 09:45:00'),
(2, 'The Kite Runner', 'Khaled Hosseini', 220, 'Historical drama', 2003, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-28 14:25:00'),
(2, 'Gone Girl', 'Gillian Flynn', 170, 'Mystery thriller', 2012, 'ADULTS', 'ACCEPTABLE', 'SURREALISM', '2023-05-29 17:40:00'),
(2, 'The Silent Patient', 'Alex Michaelides', 200, 'Psychological thriller', 2019, 'ADULTS', 'VERY_GOOD', 'EXPRESSIONISM', '2023-05-03 10:55:00'),
(2, 'The Goldfinch', 'Donna Tartt', 260, 'Coming-of-age novel', 2013, 'ADULTS', 'GOOD', 'ENLIGHTENMENT', '2023-05-08 22:15:00'),
(2, 'Educated', 'Tara Westover', 180, 'Memoir', 2018, 'ADULTS', 'NEW', 'DADAISM', '2023-05-12 11:05:00'),
(2, 'The Testaments', 'Margaret Atwood', 210, 'Dystopian novel', 2019, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-16 19:50:00');

INSERT INTO bookadaisical.active_books (book_id)
VALUES
(6), (18), (2), (15), (7), (10), (12), (9), (11), (22), (16), (20), (3), (14), (21), (5), (8);

INSERT INTO bookadaisical.genres_books (book_id, genre_name)
VALUES (22, 'THRILLER'), (22, 'REALIST');

INSERT INTO bookadaisical.genres_books (book_id, genre_name)
VALUES (22, 'THRILLER'), (22, 'REALIST');

INSERT INTO bookadaisical.genres_books (book_id, genre_name)
VALUES (11, 'CLASSICS'), (11, 'ADVENTURE');

-- Reset the id value back to 1, when deleting all rows inside a table
-- SELECT setval(pg_get_serial_sequence('bookadaisical.books', 'id'), 1, false);
-- DELETE FROM bookadaisical.books;

SELECT DISTINCT b.id, b.title, b.author, b.uploader AS userId, u.username, b.num_views AS numViews, 
b.target_audience AS targetAudience, b.artistic_movement AS artisticMovement, b.book_condition AS condition, 
pcb.amount, ppb.amount, b.description, b.created_on 
FROM bookadaisical.books b
JOIN bookadaisical.active_books ab ON b.id = ab.book_id
JOIN bookadaisical.genres_books gb ON gb.book_id = ab.book_id
JOIN bookadaisical.users u ON u.id = b.uploader
LEFT JOIN bookadaisical.price_currency_books pcb ON b.id = pcb.book_id
LEFT JOIN bookadaisical.price_points_books ppb ON b.id = ppb.book_id
WHERE ('ALL' = 'ALL' OR gb.genre_name = 'ALL');

SELECT * FROM bookadaisical.books b
JOIN bookadaisical.active_books ab ON b.id = ab.book_id
JOIN bookadaisical.genres_books gb ON gb.book_id = ab.book_id
WHERE (gb.genre_name = 'REALIST' OR gb.genre_name = 'ALL')
  AND (b.target_audience = 'ADULTS' OR b.target_audience = 'ALL')
  AND (b.artistic_movement = 'ENLIGHTENMENT' OR b.artistic_movement = 'ALL')
  AND (b.book_condition = 'GOOD' OR b.book_condition = 'ALL')
  AND b.year_of_publication < 2023
  AND b.year_of_publication > 1950
  AND (POSITION(LOWER('donna') IN LOWER(b.title)) != 0 OR POSITION(LOWER('donna') IN LOWER(b.author)) != 0 OR POSITION(LOWER('agea') IN LOWER(b.description)) != 0);

  SELECT * FROM bookadaisical.books b
JOIN bookadaisical.active_books ab ON b.id = ab.book_id
JOIN bookadaisical.genres_books gb ON gb.book_id = ab.book_id
WHERE (gb.genre_name = ? OR gb.genre_name = 'ALL')
  AND (b.target_audience = ? OR b.target_audience = 'ALL')
  AND (b.artistic_movement = ? OR b.artistic_movement = 'ALL')
  AND (b.book_condition = ? OR b.book_condition = 'ALL')
  AND b.year_of_publication < ?
  AND b.year_of_publication > ?
  AND (POSITION(LOWER(?) IN LOWER(b.title)) != 0 OR POSITION(LOWER(?) IN LOWER(b.author)) != 0 OR POSITION(LOWER(?) IN LOWER(b.description)) != 0);

SELECT * FROM bookadaisical.books;
SELECT * FROM bookadaisical.images_books;
SELECT * FROM bookadaisical.images;
SELECT * FROM bookadaisical.users;
SELECT * FROM bookadaisical.trading_options_books;


INSERT INTO bookadaisical.images_books (book_id, image_id)
VALUES (22, 3);
INSERT INTO bookadaisical.images_books (book_id, image_id)
VALUES (11, 4);

INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES (22, 'CURRENCY');

INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES (11, 'POINTS');
INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES (11, 'SWAP');

INSERT INTO bookadaisical.price_currency_books (book_id, amount)
VALUES (22, 32);

INSERT INTO bookadaisical.price_points_books (book_id, amount)
VALUES (11, 10);