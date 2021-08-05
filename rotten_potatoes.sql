-- change this line so that is uses your database
USE rotten_potatoes;

-- remove a table if it already exists to begin from scratch
DROP TABLE IF EXISTS USER_BADGES;
DROP TABLE IF EXISTS BADGES;
DROP TABLE IF EXISTS FOLLOWERS;
DROP TABLE IF EXISTS MOVIES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS VIDEO_GAMES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ITEMS;
DROP TABLE IF EXISTS CATEGORIES;


CREATE TABLE CATEGORIES(
		category_name CHAR(25), CONSTRAINT category_name_pk PRIMARY KEY (category_name)
);


CREATE TABLE USERS (
	username CHAR(20), CONSTRAINT pk_username PRIMARY KEY (username),
    first_name CHAR(20),
    last_name CHAR(30),
    date_of_birth DATE,
    hash_password CHAR(32)
);



CREATE TABLE ITEMS (
	item_id CHAR(100), CONSTRAINT ck_item_id PRIMARY KEY (item_id),
    category CHAR(25), CONSTRAINT category_fk FOREIGN KEY (category) REFERENCES CATEGORIES (category_name),
    uploader CHAR(50), CONSTRAINT uploader_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
    score DOUBLE, CONSTRAINT ck_ForItem CHECK (score BETWEEN 0 AND 10),
    cover_url TEXT 
);


INSERT INTO CATEGORIES VALUES
('MUSIC'), ('VIDEO GAMES'), ('BOOKS'), ('TV SHOWS'), ('MOVIES'); 





CREATE TABLE BADGES (
	badge_id CHAR(6), CONSTRAINT pk_badge_id PRIMARY KEY (badge_id),
	badge_name CHAR(20),
    badge_icon CHAR(28),
    badge_description CHAR(64)
);

INSERT INTO BADGES VALUES
	-- badges are given so easily for the sake of testing their work ability
	('AM_CRT', 'Amateur Critic', 'Amateur_Critic.png', 'Badge is given when the user has given a single review.'),
    ('AM_FAN', 'Amateur Fan', 'Amateur_Fan.png', 'Badge is given when the user has uploaded a single item.'),
    ('EX_CRT', 'Experienced Critic', 'Experienced_Critic.png', 'Badge is given when the user has given more than 5 reviews.'),
    ('EX_FAN', 'Experienced Fan', 'Experienced_Fan.png', 'Badge is given when the user has uploaded more than 5 items.'),
    ('TP_CRT', 'Top Critic', 'Top_Critic.png', 'Badge is given when the user has given more than 10 reviews.'),
    ('TP_FAN', 'Top Fan', 'Top_Fan.png', 'Badge is given when the user has uploaded more than 10 items.');

CREATE TABLE USER_BADGES (
	username CHAR(20), CONSTRAINT username_fk FOREIGN KEY (username) REFERENCES USERS (username),
    badge_id CHAR(6), CONSTRAINT badge_id_fk FOREIGN KEY (badge_id) REFERENCES BADGES (badge_id)
);

CREATE TABLE FOLLOWERS (
	user_username CHAR(20), CONSTRAINT user_username_fk FOREIGN KEY (user_username) REFERENCES USERS (username),
    follower_username CHAR(20), CONSTRAINT follower_username_fk FOREIGN KEY (follower_username) REFERENCES USERS (username)
);

CREATE TABLE MOVIES (
	movie_id CHAR(100), CONSTRAINT movie_id_fk FOREIGN KEY (movie_id) REFERENCES ITEMS (item_id),
	title CHAR(100),
    release_date YEAR,
    director CHAR(60),
  	movie_cast CHAR(100),
  	cover_url TEXT,
  	summary TEXT,
  	uploader CHAR(20), CONSTRAINT movies_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
  	score DOUBLE, CONSTRAINT ck_score_books CHECK (score BETWEEN 0 AND 10),
  	num_of_reviews INTEGER
);

