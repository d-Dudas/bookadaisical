CREATE SCHEMA bookadaisical;

CREATE TABLE bookadaisical.users (
	id serial PRIMARY KEY,
	username text NOT NULL UNIQUE,
	email text NOT NULL UNIQUE,
	password text NOT NULL,
	current_points int DEFAULT 0,
	total_points int DEFAULT 0,
	special_currency int DEFAULT 0
);

CREATE TABLE bookadaisical.admins (
	id serial PRIMARY KEY,
	user_id int REFERENCES bookadaisical.users(id)
);

CREATE TABLE bookadaisical.yet_to_be_validated_users (
	id serial PRIMARY KEY,
	user_id int REFERENCES bookadaisical.users(id)
);
-- DROP TYPE bookadaisical.target_audience_books;
CREATE TYPE bookadaisical.target_audience_books AS ENUM (
    'ALL',
    'CHILDREN',
    'YOUNG_ADULTS',
    'ADULTS'
);
-- DROP TYPE bookadaisical.artistic_movement_books;
CREATE TYPE bookadaisical.artistic_movement_books AS ENUM (
    'ALL',
    'ANCIENT_LITERATURE',
    'MEDIEVAL_LITERATURE',
    'BAROQUE',
    'HUMANISM',
    'CLASSICISM',
    'ENLIGHTENMENT',
    'REALISM',
    'NATURALISM',
    'IMPRESSIONISM',
    'POPORANISM',
    'PARNASSIANISM',
    'SYMBOLISM',
    'SAMANATORISM',
    'MODERNISM',
    'EXPRESSIONISM',
    'DADAISM',
    'INTEGRALISM',
    'CONSTRUCTIVISM',
    'SURREALISM',
    'LITERARY_MOVEMENTS_AFTER_1947',
    'PROLETKULT',
    'SOCIALIST_REALISM',
    'STRUCTURALISM',
    'THEATER_OF_THE_ABSURD',
    'POSTMODERNISM',
    'POST_STRUCTURALISM',
    'FEMINISM',
    'LITERARY_EIGHTIES',
    'HIMERISM',
    'FRACTURISM',
    'DEPRIMISME',
    'GENERATION_2000'
);
-- DROP TYPE bookadaisical.genres;
CREATE TYPE bookadaisical.genres AS ENUM (
    'ALL',
    'ALTERNATE_HISTORY',
    'ACADEMIC',
    'ADVENTURE',
    'BIBLIOGRAPHY',
    'COOKBOOK',
    'CRIME',
    'CLASSICS',
    'DARK',
    'DYSTOPIAN',
    'ENCYCLOPEDIC',
    'ESSAY',
    'FEMINIST',
    'FANTASY',
    'GOTHIC',
    'HISTORICAL',
    'HORROR',
    'INSPIRATIONAL',
    'JOURNALISTIC_WRITING',
    'MILITARY',
    'MANGA',
    'MYTHIC',
    'NOIR',
    'PHILOSOPHICAL',
    'PSYCHOLOGICAL',
    'POP_CULTURE',
    'POEMS',
    'REALIST',
    'RELIGIOUS',
    'ROMANTIC',
    'SATIRE',
    'SURREAL_COMEDY',
    'SCI_FI',
    'SCIENCE',
    'SELF_HELP',
    'SAGA',
    'TRAGICOMEDY',
    'TRAGEDY',
    'THRILLER',
    'URBAN',
    'UTOPIAN',
    'WESTERN'
);
-- DROP TYPE bookadaisical.condition_books;
CREATE TYPE bookadaisical.condition_books AS ENUM (
    'ALL',
    'NEW',
    'LIKE_NEW',
    'VERY_GOOD',
    'GOOD',
    'ACCEPTABLE'
);
-- DROP TYPE bookadaisical.trading_options;
CREATE TYPE bookadaisical.trading_options AS ENUM (
    'ALL',
    'CURRENCY',
    'POINTS',
    'SWAP'
);

CREATE TABLE bookadaisical.images (
    id serial PRIMARY KEY,
    image_data bytea
);

