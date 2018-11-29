
INSERT INTO result(quantity, successful) VALUES (2,3);
INSERT INTO result(quantity, successful) VALUES (2,3);

INSERT INTO question(type, question, result_id) VALUES (0,'1111111111111',1);
INSERT INTO question(type, question, result_id) VALUES (0,'2222222222222',2);

INSERT INTO question_answer(Question_id, answer) VALUES (1,'123');
INSERT INTO question_answer(Question_id, answer) VALUES (2,'123');

INSERT INTO users(username,password,enabled) VALUES('admin','admin',true);
INSERT INTO users(username,password,enabled) VALUES('user','user',true);

INSERT INTO authorities(username,authority) VALUES('admin','ROLE_ADMIN');
INSERT INTO authorities(username,authority) VALUES('user','ROLE_USER');