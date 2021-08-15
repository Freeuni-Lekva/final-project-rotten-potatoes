-- change this line so that is uses your database
CREATE DATABASE rotten_potatoes;
USE rotten_potatoes;

-- remove a table if it already exists to begin from scratch



DROP TABLE IF EXISTS USER_BADGES;
DROP TABLE IF EXISTS BADGES;
DROP TABLE IF EXISTS FOLLOWERS;
DROP TABLE IF EXISTS MUSIC;
DROP TABLE IF EXISTS TV_SHOWS;
DROP TABLE IF EXISTS REVIEWS;
DROP TABLE IF EXISTS MOVIES;
DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS VIDEO_GAMES;
DROP TABLE IF EXISTS NOTIFICATIONS;
DROP TABLE IF EXISTS REPORTS;
DROP TABLE IF EXISTS NOTIFICATION_TYPES;
DROP TABLE IF EXISTS FOLLOWERS;
DROP TABLE IF EXISTS REVIEWS;
DROP TABLE IF EXISTS ITEMS;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS USERS;


CREATE TABLE CATEGORIES(
   category_name CHAR(25) NOT NULL, CONSTRAINT category_name_pk PRIMARY KEY (category_name) 
);

INSERT INTO CATEGORIES VALUES
   ('MUSIC'), ('VIDEO_GAMES'), ('BOOKS'), ('TV_SHOWS'), ('MOVIES');

CREATE TABLE USERS (
   username CHAR(20) NOT NULL, CONSTRAINT pk_username PRIMARY KEY (username), CONSTRAINT username_ck CHECK (username NOT LIKE '% %'),
   first_name CHAR(20) NOT NULL,
   last_name CHAR(30) NOT NULL,
   date_of_birth DATE NOT NULL,
   hash_password CHAR(255) NOT NULL
);

INSERT INTO USERS VALUES 
   ('admin', 'Giorgi', 'Meore', '1900-03-07', '70e55e37480aafbb55d697f1dc7dcfb6b70ca342'),
   ('tekla', 'Tekla', 'Basilidze', '2000-02-12', 'f9853a17b3f82c79c1843e7d69ff539d154d8e50'),
   ('sjanj19', 'Shorena', 'Janjghava', '2001-05-31', '70e55e37480aafbb55d697f1dc7dcfb6b70ca342');

