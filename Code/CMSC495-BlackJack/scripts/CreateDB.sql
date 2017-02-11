DROP TABLE game_data;

DROP TABLE user;

CREATE TABLE game_data
(
    easy_play            boolean NOT NULL,
    max_chips            FLOAT(7, 2) NOT NULL,
    bank_amt             FLOAT(7, 2) NOT NULL,
    user_id              INTEGER NOT NULL
);

CREATE TABLE user
(
    user_id              INTEGER NOT NULL AUTO_INCREMENT KEY,
    user_name            VARCHAR(16) NOT NULL UNIQUE,
    email                VARCHAR(40) NOT NULL UNIQUE,
    temp_email		     VARCHAR(40) NULL,
    password             VARCHAR(16) NOT NULL,
    temp_password        boolean     NULL,
    email_key            VARCHAR(16) NULL
);

ALTER TABLE game_data
ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user (user_id);