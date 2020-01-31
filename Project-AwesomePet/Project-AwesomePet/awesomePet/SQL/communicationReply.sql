CREATE TABLE communicationReply(
	replyIDX				INTEGER AUTO_INCREMENT,
	parentIDX			INTEGER,
	writerID				VARCHAR(20),
	content				VARCHAR(300),
	writeDateTime		TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE DEFAULT (DATE(writeDateTime)),
	PRIMARY KEY(replyIDX),
	FOREIGN KEY(parentIDX) REFERENCES communicationboard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);


SELECT * FROM communicationReply;

SELECT * FROM communicationReply
ORDER BY replyIDX DESC;


-- 테스트 데이터
INSERT INTO communicationReply(parentIDX, 
										 writerID,
										 content)
								VALUES(11,
										 'aa',
										 '소통해요 댓글 입니다.');
										 
INSERT INTO communicationReply(parentIDX, 
										 writerID,
										 content)
								VALUES(11,
										 'bb',
										 '소통해요 댓글 테스트중.');
										 
INSERT INTO communicationReply(parentIDX, 
										 writerID,
										 content)
								VALUES(11,
										 'aa',
										 '여기는 소통해요 게시판 입니다.');