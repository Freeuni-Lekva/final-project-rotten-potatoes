-- change this line so that is uses your database
USE rotten_potatoes;

-- remove a table if it already exists to begin from scratch
DROP TABLE IF EXISTS BADGES;
DROP TABLE IF EXISTS USER_BADGES;
DROP TABLE IF EXISTS FOLLOWERS;

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