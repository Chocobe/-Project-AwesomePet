-- 궁금해요 (질문 게시판)
CREATE TABLE questionBoard(
	boardIDX INTEGER AUTO_INCREMENT,
	writerID VARCHAR(20),
	title VARCHAR(50),
	content VARCHAR(500),
	writeDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	watch INTEGER DEFAULT 0, 
	replyCnt INTEGER DEFAULT 0,
	PRIMARY KEY(boardIDX), 
	FOREIGN KEY(writerID) REFERENCES awesomePetMember(memberID)
);

SELECT * FROM questionBoard;

DELETE FROM questionBoard
WHERE boardIDX=1;