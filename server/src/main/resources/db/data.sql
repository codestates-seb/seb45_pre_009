INSERT INTO MEMBER (email, display_name, password, created_at, oauth)
    VALUES ('test@test.com', 'test', '1111', CURRENT_TIMESTAMP, false);
INSERT INTO QUESTION (member_id, title, content, point, created_at, views)
    VALUES (1, 'title', 'content', 0, CURRENT_TIMESTAMP, 0);
INSERT INTO ANSWER (content, point, created_at, question_id, member_id)
    VALUES ('content', 0, CURRENT_TIMESTAMP, 1, 1);
INSERT INTO COMMENT (answer_id, content, point, created_at, member_id)
    VALUES (1, 'content', 0, CURRENT_TIMESTAMP, 1);