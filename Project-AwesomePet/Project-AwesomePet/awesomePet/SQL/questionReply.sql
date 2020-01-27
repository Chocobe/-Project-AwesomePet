CREATE TABLE questionReply(
	replyIDX				INTEGER	AUTO_INCREMENT,
	parentIDX			INTEGER,
	writerID				VARCHAR(20),
	content				VARCHAR(300),
	writeDateTime		TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE	DEFAULT (DATE(writeDateTime)),
	PRIMARY KEY(replyIDX),
	FOREIGN KEY(parentIDX) REFERENCES questionboard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);
DROP TABLE questionReply;

SELECT * FROM questionreply
ORDER BY replyIDX DESC;


INSERT INTO questionReply(parentIDX, writerID, content)
VALUES(45, 'aa', '댓글 테스트1 입니다');

INSERT INTO questionReply(parentIDX, writerID, content)
VALUES(45, 'bb', '댓글 테스트2 입니다');

INSERT INTO questionReply(parentIDX, writerID, content)
VALUES(45, 'cc', '댓글 테스트3 입니다');

INSERT INTO questionReply(parentIDX, writerID, content)
VALUES(45, 'aa', 'JSON 테스트!');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(45, 'bb',
'긴 댓글 입니다.
깁니다.
짧지 않아요');