INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition, artistic_movement)
VALUES
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Great Gatsby', 'F. Scott Fitzgerald', 200, 'Classic American novel', 1925, 'ADULTS', 'VERY_GOOD', 'CLASSICISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'To Kill a Mockingbird', 'Harper Lee', 180, 'Coming-of-age story', 1960, 'ADULTS', 'LIKE_NEW', 'REALISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', '1984', 'George Orwell', 250, 'Dystopian novel', 1949, 'ADULTS', 'NEW', 'DADAISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Catcher in the Rye', 'J.D. Salinger', 150, 'Coming-of-age novel', 1951, 'ADULTS', 'GOOD', 'LITERARY_MOVEMENTS_AFTER_1947'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Hobbit', 'J.R.R. Tolkien', 220, 'Fantasy adventure', 1937, 'ADULTS', 'ACCEPTABLE', 'POPORANISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'Pride and Prejudice', 'Jane Austen', 190, 'Classic romance', 1813, 'ADULTS', 'NEW', 'SYMBOLISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Lord of the Rings', 'J.R.R. Tolkien', 300, 'Epic fantasy', 1954, 'ADULTS', 'VERY_GOOD', 'INTEGRALISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Alchemist', 'Paulo Coelho', 120, 'Philosophical novel', 1988, 'ADULTS', 'LIKE_NEW', 'EXPRESSIONISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 280, 'Fantasy series', 1997, 'ADULTS', 'NEW', 'FEMINISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Shining', 'Stephen King', 170, 'Horror novel', 1977, 'ADULTS', 'GOOD', 'STRUCTURALISM'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Odyssey', 'Homer', 210, 'Epic poem', 800, 'ADULTS', 'ACCEPTABLE', 'ENLIGHTENMENT'),
('1f7173dd-248e-4a75-b03b-919129be2cad', 'The Picture of Dorian Gray', 'Oscar Wilde', 160, 'Gothic novel', 1890, 'ADULTS', 'VERY_GOOD', 'CLASSICISM');

INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition, artistic_movement, created_on)
VALUES
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Martian', 'Andy Weir', 230, 'Science fiction', 2011, 'ADULTS', 'GOOD', 'BAROQUE', '2023-05-01 08:30:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Girl on the Train', 'Paula Hawkins', 200, 'Mystery thriller', 2015, 'ADULTS', 'ACCEPTABLE', 'DADAISM', '2023-05-05 12:45:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 180, 'Non-fiction', 2011, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-10 15:20:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Hunger Games', 'Suzanne Collins', 250, 'Dystopian novel', 2008, 'YOUNG_ADULTS', 'VERY_GOOD', 'PROLETKULT', '2023-05-15 18:10:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Road', 'Cormac McCarthy', 150, 'Post-apocalyptic novel', 2006, 'ADULTS', 'NEW', 'LITERARY_MOVEMENTS_AFTER_1947', '2023-05-20 21:30:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Da Vinci Code', 'Dan Brown', 190, 'Mystery thriller', 2003, 'ADULTS', 'GOOD', 'INTEGRALISM', '2023-05-25 09:45:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Kite Runner', 'Khaled Hosseini', 220, 'Historical drama', 2003, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-28 14:25:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'Gone Girl', 'Gillian Flynn', 170, 'Mystery thriller', 2012, 'ADULTS', 'ACCEPTABLE', 'SURREALISM', '2023-05-29 17:40:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Silent Patient', 'Alex Michaelides', 200, 'Psychological thriller', 2019, 'ADULTS', 'VERY_GOOD', 'EXPRESSIONISM', '2023-05-03 10:55:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Goldfinch', 'Donna Tartt', 260, 'Coming-of-age novel', 2013, 'ADULTS', 'GOOD', 'ENLIGHTENMENT', '2023-05-08 22:15:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'Educated', 'Tara Westover', 180, 'Memoir', 2018, 'ADULTS', 'NEW', 'DADAISM', '2023-05-12 11:05:00'),
('8c5fa226-ec07-480a-ad4a-4bce96fd9abc', 'The Testaments', 'Margaret Atwood', 210, 'Dystopian novel', 2019, 'ADULTS', 'LIKE_NEW', 'POPORANISM', '2023-05-16 19:50:00');

SELECT * FROM bookadaisical.books;

INSERT INTO bookadaisical.genres_books (book_id, genre_name)
VALUES ('b5137779-1cf4-4746-b7a3-963a8a6cdb09', 'THRILLER'), ('b5137779-1cf4-4746-b7a3-963a8a6cdb09', 'REALIST');