CREATE TABLE ITEMS (
   item_id CHAR(100) NOT NULL, CONSTRAINT ck_item_id PRIMARY KEY (item_id),
   -- title column is neither primary key nor unique key, because for instance, books and movies can have same titles.
   title CHAR(100) NOT NULL,
   category CHAR(25) NOT NULL, CONSTRAINT category_fk FOREIGN KEY (category) REFERENCES CATEGORIES (category_name),
   uploader CHAR(50) NOT NULL, CONSTRAINT uploader_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck_ForItem CHECK (score BETWEEN 0 AND 10),
   cover_url TEXT NOT NULL, CONSTRAINT items_url CHECK (cover_url NOT LIKE '% %'),
   release_date YEAR NOT NULL,
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO ITEMS VALUES
   ('MU_Dangerous_1991', 'Dangerous', 'MUSIC', 'admin', 0, 'https://upload.wikimedia.org/wikipedia/en/1/11/Michaeljacksondangerous.jpg', 1991, 0),
   ('MU_Magical Mystery Tour_1967', 'Magical Mystery Tour', 'MUSIC', 'admin', 0, 'https://upload.wikimedia.org/wikipedia/en/thumb/e/e8/MagicalMysteryTourDoubleEPcover.jpg/220px-MagicalMysteryTourDoubleEPcover.jpg', 1967, 0),
   ('TV_One Tree Hill_2003', 'One Tree Hill', 'TV_SHOWS', 'admin', 0, 'https://m.media-amazon.com/images/M/MV5BN2Y0NzRjOTQtNGNmYS00NjNiLWIwY2MtZWIyZDI5ZDhhZmI1XkEyXkFqcGdeQXVyODIxOTMwMjk@._V1_.jpg', 2003, 0),
   ('TV_Friends_1994', 'Friends', 'TV_SHOWS', 'admin', 0, 'https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_.jpg', 1994, 0),
   ('BO_Bill Bergson, Master Detective_1946', 'Bill Bergson, Master Detective', 'BOOKS', 'admin', 0, 'https://upload.wikimedia.org/wikipedia/en/9/9a/M%C3%A4sterdetektivenBlomkvistLeverFarligt.jpg', 1946, 0),
   ('MO_Love, Rosie_2014', 'Love, Rosie', 'MOVIES', 'admin', 0, 'https://pbs.twimg.com/media/Cc38VzZWAAIpd08.jpg', 2014, 0),
   ('MO_The Grand Budapest Hotel_2014', 'The Grand Budapest Hotel', 'MOVIES', 'admin', 0, 'https://m.media-amazon.com/images/M/MV5BMzM5NjUxOTEyMl5BMl5BanBnXkFtZTgwNjEyMDM0MDE@._V1_.jpg', 2014, 0),
   ('VI_Minecraft_2011', 'Minecraft', 'VIDEO_GAMES', 'admin', 0, 'https://images-na.ssl-images-amazon.com/images/I/418cEZfh8-L.jpg', 2011, 0),
   ('VI_Stardew Valley_2016', 'Stardew Valley', 'VIDEO_GAMES', 'admin', 0, 'https://www.researchgate.net/publication/342704239/figure/fig1/AS:960471637192707@1606005691630/Stardew-Valley-promotional-image-Sourcewwwstardewvalleynet-Image-copyright-Eric-Barone.jpg', 2016, 0),
   ('MO_Cruella_2021', 'Cruella', 'MOVIES', 'sjanj19', 0, 'https://m.media-amazon.com/images/M/MV5BNDUzMjhhMmUtZTYwZS00MWViLWI1NTctNjNkNTc3NDAxYTM2XkEyXkFqcGdeQXVyMTA3MDk2NDg2._V1_.jpg', 2021, 0),
   ('MO_Van Helsing_2004', 'Van Helsing', 'MOVIES', 'sjanj19', 0, 'https://static.wikia.nocookie.net/4eee0092-1689-4cbe-8dfa-33bf3678481d', 2004, 0),
   ('MO_Get Out_2017', 'Get Out', 'MOVIES', 'sjanj19', 0, 'https://i.pinimg.com/originals/31/91/e0/3191e01e8ede50a2fc85d4bfb20d239d.jpg', 2017, 0),
   ('MO_Kill Bill: Vol. 1_2003', 'Kill Bill: Vol. 1', 'MOVIES', 'sjanj19', 0, 'https://flxt.tmsimg.com/assets/p32988_p_v10_ae.jpg', 2003, 0),
   ('TV_Gravity Falls_2012', 'Gravity Falls', 'TV_SHOWS', 'sjanj19', 0, 'https://lumiere-a.akamaihd.net/v1/images/open-uri20150422-12561-18wj036_1d2d1ae5_239838e4.jpeg', 2012, 0),
   ('MU_Lemonade_2016', 'Lemonade', 'MUSIC', 'sjanj19', 0, 'https://pyxis.nymag.com/v1/imgs/245/4b9/b4496eda47e6c7c641cc7fa774498cab82-25-beyonce-lemonade-cover.rsquare.w700.jpg', 2016, 0),
   ('MU_Thriller_1982', 'Thriller', 'MUSIC', 'sjanj19', 0, 'https://vinyl-records.nl/pop/photo-gallery/michael-jackson/A0804-MICHAEL-JACKSON-Thriller-Large.jpg', 1982, 0),
   ('VI_Tomb Raider_2013', 'Tomb Raider', 'VIDEO_GAMES', 'sjanj19', 0, 'https://image.api.playstation.com/cdn/UP0082/CUSA00107_00/jT7kkwSWEinafnaYWMZoReRr4LlmYmym.png', 2013, 0),
   ('VI_Cyberpunk 2077_2020', 'Cyberpunk 2077', 'VIDEO_GAMES', 'sjanj19', 0, 'https://images-na.ssl-images-amazon.com/images/I/91Nw-OlqDIL.jpg', 2020, 0),
   ('VI_Batman: Arkham City_2011', 'Batman: Arkham City', 'VIDEO_GAMES', 'sjanj19', 0, 'https://image.api.playstation.com/cdn/UP1018/NPUB30754_00/1tKIV7dIntxi6laeiSH9ffzTFmOHObKa.png?w=960&h=960', 2011, 0),
   ('BO_The Magic Mountain_1924', 'The Magic Mountain', 'BOOKS', 'sjanj19', 0, 'https://images-na.ssl-images-amazon.com/images/I/81hJb7YycbL.jpg', 1924, 0),
   ('TV_Game of Thrones_2011', 'Game of Thrones', 'TV_SHOWS', 'sjanj19', 0, 'https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg', 2011, 0);

CREATE TABLE BADGES (
   badge_id CHAR(6) NOT NULL, CONSTRAINT pk_badge_id PRIMARY KEY (badge_id),
   badge_name CHAR(20) NOT NULL,
   badge_icon CHAR(28) NOT NULL,
   badge_description CHAR(64) NOT NULL
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
   username CHAR(20) NOT NULL, CONSTRAINT username_fk2 FOREIGN KEY (username) REFERENCES USERS (username),
   badge_id CHAR(6) NOT NULL, CONSTRAINT badge_id_fk FOREIGN KEY (badge_id) REFERENCES BADGES (badge_id)
);

CREATE TABLE FOLLOWERS (
   user_username CHAR(20) NOT NULL, CONSTRAINT user_username_fk FOREIGN KEY (user_username) REFERENCES USERS (username),
   follower_username CHAR(20) NOT NULL, CONSTRAINT follower_username_fk FOREIGN KEY (follower_username) REFERENCES USERS (username)
);

CREATE TABLE MUSIC (
   music_id CHAR(100) NOT NULL, CONSTRAINT music_id_fk FOREIGN KEY (music_id) REFERENCES ITEMS (item_id),
   artist CHAR(100) NOT NULL,
   title CHAR(100) NOT NULL,
   label CHAR(50) NOT NULL,
   release_year YEAR NOT NULL,
   genre CHAR(100) NOT NULL,
   album_cover_url TEXT NOT NULL, CONSTRAINT music_url CHECK (album_cover_url NOT LIKE '% %'),
   uploader CHAR(20) NOT NULL, CONSTRAINT music_uploader_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck_score_music CHECK (score BETWEEN 0 AND 10),
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO MUSIC VALUES
   ('MU_Dangerous_1991', 'Michael Jackson', 'Dangerous', 'Epic Records', 1991, 'New Jack Swing, R&B, Pop', 'https://upload.wikimedia.org/wikipedia/en/1/11/Michaeljacksondangerous.jpg', 'admin', 0, 0),
   ('MU_Magical Mystery Tour_1967', 'The Beatles', 'Magical Mystery Tour', 'Parlophone, Capitol Records', 1967, 'Rock, Psychedelia', 'https://upload.wikimedia.org/wikipedia/en/thumb/e/e8/MagicalMysteryTourDoubleEPcover.jpg/220px-MagicalMysteryTourDoubleEPcover.jpg', 'admin', 0, 0),
   ('MU_Lemonade_2016', 'Beyoncé', 'Lemonade', 'Parkwood, Columbia', 2016, 'R&B, Art Pop', 'https://pyxis.nymag.com/v1/imgs/245/4b9/b4496eda47e6c7c641cc7fa774498cab82-25-beyonce-lemonade-cover.rsquare.w700.jpg', 'sjanj19', 0, 0),
   ('MU_Thriller_1982', 'Michael Jackson', 'Thriller', 'Epic Records', 1982, 'Pop, Post-Disco, Pop-Soul, R&B, Rock, Funk', 'https://vinyl-records.nl/pop/photo-gallery/michael-jackson/A0804-MICHAEL-JACKSON-Thriller-Large.jpg', 'sjanj19', 0, 0);
   
CREATE TABLE TV_SHOWS (
   tv_show_id CHAR(100) NOT NULL, CONSTRAINT tv_shows_id_fk FOREIGN KEY (tv_show_id) REFERENCES ITEMS (item_id),
   title CHAR(100) NOT NULL,
   airing_year YEAR NOT NULL,
   director CHAR(60) NOT NULL,
   tv_show_cast CHAR(100) NOT NULL,
   cover_url TEXT NOT NULL, CONSTRAINT tv_shows_url CHECK (cover_url NOT LIKE '% %'),
   summary TEXT NOT NULL,
   uploader CHAR(20) NOT NULL, CONSTRAINT tv_show_uploader_fk FOREIGN KEY (uploader) REFERENCES USERS(username),
   score DOUBLE NOT NULL, CONSTRAINT ck_score_tv_shows CHECK (score BETWEEN 0 AND 10),
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO TV_SHOWS VALUES
   ('TV_One Tree Hill_2003', 'One Tree Hill', 2003, 'Mark Schwahn', 
    'James Lafferty, Sophia Bush, Bethany Joy Lenz, Chad Michael Murray', 
    'https://m.media-amazon.com/images/M/MV5BN2Y0NzRjOTQtNGNmYS00NjNiLWIwY2MtZWIyZDI5ZDhhZmI1XkEyXkFqcGdeQXVyODIxOTMwMjk@._V1_.jpg',
    'Half-brothers - Lucas and Nathan Scott - trade between kinship and rivalry both on the basketball court and in the hearts of their friends in the small, but not so quiet town of Tree Hill, North Carolina.', 'admin', 0, 0),
   ('TV_Friends_1994', 'Friends', 1994, 'David Crane, Marta Kauffman',
    'Matthew Perry, Courteney Cox, Matt LeBlanc, David Schwimmer, Lisa Kudrow, Jennifer Aniston', 
    'https://m.media-amazon.com/images/M/MV5BNDVkYjU0MzctMWRmZi00NTkxLTgwZWEtOWVhYjZlYjllYmU4XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_.jpg',
    'A show about six friends in New York as they navigate their way through life and learn to grow up as they approach the third decade of their life. All, with 
    the help from each other to get them through the obstacles that life naturally has for us.', 'admin', 0, 0),
    ('TV_Gravity Falls_2012', 'Gravity Falls', 2012, 'Alex Hirsch', 'Jason Ritter, Alex Hirsch, Kristen Schaal, Linda Cardellini',
    'https://lumiere-a.akamaihd.net/v1/images/open-uri20150422-12561-18wj036_1d2d1ae5_239838e4.jpeg',
    'The series follows the adventures of Dipper Pines (voiced by Jason Ritter) and his twin sister Mabel (voiced by Kristen Schaal) who are sent to spend the summer with their great-uncle 
    (or "Grunkle") Stan (voiced by Hirsch) in Gravity Falls, Oregon, a mysterious town full of paranormal incidents and supernatural...', 'sjanj19', 0, 0),
    ('TV_Game of Thrones_2011', 'Game of Thrones', 2011, 'Alan Taylor, Mark Mylod, Alex Graves, Jeremy Podeswa', 'Peter Dinklage, Lena Headey, Emilia Clarke, Kit Harington, Nikolaj Coster-Waldau',
     'https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg', 'In the Game of Thrones, you either win or you die. Nine noble families fight for control of 
     the mythical land of Westeros. Political and sexual intrigue is pervasive. Robert Baratheon (Mark Addy), King of Westeros, asks his old friend, Lord Eddard Stark (Sean Bean), to serve as Hand of the King, or highest official.',
     'sjanj19', 0, 0);

CREATE TABLE REVIEWS (
   item_id CHAR(100) NOT NULL, CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES ITEMS (item_id),
   username CHAR(20) NOT NULL, CONSTRAINT username_fk FOREIGN KEY (username) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck_score_in_review CHECK (score BETWEEN 0 AND 10),
   review TEXT NOT NULL,
   category CHAR(15) NOT NULL
);

CREATE TABLE MOVIES (
   movie_id CHAR(100) NOT NULL, CONSTRAINT movie_id_fk FOREIGN KEY (movie_id) REFERENCES ITEMS (item_id),
   title CHAR(100) NOT NULL,
   release_date YEAR NOT NULL,
   director CHAR(60) NOT NULL,
   movie_cast CHAR(100) NOT NULL,
   cover_url TEXT NOT NULL, CONSTRAINT movies_url CHECK (cover_url NOT LIKE '% %'),
   summary TEXT NOT NULL,
   uploader CHAR(20) NOT NULL, CONSTRAINT movies_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck2_score_books CHECK (score BETWEEN 0 AND 10),
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO MOVIES VALUES
   ('MO_Love, Rosie_2014', 'Love, Rosie', 2014, 'Christian Ditter', 'Sam Claflin, Lily Collins, Christian Cooke',
    'https://pbs.twimg.com/media/Cc38VzZWAAIpd08.jpg', 
    'Rosie and Alex have been best friends since they were 5, so they could not possibly be right for one another... or could they? When it comes to love, life and making the right choices, these two are their own worst enemies.', 'admin', 0, 0),
   ('MO_The Grand Budapest Hotel_2014', 'The Grand Budapest Hotel', 2014, 'Wes Anderson', 
    'Ralph Fiennes, Saoirse Ronan, Adrien Brody, Tilda Swinton', 
    'https://m.media-amazon.com/images/M/MV5BMzM5NjUxOTEyMl5BMl5BanBnXkFtZTgwNjEyMDM0MDE@._V1_.jpg',
    'A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel\'s glorious years under an exceptional concierge.', 'admin', 0, 0),
    ('MO_Cruella_2021', 'Cruella', 2021, 'Craig Gillespie', 'Emma Stone, Emma Thompson, Joel Fry, Paul Walter Hauser', 'https://m.media-amazon.com/images/I/71LD6AGowjL._AC_SL1100_.jpg', 'Estella is a young and clever grifter who''s determined 
	to make a name for herself in the fashion world. She soon meets a pair of thieves who appreciate her appetite for mischief, and together they build a life for themselves on the streets of London. 
    However, when Estella befriends fashion legend Baroness von Hellman, she embraces her wicked side to become the raucous and revenge-bent Cruella.', 'sjanj19', 0, 0),
    ('MO_Van Helsing_2004', 'Van Helsing', 2004, 'Stephen Sommers', 'Hugh Jackman, Kate Beckinsale, Richard Roxburgh, David Wenham', 'https://static.wikia.nocookie.net/4eee0092-1689-4cbe-8dfa-33bf3678481d',
    'Famed monster slayer Gabriel Van Helsing (Hugh Jackman) is dispatched to Transylvania to assist the last of the Valerious bloodline in defeating Count Dracula (Richard Roxburgh). Anna Valerious (Kate Beckinsale) reveals 
    that Dracula has formed an unholy alliance with Dr. Frankenstein''s monster (Shuler Hensley) and is hell-bent on exacting a centuries-old curse on her family. Together Anna and Van Helsing set out to destroy their common 
    enemy, but uncover some unsettling secrets along the way.', 'sjanj19', 0, 0),
    ('MO_Get Out_2017', 'Get Out', 2017, 'Jordan Peele', 'Daniel Kaluuya, Allison Williams, Bradley Whitford, Catherine Keener, Caleb Landry Jones', 'https://i.pinimg.com/originals/31/91/e0/3191e01e8ede50a2fc85d4bfb20d239d.jpg',
    'Now that Chris and his girlfriend, Rose, have reached the meet-the-parents milestone of dating, she invites him for a weekend getaway upstate with her parents, Missy and Dean. At first, 
    Chris reads the family''s overly accommodating behaviour as nervous attempts to deal with their daughter''s interracial relationship, but as the weekend progresses, a series of increasingly 
    disturbing discoveries leads him to a truth that he never could have imagined.', 'sjanj19', 0, 0),
    ('MO_Kill Bill: Vol. 1_2003', 'Kill Bill: Vol. 1', 2003, 'Quentin Tarantino', 'Uma Thurman, David Carradine, Daryl Hannah, Michael Madsen, Lucy Liu, Vivica A. Fox, Julie Dreyfus',
    'https://flxt.tmsimg.com/assets/p32988_p_v10_ae.jpg', 'A former assassin, known simply as The Bride (Uma Thurman), wakes from a coma four years after her jealous ex-lover Bill 
    (David Carradine) attempts to murder her on her wedding day. Fueled by an insatiable desire for revenge, she vows to get even with every person who contributed to the loss of her unborn child, 
    her entire wedding party, and four years of her life. After devising a hit list, The Bride sets off on her quest, enduring unspeakable injury and unscrupulous enemies.', 'sjanj19', 0, 0);

CREATE TABLE BOOKS (
   book_id CHAR(100) NOT NULL, CONSTRAINT book_id_fk FOREIGN KEY (book_id) REFERENCES ITEMS (item_id),
   title CHAR(100) NOT NULL,
   release_date YEAR NOT NULL,
   writer CHAR(60) NOT NULL,
   cover_url TEXT NOT NULL, CONSTRAINT books_url CHECK (cover_url NOT LIKE '% %'),
   summary TEXT NOT NULL,
   uploader CHAR(20) NOT NULL, CONSTRAINT books_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck_score_books CHECK (score BETWEEN 0 AND 10),
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO BOOKS VALUES
   ('BO_Bill Bergson, Master Detective_1946', 'Bill Bergson, Master Detective', 1946, 'Astrid Lindgren', 
    'https://upload.wikimedia.org/wikipedia/en/9/9a/M%C3%A4sterdetektivenBlomkvistLeverFarligt.jpg', 
    'It is the first in the series about the Swedish boy detective.', 'admin', 0, 0),
   ('BO_The Magic Mountain_1924', 'The Magic Mountain', 1924, 'Thomas Mann', 'https://images-na.ssl-images-amazon.com/images/I/81hJb7YycbL.jpg', 'The Magic Mountain tells the story of Hans Castorp, a young German engineer, 
    who goes to visit a cousin in a tuberculosis sanatorium in the mountains of Davos, Switz.', 'sjanj19', 0, 0);

CREATE TABLE VIDEO_GAMES (
   video_game_id CHAR(100) NOT NULL, CONSTRAINT video_game_id_fk FOREIGN KEY (video_game_id) REFERENCES ITEMS (item_id),
   title CHAR(100) NOT NULL,
   release_date YEAR NOT NULL,
   developers CHAR(100) NOT NULL,
   cover_url TEXT NOT NULL, CONSTRAINT video_games_url CHECK (cover_url NOT LIKE '% %'),
   summary TEXT NOT NULL,
   uploader CHAR(20) NOT NULL, CONSTRAINT video_games_author_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
   score DOUBLE NOT NULL, CONSTRAINT ck_score_vide_games CHECK (score BETWEEN 0 AND 10),
   num_of_reviews INTEGER NOT NULL
);

INSERT INTO VIDEO_GAMES VALUES
   ('VI_Minecraft_2011', 'Minecraft', 2011, 'Mojang Studios, Other Ocean Interactive, 4J Studios, Xbox Game Studios',
    'https://images-na.ssl-images-amazon.com/images/I/418cEZfh8-L.jpg', 'Minecraft is a video game in which players create and break apart various kinds of blocks in three-dimensional worlds. The game\'s two main modes are Survival and Creative. In Survival, players must find their own building supplies and food. They also interact with blocklike mobs, or moving creatures.', 'admin', 0, 0),
   ('VI_Stardew Valley_2016', 'Stardew Valley', 2016, 'Eric Barone, Sickhead Games, ConcernedApe', 
	'https://www.researchgate.net/publication/342704239/figure/fig1/AS:960471637192707@1606005691630/Stardew-Valley-promotional-image-Sourcewwwstardewvalleynet-Image-copyright-Eric-Barone.jpg',
    'Stardew Valley is a farming simulation game primarily inspired by the Harvest Moon video game series. At the start of the game, players create a character, who becomes the recipient of a 
    plot of land and a small house once owned by their grandfather in a small town called Pelican Town.', 'admin', 0, 0),
    ('VI_Tomb Raider_2013', 'Tomb Raider', 2013, 'Crystal Dynamics, Eidos-Montréal, Nixxes Software BV, Feral Interactive', 'https://image.api.playstation.com/cdn/UP0082/CUSA00107_00/jT7kkwSWEinafnaYWMZoReRr4LlmYmym.png',
    'A new intense and gritty origin story of Lara Croft and her ascent from a frightened young woman to a hardened survivor. Armed only with raw instincts and the ability to push beyond the limits of human endurance, Lara 
    must fight to unravel the dark history of a forgotten island to escape its relentless hold.', 'sjanj19', 0, 0),
    ('VI_Cyberpunk 2077_2020', 'Cyberpunk 2077', 2020, 'CD Projekt, CD Projekt RED', 'https://images-na.ssl-images-amazon.com/images/I/91Nw-OlqDIL.jpg', 'Cyberpunk 2077 is an open-world, action-adventure story set in Night City, a megalopolis obsessed with power, glamour and body modification. You can customize your character''s 
    cyberware, skillset and playstyle, and explore a vast city where the choices you make shape the story and the world around you.', 'sjanj19', 0, 0),
    ('VI_Batman: Arkham City_2011', 'Batman: Arkham City', 2011, 'Rocksteady Studios, WB Games Montréal', 'https://image.api.playstation.com/cdn/UP1018/NPUB30754_00/1tKIV7dIntxi6laeiSH9ffzTFmOHObKa.png?w=960&h=960',
    'Bruce Wayne is kidnapped whilst protesting the use of Arkham City, a dilapidated part of Gotham that is used to house the resident of Arkham Asylum. ... After the prison, ran by the mad psychiatrist Hugo Strange, 
    becomes a war zone between his greatest enemies, Bruce must stop everyone who stands in his way as the Batman.', 'sjanj19', 0, 0);

CREATE TABLE REPORTS (
	reportID int NOT NULL AUTO_INCREMENT, CONSTRAINT pk_reportID PRIMARY KEY (reportID),
    reporter_username CHAR(20) NOT NULL, CONSTRAINT reporter_fk FOREIGN KEY (reporter_username) REFERENCES USERS (username),
    item_id CHAR(100) NOT NULL, CONSTRAINT item_id_fk2 FOREIGN KEY (item_id) REFERENCES ITEMS (item_id),
    category CHAR(25) NOT NULL, CONSTRAINT category_fk2 FOREIGN KEY (category) REFERENCES CATEGORIES (category_name),
    title CHAR(100) NOT NULL,
    release_date YEAR NOT NULL,
    url TEXT NOT NULL, CONSTRAINT url CHECK (url NOT LIKE '%  %'),
    producer CHAR(100) NOT NULL,
    info TEXT NOT NULL,
    members CHAR(100) ,
    user_comment TEXT
);

INSERT INTO REPORTS VALUES 
  (NULL, 'tekla', 'MO_Love, Rosie_2014', 'MOVIES', 'Love, Rosie', 2012, 'https://pbs.twimg.com/media/Cc38VzZWAAIpd08.jpg',
  'Christian Ditter', 'Rosie and Alex have been best friends since they were 5, so they could not possibly be right for one another... or could they? When it comes to love, life and making the right choices, these two are their own worst enemies.', 
  'Sam Claflin, Lily Collins, Christian Cooke','');
  
CREATE TABLE NOTIFICATION_TYPES (
	notification_type CHAR(30) NOT NULL, CONSTRAINT notification_type_pk PRIMARY KEY (notification_type) 
);

INSERT INTO NOTIFICATION_TYPES VALUES
   ('REVIEW'), ('REQUEST'), ('UPLOAD'), ('FOLLOW');
   
CREATE TABLE NOTIFICATIONS (
	notificationID int NOT NULL AUTO_INCREMENT, CONSTRAINT pk_notificationID PRIMARY KEY (notificationID),
    receiver_username CHAR(20) NOT NULL, CONSTRAINT receiver_username_fk FOREIGN KEY (receiver_username) REFERENCES USERS (username),
    sender_username CHAR(20) NOT NULL, CONSTRAINT sender_username_fk FOREIGN KEY (sender_username) REFERENCES USERS (username),
    item_id CHAR(100), CONSTRAINT item_id_fk3 FOREIGN KEY (item_id) REFERENCES ITEMS (item_id),
    notification_type CHAR(30) NOT NULL, CONSTRAINT notification_type_fk FOREIGN KEY (notification_type) REFERENCES NOTIFICATION_TYPES (notification_type)
);

