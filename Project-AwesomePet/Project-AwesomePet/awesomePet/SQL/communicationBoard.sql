-- communicationBoard
CREATE TABLE communicationBoard(
	boardIDX INTEGER AUTO_INCREMENT,
	writerID VARCHAR(20),
	title VARCHAR(50),
	content VARCHAR(500),
	
	imgLocation_1 VARCHAR(500),
	imgOriginLocation_1 VARCHAR(500),
	
	imgLocation_2 VARCHAR(500),
	imgOriginLocation_2 VARCHAR(500),
	
	imgLocation_3 VARCHAR(500),
	imgOriginLocation_3 VARCHAR(500),
	
	writeDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate DATE DEFAULT (DATE(writeDateTime)),
	watch INTEGER DEFAULT 0, 
	replyCnt INTEGER DEFAULT 0,
	
	PRIMARY KEY(boardIDX), 
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);
DROP TABLE communicationboard;

--
-- 속성명 변경하자
--
-- imgLocation_1 -> imgFile_1
-- imgOriginLocation_1 -> imgOrigin_1
--


SELECT * FROM communicationboard;

SELECT * FROM communicationboard
ORDER BY boardIDX DESC;


DELETE FROM communicationboard;

DELETE FROM communicationboard
WHERE boardIDX=23;


-- 테스트 데이터 입력
-- 01
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '소통해요 테스트_1',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('bb',
										 '소통해요 테스트_2',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('cc',
										 '소통해요 테스트_3',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '소통해요 테스트_4',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '소통해요 테스트_5',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('cc',
										 '소통해요 테스트_6',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '소통해요 테스트_7',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('cc',
										 '소통해요 테스트_8',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('bb',
										 '소통해요 테스트_9',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('bb',
										 '소통해요 테스트_10',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('ff',
										 '소통해요 테스트_11',
										 '테스트중 입니다.',
										 '이미지 경로_1',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '이미지 테스트용 게시물 입니다 ; )',
										 '강아지가 이쁘네요ㅎㅎ',
										 '/awesomePet/communicationUploadImages/background_113.jpg',
										 '원본_1',
										 '이미지 경로_2',
										 '원본_2',
										 '이미지 경로_3',
										 '원본_3');
										 
										 
INSERT INTO communicationboard(writerID,
										 title,
										 content,
										 imgLocation_1,
										 imgOriginLocation_1,
										 imgLocation_2,
										 imgOriginLocation_2,
										 imgLocation_3,
										 imgOriginLocation_3)
								VALUES('aa',
										 '이미지 테스트용 게시물 입니다 ; )',
										 '강아지가 이쁘네요ㅎㅎ',
										 '/awesomePet/communicationUploadImages/background_113.jpg',
										 '',
										 NULL,
										 '',
										 '',
										 '');