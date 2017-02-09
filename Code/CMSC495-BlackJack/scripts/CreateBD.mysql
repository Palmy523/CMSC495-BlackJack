DROP TABLE game_data;

DROP TABLE user;

CREATE TABLE game_data
(
    extreme_play         boolean NOT NULL,
    max_chips            INTEGER NULL,
    bank_amt             INTEGER NOT NULL,
    game_data_id         INTEGER NOT NULL,
    user_id              INTEGER NOT NULL
);

ALTER TABLE game_data
ADD CONSTRAINT XPKgame_data PRIMARY KEY (game_data_id);

CREATE TABLE user
(
    user_id              INTEGER NOT NULL,
    user_name            VARCHAR(16) NOT NULL,
    email                VARCHAR(40) NOT NULL,
    password             VARCHAR(16) NOT NULL,
    temp_password        boolean NOT NULL,
    email_key            VARCHAR(16) NULL
);

ALTER TABLE user
ADD CONSTRAINT XPKuser PRIMARY KEY (user_id);

CREATE UNIQUE INDEX XAK1user ON user
(
    email ASC
);

CREATE UNIQUE INDEX XAK2user ON user
(
    user_name ASC
);

ALTER TABLE game_data
ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user (user_id);

[6:17]  
wait, I'm going to change it to easy play

[6:18]  
Here you go

[6:18]  
DROP TABLE game_data;

DROP TABLE user;

CREATE TABLE game_data
(
    easy_play            boolean NOT NULL,
    max_chips            INTEGER NULL,
    bank_amt             INTEGER NOT NULL,
    game_data_id         INTEGER NOT NULL,
    user_id              INTEGER NOT NULL
);

ALTER TABLE game_data
ADD CONSTRAINT XPKgame_data PRIMARY KEY (game_data_id);

CREATE TABLE user
(
    user_id              INTEGER NOT NULL,
    user_name            VARCHAR(16) NOT NULL,
    email                VARCHAR(40) NOT NULL,
    password             VARCHAR(16) NOT NULL,
    temp_password        boolean NOT NULL,
    email_key            VARCHAR(16) NULL
);

ALTER TABLE user
ADD CONSTRAINT XPKuser PRIMARY KEY (user_id);

CREATE UNIQUE INDEX XAK1user ON user
(
    email ASC
);

CREATE UNIQUE INDEX XAK2user ON user
(
    user_name ASC
);

ALTER TABLE game_data
ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user (user_id);