INSERT INTO MOVIES VALUES
	('MO_Love, Rosie_2014', 'Love, Rosie', 2014, 'Christian Ditter', 'Sam Claflin, Lily Collins, Christian Cooke',
    	'https://pics.filmaffinity.com/Love_Rosie-612339378-large.jpg', 'Rosie and Alex have been best friends since they were 5,
     	so they could not possibly be right for one another...or could they? When it comes to love, life and making the right choices, 
     	these two are their own worst enemies.', 'admin', 0, 0),
    ('MO_The Grand Budapest Hotel_2014', 'The Grand Budapest Hotel', 2014, 'Wes Anderson', 'Ralph Fiennes, Saoirse Ronan,
     	Adrien Brody, Tilda Swinton', 'https://m.media-amazon.com/images/M/MV5BMzM5NjUxOTEyMl5BMl5BanBnXkFtZTgwNjEyMDM0MDE@._V1_.jpg',
    	'A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the 
     	hotel\'s glorious years under an exceptional concierge.', 'admin', 0, 0);


CREATE TABLE BOOKS (
	book_id CHAR(100), CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES ITEMS (item_id),
	title CHAR(100),
    release_date YEAR,
    writer CHAR(60),
  	cover_url TEXT,
  	summary TEXT,
  	uploader CHAR(20), CONSTRAINT books_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
  	score DOUBLE, CONSTRAINT ck_score_books CHECK (score BETWEEN 0 AND 10),
  	num_of_reviews INTEGER
);

INSERT INTO BOOKS VALUES
	('BO_Bill Bergson, Master Detective_1946', 'Bill Bergson, Master Detective', 1946, 'Astrid Lindgren', 
    	'https://upload.wikimedia.org/wikipedia/en/9/9a/M%C3%A4sterdetektivenBlomkvistLeverFarligt.jpg', 
    	'It is the first in the series about the Swedish boy detective.', 'admin', 0, 0),
	('BO_Colorless Tsukuru Tazaki and His Years of Pilgrimage_2013', 'Colorless Tsukuru Tazaki and His Years of Pilgrimage', 2013,
    	'Haruki Murakami', 'https://images-na.ssl-images-amazon.com/images/I/41OtORHHW4L._SX323_BO1,204,203,200_.jpg',
    	'In the novel "Colorless Tsukuru Tazaki and His Years of Pilgrimage" Murakami has created a character that is trying to make 
     	order in a disorderly world. Tsukuru is a man who does not recognize his own complexity, and who finds himself adrift after 
     	an unexplained rejection by his four colorful friends.', 'admin', 0, 0);

CREATE TABLE VIDEO_GAMES (
	video_game_id CHAR(100), CONSTRAINT video_game_id_fk FOREIGN KEY (video_game_id) REFERENCES ITEMS (item_id),
	title CHAR(100),
    release_date YEAR,
    developers CHAR(100),
  	cover_url TEXT,
  	summary TEXT,
  	uploader CHAR(20), CONSTRAINT video_games_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
  	score DOUBLE, CONSTRAINT ck_score_vide_games CHECK (score BETWEEN 0 AND 10),
  	num_of_reviews INTEGER
);

INSERT INTO VIDEO_GAMES VALUES
	('VI_Minecraft_2011', 'Minecraft', 2011, 'Mojang Studios, Other Ocean Interactive, 4J Studios, Xbox Game Studios',
    	'https://images-na.ssl-images-amazon.com/images/I/418cEZfh8-L.jpg', 'Minecraft is a video game in which players
     	create and break apart various kinds of blocks in three-dimensional worlds. The game\'s two main modes are Survival 
     	and Creative. In Survival, players must find their own building supplies and food. They also interact with blocklike
     	mobs, or moving creatures.', 'admin', 0, 0),
    ('VI_Stardew Valley_2016', 'Stardew Valley', 2016, 'Eric Barone, Sickhead Games, ConcernedApe', 
    	'https://www.researchgate.net/publication/342704239/figure/fig1/AS:960471637192707@1606005691630/Stardew-Valley-promotional-image-Sourcewwwstardewvalleynet-Image-copyright-Eric-Barone.jpg',
    	'Stardew Valley is a farming simulation game primarily inspired by the Harvest Moon video game series. At the start of the game, 
     	players create a character, who becomes the recipient of a plot of land and a small house once owned 
     	by their grandfather in a small town called Pelican Town.', 'admin', 0, 0);





CREATE TABLE ITEMS (
	item_id CHAR(100), CONSTRAINT ck_item_id PRIMARY KEY (item_id),
    category CHAR(25), CONSTRAINT category_fk FOREIGN KEY (category) REFERENCES CATEGORIES (category_name),
    uploader CHAR(50), CONSTRAINT uploader_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
    score DOUBLE, CONSTRAINT ck_ForItem CHECK (score BETWEEN 0 AND 10),
    cover_url TEXT 
);

CREATE TABLE CATEGORIES(
		category_name CHAR(25), CONSTRAINT category_name_pk PRIMARY KEY (category_name)
);

INSERT INTO CATEGORIES VALUES

('MUSIC'), ('VIDEO GAMES'), ('BOOKS'), ('TV SHOWS'), ('MOVIES'); 

