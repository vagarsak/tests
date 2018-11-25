
CREATE TABLE IF NOT EXISTS result (
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    quantity        INTEGER,
    successful      INTEGER
);

CREATE TABLE IF NOT EXISTS question (
    id             INTEGER PRIMARY KEY AUTO_INCREMENT,
    type           INTEGER,
    question       varchar,
    result_id       INTEGER,
    FOREIGN KEY (result_id) REFERENCES result (id)
);

CREATE TABLE IF NOT EXISTS question_answer (
    question_id             INTEGER NOT NULL ,
    answer varchar(255),
    FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE IF NOT EXISTS question_choices_answer (
    question_id             INTEGER NOT NULL ,
    choices_answer varchar(255),
    FOREIGN KEY (question_id) REFERENCES question (id)
);

create table IF NOT EXISTS users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create table IF NOT EXISTS authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_user foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
