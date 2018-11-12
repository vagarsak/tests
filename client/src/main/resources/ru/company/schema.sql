
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


