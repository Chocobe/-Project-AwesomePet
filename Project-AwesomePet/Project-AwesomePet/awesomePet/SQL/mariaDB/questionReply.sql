-- 궁금해요(질문 게시판) 댓글
CREATE TABLE questionReply(
	replyIDX				INTEGER	AUTO_INCREMENT,
	parentIDX			INTEGER,
	writerID				VARCHAR(20),
	content				VARCHAR(300),
	writeDate			TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	PRIMARY KEY(replyIDX),
	FOREIGN KEY(parentIDX) REFERENCES questionBoard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomePetMember(memberID) ON DELETE CASCADE
);


SELECT * FROM questionReply;