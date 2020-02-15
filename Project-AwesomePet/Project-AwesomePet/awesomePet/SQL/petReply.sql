DESC communicationreply;

CREATE TABLE petReply(
	replyIDX				INTEGER AUTO_INCREMENT,
	parentIDX			INTEGER NOT NULL,
	writerID				VARCHAR(20) NOT NULL,
	content				VARCHAR(300) NOT NULL,
	writeDateTime		TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE DEFAULT (DATE(writeDateTime)),
	PRIMARY KEY(replyIDX),
	FOREIGN KEY(parentIDX) REFERENCES pet(petID) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);

DESC petReply;


SELECT * FROM pet JOIN petReply
ON pet.petID = petReply.parentIDX;


-- 테스트 데이터 입력
INSERT INTO petReply(parentIDX,						   
						   writerID,
						   content)
VALUES(1, 'aa', '가족을 찾아요 댓글 테스트 중 입니다.');


INSERT INTO petReply(parentIDX,
						   writerID,
						   content)
VALUES(1, 'bb', '댓글 테스트 !!');