INSERT INTO bookadaisical.genres_books (book_id, genre_name)
VALUES ('490fcc89-a647-41da-abc0-4b5a354225cc', 'CLASSICS'), ('490fcc89-a647-41da-abc0-4b5a354225cc', 'ADVENTURE');


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
SELECT * FROM bookadaisical.images;
SELECT * FROM bookadaisical.users;
SELECT * FROM bookadaisical.trading_options_books;
SELECT * FROM bookadaisical.genres_books;

INSERT INTO bookadaisical.images_books (book_id, image_id)
VALUES ('b5137779-1cf4-4746-b7a3-963a8a6cdb09', 'c0b5f54b-19cd-4953-9425-674c5e7d674b');
INSERT INTO bookadaisical.images_books (book_id, image_id)
VALUES ('490fcc89-a647-41da-abc0-4b5a354225cc', '25ce7fd0-f526-466c-bd00-0542a6f4b816');

INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES ('b5137779-1cf4-4746-b7a3-963a8a6cdb09', 'CURRENCY');

INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES ('490fcc89-a647-41da-abc0-4b5a354225cc', 'POINTS');
INSERT INTO bookadaisical.trading_options_books (book_id, trading_option)
VALUES ('490fcc89-a647-41da-abc0-4b5a354225cc', 'SWAP');

INSERT INTO bookadaisical.price_currency_books (book_id, amount)
VALUES ('b5137779-1cf4-4746-b7a3-963a8a6cdb09', 32);

INSERT INTO bookadaisical.price_points_books (book_id, amount)
VALUES ('490fcc89-a647-41da-abc0-4b5a354225cc', 10);

SELECT DISTINCT b.id AS bookId,  
b.title AS title,  
b.author AS author,  
b.uploader AS userId,  
b.num_views AS numViews,  
b.target_audience AS targetAudience,  
b.book_condition AS condition,  
b.artistic_movement AS artisticMovement,  
pcb.amount AS currency,  
ppb.amount AS points,  
b.description AS description,  
b.created_on AS createdOn  
FROM bookadaisical.books b  
JOIN bookadaisical.active_books ab ON b.id = ab.book_id  
JOIN bookadaisical.users u ON u.id = b.uploader  
LEFT JOIN bookadaisical.price_currency_books pcb ON b.id = pcb.book_id  
LEFT JOIN bookadaisical.price_points_books ppb ON b.id = ppb.book_id  
WHERE EXTRACT(MONTH FROM b.created_on) = EXTRACT(MONTH FROM (CURRENT_DATE))
AND EXTRACT(YEAR FROM b.created_on) = EXTRACT(YEAR FROM CURRENT_DATE) 
ORDER BY B.num_views DESC;

-- create view mapped to the BookResponseDto class
CREATE VIEW book_response AS 
SELECT DISTINCT b.id AS book_id,  
b.title AS title,  
b.author AS author,  
b.uploader AS user_id,  
b.num_views AS num_views,  
b.target_audience AS target_audience,  
b.book_condition AS condition,  
b.artistic_movement AS artistic_movement,  
pcb.amount AS currency,  
ppb.amount AS points,  
b.description AS description,  
b.created_on AS created_on  
FROM bookadaisical.books b  
JOIN bookadaisical.active_books ab ON b.id = ab.book_id  
JOIN bookadaisical.users u ON u.id = b.uploader  
LEFT JOIN bookadaisical.price_currency_books pcb ON b.id = pcb.book_id  
LEFT JOIN bookadaisical.price_points_books ppb ON b.id = ppb.book_id;
-- DROP VIEW bookadaisical.book_response;

-- get top ten books
SELECT * FROM bookadaisical.books b
WHERE EXTRACT(MONTH FROM b.created_on) = EXTRACT(MONTH FROM (CURRENT_DATE))
AND EXTRACT(YEAR FROM b.created_on) = EXTRACT(YEAR FROM CURRENT_DATE) 
ORDER BY b.num_views DESC LIMIT 10;

SELECT * FROM bookadaisical.book_response br
JOIN bookadaisical.genres_books gb ON br.book_id = gb.book_id
WHERE (gb.genre_name = CAST('ALL' AS bookadaisical.genres) OR CAST('ALL' AS bookadaisical.genres) = 'ALL')
IN (SELECT max(genres_books.genre_name) FROM genres_books GROUP BY genres_books.book_id);

SELECT * FROM bookadaisical.genres_books;
SELECT * FROM bookadaisical.images;