CREATE TABLE bookadaisical.books (
    id serial PRIMARY KEY,
    uploader int REFERENCES bookadaisical.users(id),
    title text NOT NULL,
    author text NOT NULL,
    num_views int,
    description text,
    created_on timestamp NOT NULL DEFAULT NOW(),
    last_modified timestamp,
    year_of_publication int,
    artistic_movement artistic_movement_books,
    target_audience bookadaisical.target_audience_books,
    book_condition bookadaisical.condition_books
);

CREATE TABLE bookadaisical.images_books (
    book_id int REFERENCES bookadaisical.books(id),
    image_id int REFERENCES bookadaisical.images(id),
    PRIMARY KEY (book_id, image_id)
);

CREATE TABLE bookadaisical.active_books (
    id serial PRIMARY KEY,
    book_id int REFERENCES bookadaisical.books(id)
);

CREATE TABLE bookadaisical.genres_books (
	book_id INT REFERENCES bookadaisical.books(id),
    genre_name bookadaisical.genres,
    PRIMARY KEY (book_id, genre_name)
);

CREATE TABLE bookadaisical.trading_options_books (
	book_id INT REFERENCES bookadaisical.books(id),
    trading_option bookadaisical.trading_options,
    PRIMARY KEY (book_id, trading_option)
);

CREATE TABLE bookadaisical.book_trades (
	id serial PRIMARY KEY,
	sender int REFERENCES bookadaisical.users(id),
	receiver int REFERENCES bookadaisical.users(id),
	book_id int REFERENCES bookadaisical.books(id),
	trade_timestamp timestamp NOT NULL DEFAULT NOW(),
	trading_option bookadaisical.trading_options_books
);

CREATE TABLE bookadaisical.articles (
	id serial PRIMARY KEY,
	author int REFERENCES bookadaisical.users(id),
	title text,
	num_views int,
	created_on timestamp NOT NULL DEFAULT NOW(),
	last_modified timestamp
);

CREATE TABLE bookadaisical.articles_books (
	article_id int REFERENCES bookadaisical.articles(id),
	book_id int REFERENCES bookadaisical.books(id),
    PRIMARY KEY (article_id, book_id)
);

CREATE TABLE bookadaisical.article_comments (
	id serial PRIMARY KEY,
	author int REFERENCES bookadaisical.users(id),
	article int REFERENCES bookadaisical.articles(id),
	created_on timestamp NOT NULL DEFAULT NOW(),
	last_modified timestamp
);

CREATE TABLE bookadaisical.points_transferred (
	id serial PRIMARY KEY,
	sender int REFERENCES bookadaisical.users(id),
	receiver int REFERENCES bookadaisical.users(id),
	amount int,
	sent_at timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE bookadaisical.chat (
	id serial PRIMARY KEY,
	sender int REFERENCES bookadaisical.users(id),
	receiver int REFERENCES bookadaisical.users(id),
	message text,
	sent_at timestamp NOT NULL DEFAULT NOW()
);

CREATE TABLE bookadaisical.reward_for_trading (
	trading_option bookadaisical.trading_options,
	amount int
);

CREATE TABLE bookadaisical.price_currency_books (
	book_id int PRIMARY KEY REFERENCES bookadaisical.books(id),
	amount float
);

CREATE TABLE bookadaisical.price_points_books (
	book_id int PRIMARY KEY REFERENCES bookadaisical.books(id),
	amount float
);

CREATE TABLE bookadaisical.login_tokens (
    id serial PRIMARY KEY,
    user_id int REFERENCES bookadaisical.users(id),
    token text,
    key text,
    last_validated_on timestamp NOT NULL DEFAULT NOW()
);

CREATE OR REPLACE FUNCTION delete_old_tokens()
  RETURNS void AS
$$
BEGIN
  DELETE FROM bookadaisical.login_tokens
  WHERE created_on <= NOW() - INTERVAL '30 days';
END;
$$
LANGUAGE plpgsql;

SELECT cron.schedule('0 0 * * *', 'SELECT delete_old_tokens()');
