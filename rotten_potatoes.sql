-- change this line so that is uses your database
USE rotten_potatoes;

-- remove a table if it already exists to begin from scratch
DROP TABLE IF EXISTS BADGES;

CREATE TABLE BADGES (
	badge_id CHAR(6),
		CONSTRAINT pk_badge_id PRIMARY KEY (badge_id),
	badge_name CHAR(20),
    badge_icon CHAR(28),
    badge_description CHAR(64)
);