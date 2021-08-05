-- change this line so that is uses your database
USE rotten_potatoes;

-- remove a table if it already exists to begin from scratch
DROP TABLE IF EXISTS BADGES;
DROP TABLE IF EXISTS USER_BADGES;
DROP TABLE IF EXISTS FOLLOWERS;
DROP TABLE IF EXISTS MUSIC;
DROP TABLE IF EXISTS TV_SHOWS;
DROP TABLE IF EXISTS REVIEWS;

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
	user_username CHAR(20), CONSTRAINT user_username_fk FOREIGN KEY (username) REFERENCES USERS (username),
    follower_username CHAR(20), CONSTRAINT follower_username_fk FOREIGN KEY (follower_username) REFERENCES USERS (username)
);

CREATE TABLE MUSIC (
    music_id CHAR(100), CONSTRAINT music_id_fk FOREIGN KEY (music_id) REFERENCES ITEMS (item_id),
    artist CHAR(100),
    title CHAR(100),
    label CHAR(50),
    release_year YEAR,
    genre CHAR(100),
    album_cover_url CHAR(2083), CONSTRAINT uk_url_album UNIQUE KEY (album_cover_url)
    uploader CHAR(20), CONSTRAINT music_uploader_fk FOREIGN KEY (uploader) REFERENCES USERS (username),
    score DOUBLE, CONSTRAINT ck_score_music CHECK (score BETWEEN 0 AND 10),
    num_of_reviews INTEGER
);

INSERT INTO MUSIC VALUES
        ('MU_Dangerous_1991', 'Michael Jackson', 'Dangerous', 'Epic Records', 1991 ,'New Jack Swing, R&B, Pop' , 'https://upload.wikimedia.org/wikipedia/en/1/11/Michaeljacksondangerous.jpg' ,'admin' , 0 , 0) ,
        ('MU_Magical Mystery Tour_1967', 'The Beatles', 'Magical Mystery Tour','Parlophone, Capitol Records' 1967 , 'Rock, Psychedelia',  'https://upload.wikimedia.org/wikipedia/en/thumb/e/e8/MagicalMysteryTourDoubleEPcover.jpg/220px-MagicalMysteryTourDoubleEPcover.jpg' ,'admin' , 0 , 0),
        ('MU_The Rise and Fall of Ziggy Stardust and the Spiders from Mars_1972', 'David Bowie', 'The Rise and Fall of Ziggy Stardust and the Spiders from Mars',
         'RCA', 1972, 'Glam rock, Proto-punk', 'https://i.scdn.co/image/ab67616d0000b273ce928bc5dc2ed4d8e6d82366' , 'admin' , 0, 0);

CREATE TABLE TV_SHOWS (
        tv_show_id CHAR(100) , CONSTRAINT tv_shows_id_fk FOREIGN KEY (tv_show_id) REFERENCES ITEMS (item_id),
        title CHAR(100),
        airing_year YEAR,
        director CHAR(60),
        tv_show_cast CHAR(100),
        cover_url CHAR(2083), CONSTRAINT uk_url_tv_shows UNIQUE KEY (cover_url)
        summary CHAR(1000),
        uploader CHAR(20), CONSTRAINT tv_show_uploader_fk FOREIGN KEY (uploader) REFERENCES USERS(username),
        score DOUBLE, CONSTRAINT ck_score_tv_shows CHECK (score BETWEEN 0 AND 10),
        num_of_reviews INTEGER
);

INSERT INTO TV_SHOWS VALUES
('TV_One Tree Hill_2003', 'One Tree Hill' ,2003 , 'Mark Schwahn',
 'James Lafferty ,Sophia Bush , Bethany Joy Lenz , Chad Michael Murray'
  'https://upload.wikimedia.org/wikipedia/en/8/8f/One_Tree_Hill_%28soundtrack_album_-_cover_art%29.jpg'
  'Half-brothers Lucas and Nathan Scott trade between kinship
  and rivalry both on the basketball court and in the hearts of their friends in the small,
  but not so quiet town of Tree Hill, North Carolina.' , 'admin',0 , 0) ,

('TV_Friends_1994', 'Friends' ,1994 , 'David Crane , Marta Kauffman',
 'Matthew Perry , Courteney Cox ,Matt LeBlanc , David Schwimmer ,Lisa Kudrow ,
    Jennifer Aniston  ', 'https://meetmeinparadise.files.wordpress.com/2012/03/mpw-26106.jpeg'
  'A show about six friends in New York as they navigate their way through life
     and learn to grow up as they approach the third decade of their life.
     All, with the help from each other to get them through the obstacles that
     life naturally has for us.' , 'admin',0 , 0);


CREATE TABLE REVIEWS (
        item_id CHAR(100), CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES ITEMS (item_id)
        username CHAR(20), CONSTRAINT username_fk FOREIGN KEY (username) REFERENCES USERS (username)
        score DOUBLE, CONSTRAINT ck_score_in_review CHECK (score BETWEEN 0 AND 10),
        review CHAR(1000),
        category CHAR(15)
);
