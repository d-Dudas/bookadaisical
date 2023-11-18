-- Inserts for target_audience and book_condition as ENUM values
INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition)
VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 200, 'Classic American novel', 1925, 'ADULTS', 'VERY_GOOD'),
(1, 'To Kill a Mockingbird', 'Harper Lee', 180, 'Coming-of-age story', 1960, 'ADULTS', 'LIKE_NEW'),
(1, '1984', 'George Orwell', 250, 'Dystopian novel', 1949, 'ADULTS', 'NEW'),
(1, 'The Catcher in the Rye', 'J.D. Salinger', 150, 'Coming-of-age novel', 1951, 'ADULTS', 'GOOD'),
(1, 'The Hobbit', 'J.R.R. Tolkien', 220, 'Fantasy adventure', 1937, 'ADULTS', 'ACCEPTABLE'),
(1, 'Pride and Prejudice', 'Jane Austen', 190, 'Classic romance', 1813, 'ADULTS', 'NEW'),
(1, 'The Lord of the Rings', 'J.R.R. Tolkien', 300, 'Epic fantasy', 1954, 'ADULTS', 'VERY_GOOD'),
(1, 'The Alchemist', 'Paulo Coelho', 120, 'Philosophical novel', 1988, 'ADULTS', 'LIKE_NEW'),
(1, 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 280, 'Fantasy series', 1997, 'ADULTS', 'NEW'),
(1, 'The Shining', 'Stephen King', 170, 'Horror novel', 1977, 'ADULTS', 'GOOD'),
(1, 'The Odyssey', 'Homer', 210, 'Epic poem', 800, 'ADULTS', 'ACCEPTABLE'),
(1, 'The Picture of Dorian Gray', 'Oscar Wilde', 160, 'Gothic novel', 1890, 'ADULTS', 'VERY_GOOD');

-- Inserts for target_audience and book_condition as ENUM values with explicit created_on values
INSERT INTO bookadaisical.books (uploader, title, author, num_views, description, year_of_publication, target_audience, book_condition, created_on)
VALUES
(2, 'The Martian', 'Andy Weir', 230, 'Science fiction', 2011, 'ADULTS', 'GOOD', '2023-05-01 08:30:00'),
(2, 'The Girl on the Train', 'Paula Hawkins', 200, 'Mystery thriller', 2015, 'ADULTS', 'ACCEPTABLE', '2023-05-05 12:45:00'),
(2, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 180, 'Non-fiction', 2011, 'ADULTS', 'LIKE_NEW', '2023-05-10 15:20:00'),
(2, 'The Hunger Games', 'Suzanne Collins', 250, 'Dystopian novel', 2008, 'YOUNG_ADULTS', 'VERY_GOOD', '2023-05-15 18:10:00'),
(2, 'The Road', 'Cormac McCarthy', 150, 'Post-apocalyptic novel', 2006, 'ADULTS', 'NEW', '2023-05-20 21:30:00'),
(2, 'The Da Vinci Code', 'Dan Brown', 190, 'Mystery thriller', 2003, 'ADULTS', 'GOOD', '2023-05-25 09:45:00'),
(2, 'The Kite Runner', 'Khaled Hosseini', 220, 'Historical drama', 2003, 'ADULTS', 'LIKE_NEW', '2023-05-28 14:25:00'),
(2, 'Gone Girl', 'Gillian Flynn', 170, 'Mystery thriller', 2012, 'ADULTS', 'ACCEPTABLE', '2023-05-29 17:40:00'),
(2, 'The Silent Patient', 'Alex Michaelides', 200, 'Psychological thriller', 2019, 'ADULTS', 'VERY_GOOD', '2023-05-03 10:55:00'),
(2, 'The Goldfinch', 'Donna Tartt', 260, 'Coming-of-age novel', 2013, 'ADULTS', 'GOOD', '2023-05-08 22:15:00'),
(2, 'The Testaments', 'Margaret Atwood', 210, 'Dystopian novel', 2019, 'ADULTS', 'LIKE_NEW', '2023-05-16 19:50:00');


INSERT INTO bookadaisical.active_books (book_id)
VALUES
(6), (18), (2), (15), (7), (10), (12), (9), (11), (22), (16), (20), (3), (14), (21), (5), (8);

-- Reset the id value back to 1, when deleting all rows inside a table
-- SELECT setval(pg_get_serial_sequence('bookadaisical.books', 'id'), 1, false);
-- DELETE FROM bookadaisical.books;
