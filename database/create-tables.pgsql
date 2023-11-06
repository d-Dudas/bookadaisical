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

CREATE TYPE bookadaisical.target_audience_books AS ENUM ('Children’s', 'Young Adult', 'Adult');

CREATE TYPE bookadaisical.artistic_movement_books AS ENUM (
    'Ancient Literature',
    'Medieval Literature',
    'Baroque',
    'Humanism',
    'Classicism',
    'Enlightenment',
    'Realism',
    'Naturalism',
    'Impressionism',
    'Poporanism',
    'Parnassianism',
    'Symbolism',
    'Sămănătorism',
    'Modernism',
    'Expressionism',
    'Dadaism',
    'Integralism',
    'Constructivism',
    'Surrealism',
    'Literary Movements after 1947',
    'Proletkult',
    'Socialist Realism',
    'Structuralism',
    'Theater of the Absurd',
    'Postmodernism',
    'Post-structuralism',
    'Feminism',
    'Literary Eighties (1980s)',
    'Himerism',
    'Fracturism',
    'Déprimisme',
    'Generation 2000'
);

CREATE TYPE bookadaisical.genres AS ENUM (
    'Alternate history',
    'Academic',
    'Adventure',
    'Bibliography',
    'Cookbook',
    'Crime',
    'Classics',
    'Dark',
    'Dystopian',
    'Encyclopedic',
    'Essay',
    'Feminist',
    'Fantasy',
    'Gothic',
    'Historical',
    'Horror',
    'Inspirational',
    'Journalistic writing',
    'Military',
    'Manga',
    'Mythic',
    'Noir',
    'Philosophical',
    'Psychological',
    'Pop culture',
    'Poems',
    'Realist',
    'Religious',
    'Romantic',
    'Satire',
    'Surreal comedy',
    'Sci-fi',
    'Science',
    'Self-help',
    'Saga',
    'Tragicomedy',
    'Tragedy',
    'Thriller',
    'Urban',
    'Utopian',
    'Western'
);

CREATE TYPE bookadaisical.condition_books AS ENUM (
    'new',
    'like new',
    'very good',
    'good',
    'acceptable'
);
	
CREATE TYPE bookadaisical.trading_options AS ENUM (
    'currency',
    'points',
    'swap'
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