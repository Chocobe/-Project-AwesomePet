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
WHERE parentIDX=71 
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


-- 댓글 테스트
INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '첫번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '두번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '세번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '네번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '다섯번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '여섯번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '일곱번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '여덟번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '아홉번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열한번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열두번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열세번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열네번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열다섯번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열여섯번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열일곱번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열여덟번째 테스트 댓글입니다');

INSERT INTO questionreply(parentIDX, writerID, content)
VALUES(71, 'bb', '열아홉번째 테스트 댓글입니다');