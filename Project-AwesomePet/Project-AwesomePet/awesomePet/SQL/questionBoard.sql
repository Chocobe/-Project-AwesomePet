CREATE TABLE questionBoard(
	boardIDX				INTEGER			AUTO_INCREMENT,
	writerID				VARCHAR(20),
	title					VARCHAR(50),
	content				VARCHAR(500),
	writeDateTime		TIMESTAMP		DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE				DEFAULT (DATE(writeDateTime)),
	watch					INTEGER			DEFAULT 0,
	PRIMARY KEY(boardIDX),
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID)
);


SELECT * FROM questionBoard;


INSERT INTO questionBoard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '첫번째 질문입니다',
						 		  '궁금한게 너무 많습니다.');
						 		  
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('bb',
  						 		  '두번째 질문이에요',
  						 		  '없는 아이디로 작성 테스트 중입니다');