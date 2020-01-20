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


SELECT * FROM questionboard;
SELECT COUNT(*) AS totalCount FROM questionboard;

SELECT COUNT(*) DIV 11 AS divResult FROM questionboard;
SELECT COUNT(*) DIV 3 + 1 AS myResult FROM questionboard;

-- 1
INSERT INTO questionBoard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '첫번째 질문입니다',
						 		  '궁금한게 너무 많습니다.');
	
-- 2					 		  
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('bb',
  						 		  '두번째 질문이에요',
  						 		  '없는 아이디로 작성 테스트 중입니다');
  		
-- 3				 		  
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('aa',
  						 		  '세번째 질문이에요',
  						 		  'cc아이디로 작성 테스트 중입니다');
  		
-- 4				 		  
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('aa',
  						 		  '네번째 질문이에요',
  						 		  'aa는 궁금한게 많아요');
-- 5				 		  
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('bb',
  						 		  '다섯번째 질문이에요',
  						 		  '저는 bb에요');
  						 		  
-- 6
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('cc',
  						 		  '여섯번째 질문이에요',
  						 		  'cc입니다 잘 부탁드려요');
  						 		  
-- 7
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('dd',
  						 		  '일곱번째 질문이에요',
  						 		  '디디입니다');
  						 		  
-- 8
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('ee',
  						 		  '여덟번째 질문이에요',
  						 		  'ee 아이디 테스트 글 입니다');
  						 		  
-- 9
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('ff',
  						 		  '아홉번째 질문이에요',
  						 		  '테스트 글 작성중입니다.');
  						 		  
-- 10
INSERT INTO questionBoard(writerID,
								  title,
								  content)
  						 VALUES('gg',
  						 		  '열번째 질문이에요',
  						 		  '1페이지 게시글 10개가 작성되었습니다.');

-- 11  						 		  
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('hh',
						 		  '열한번째 질문 입니다',
						 		  '게시글 개수가 홀수가 되었습니다');
						 		  
-- 12
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '헬로~',
						 		  '날씨가 춥네요');
						 		  
--
-- 13
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('cc',
						 		  '안뇽안뇽',
						 		  '날씨가 춥네요~');
						 		  
-- 14
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('cc',
						 		  '어라?',
						 		  '날씨가 춥네요');
						 		  
-- 15
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('dd',
						 		  '굿모닝',
						 		  '아침은 토스트');
						 		  
-- 16
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('dd',
						 		  '우유좋아',
						 		  '딸기쨈도 좋아요');
						 		  
-- 17
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('ee',
						 		  '프로젝트 중',
						 		  '프로젝트가 언능 완성되면 좋겠어요');
						 		  
-- 18
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '헬로~',
						 		  '날씨가 춥네요');
						 		  
-- 19
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('cc',
						 		  '아메리카노',
						 		  '엔젤이 공부하기도 좋고 커피도 맛있어요');
						 		  
-- 20
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('cc',
						 		  '머그컵 좋아',
						 		  '머그 머그 머그컵');
						 		  
-- 21
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('hh',
						 		  '배고프네요',
						 		  '저녁은 뭐먹지?');
						 		  
-- 22
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '헬로~',
						 		  '날씨가 춥네요');
						 		  
-- 23
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '울집 강아지',
						 		  '못키우게 해요ㅜㅜ');
						 		  
-- 24
INSERT INTO questionboard(writerID,
								  title,
								  content)
						 VALUES('aa',
						 		  '추워추워...',
						 		  '서울은 부산보다 춥겠죠??');