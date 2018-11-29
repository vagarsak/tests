
CREATE TABLE IF NOT EXISTS result (
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    quantity        INTEGER,
    successful      INTEGER
);

CREATE TABLE IF NOT EXISTS question (
    id             INTEGER PRIMARY KEY AUTO_INCREMENT,
    type           INTEGER,
    question       VARCHAR ,
    result_id      INTEGER,
    FOREIGN KEY (result_id) REFERENCES result (id)
);

CREATE TABLE IF NOT EXISTS question_answer (
    question_id             INTEGER NOT NULL ,
    answer                  VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES question (id)
);


CREATE TABLE IF NOT EXISTS question_choicesanswer (
    question_id             INTEGER NOT NULL ,
    choices_answer          VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES question (id)
);

CREATE TABLE IF NOT EXISTS resultTest (
    id             INTEGER PRIMARY KEY AUTO_INCREMENT,
    result 	   boolean
);

CREATE TABLE IF NOT EXISTS resultTest_answer (
    resultTest_id INTEGER not null,
    answer        VARCHAR(255),
    FOREIGN KEY (resultTest_id) REFERENCES resultTest (id)
);


CREATE TABLE IF NOT EXISTS test (
    id             INTEGER PRIMARY KEY AUTO_INCREMENT,
    nameUser       VARCHAR,
	  result 	   boolean
);

CREATE TABLE IF NOT EXISTS test_resultTest (
    Test_id               INTEGER NOT NULL,
    listQuestionAnswer_id INTEGER NOT NULL ,
    key                   INTEGER NOT NULL,
    FOREIGN KEY (Test_id) REFERENCES test (id),
    FOREIGN KEY (listQuestionAnswer_id) REFERENCES resultTest (id),
    PRIMARY KEY (Test_id, key)
);

CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(100) NOT NULL,
	enabled boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
	username  VARCHAR(50)NOT NULL,
	authority VARCHAR(50) NOT NULL,
	constraint fk_authorities_user  FOREIGN KEY(username) REFERENCES users(username)
);
CREATE UNIQUE index ix_auth_username ON authorities (username,authority);
