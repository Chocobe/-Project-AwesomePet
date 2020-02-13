-- "petType" table
CREATE TABLE petType(
	typeName		VARCHAR(40) PRIMARY KEY
);

DESC petType;


-- "petSubType" table
CREATE TABLE petSubType(
	typeName			VARCHAR(40) NOT NULL,
	subTypeName		VARCHAR(40),
	subTypeComment	VARCHAR(100),
	PRIMARY KEY(subTypeName),
	FOREIGN KEY(typeName) REFERENCES petType(typeName) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC petSubType;


-- "pet" table
CREATE TABLE pet(
	petID		INTEGER	AUTO_INCREMENT, 
	subType	VARCHAR(20) NOT NULL,
	age		INTEGER,
	gender	VARCHAR(10),
	price		INTEGER,
	vaccination	VARCHAR(50),
	neutralization	VARCHAR(10),
	PRIMARY KEY(petID),
	FOREIGN KEY(subType) REFERENCES petSubType(subTypeName) ON DELETE CASCADE ON UPDATE CASCADE
);

DESC pet;


-- "petBoard" table
CREATE TABLE petBoard(
	boardIDX				INTEGER,
	writerID				VARCHAR(20),
	watch					INTEGER DEFAULT 0,
	writeDateTime		TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE DEFAULT (DATE(writeDateTime)),
	boardState			VARCHAR(10),
	PRIMARY KEY(boardIDX),
	FOREIGN KEY(boardIDX) REFERENCES pet(petID) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);

DESC petboard;


-- "petBoardImages"
CREATE TABLE petContentsImage(
	boardIDX					INTEGER,
	orderNumber				INTEGER DEFAULT 0,
	imgLocation				VARCHAR(500),
	imgOriginLocation		VARCHAR(500),
	FOREIGN KEY(boardIDX) REFERENCES petboard(boardIDX) ON DELETE CASCADE
);




SELECT pet.petID, 
		 pet.subType, 
		 pet.age, 
		 pet.gender,
		 pet.price,
		 pet.vaccination, 
		 pet.neutralization, 			

		 petBoard.boardIDX, 
		 petBoard.writerID, 
		 petBoard.boardState, 								
	
		 firstImage.boardIDX, 
		 firstImage.imgLocation, 
		 firstImage.imgOriginLocation, 
		 firstImage.orderNumber
			
FROM pet, petBoard LEFT JOIN 
		(SELECT petContentsImage.boardIDX, 
		 		  petContentsImage.imgLocation, 
		 	 	  petContentsImage.imgOriginLocation, 
		 		  petContentsImage.orderNumber 
		 FROM petContentsImage, (SELECT boardIDX, MAX(orderNumber) AS orderNumber 
		 								 FROM petContentsImage 
		 	 							 GROUP BY boardIDX) AS tempTable
		 	
		 WHERE petContentsImage.boardIDX = tempTable.boardIDX 
		 AND petContentsImage.orderNumber = tempTable.orderNumber) AS firstImage 
			
ON petBoard.boardIDX = firstImage.boardIDX 
WHERE pet.petID = petBoard.boardIDX
AND petBoard.boardState = '공개'
LIMIT 4 OFFSET 0;





DESC petContentsImage;


DROP TABLE petContentsImage;
DROP TABLE petboard;
DROP TABLE pet;


--
-- petBoard 작성 순서
--
-- 1. petType 데이터 작성
--
-- 2. petSubType 데이터 작성
--
-- 3. pet 데이터 작성
--
-- 4. petBoard 데이터 작성
--
-- 5. petBoardImages 데이터 작성

SELECT * FROM petType;

SELECT * FROM petSubType;

SELECT * FROM pet;

SELECT * FROM petBoard;

SELECT * FROM petContentsImage;


SELECT * FROM petContentsImage
WHERE boardIDX = 2
GROUP BY boardIDX
HAVING orderNumber = MIN(orderNumber);


SELECT * 
FROM pet, petboard
LEFT JOIN (SELECT * FROM petContentsImage
			  GROUP BY boardIDX
			  HAVING orderNumber = MIN(orderNumber)) AS petImage
ON petboard.boardIDX = petImage.boardIDX);


-- 첫번째 이미지만 조회하기
SELECT *
FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber
								FROM petContentsImage
								GROUP BY boardIDX) AS temp
WHERE petContentsImage.boardIDX = temp.boardIDX
AND petContentsImage.orderNumber = temp.orderNumber;


-- 전체 조회 테스트 (첫번째 이미지 포함)
SELECT pet.petID, pet.age, pet.gender, image.imgLocation
FROM pet LEFT JOIN (SELECT petContentsImage.boardIDX, petContentsImage.orderNumber, petContentsImage.imgLocation, petContentsImage.imgOriginLocation
						  FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber
						  								  FROM petContentsImage
						  								  GROUP BY boardIDX) AS subImage
						  WHERE petContentsImage.boardIDX = subImage.boardIDX
						  AND petContentsImage.orderNumber = subImage.orderNumber) AS image
ON pet.petID = image.boardIDX;


SELECT pet.petID, pet.age, pet.gender, petboard.boardState, firstImage.imgLocation, firstImage.orderNumber
FROM pet, petboard LEFT JOIN  (SELECT petContentsImage.boardIDX, petContentsImage.imgLocation, petContentsImage.orderNumber
										 FROM petContentsImage, (SELECT boardIDX, MIN(orderNumber) AS orderNumber
										 			 					 FROM petContentsImage
										 								 GROUP BY boardIDX) AS tempImage
										 WHERE petContentsImage.boardIDX = tempImage.boardIDX
										 AND petContentsImage.orderNumber = tempImage.orderNumber) AS firstImage
ON petboard.boardIDX = firstImage.boardIDX
WHERE pet.petID = petboard.boardIDX
AND petboard.boardState = '공개';




DELETE FROM pet;

DELETE FROM petboard;

DELETE FROM petContentsImage;


-- petType 테스트 데이터 입력
INSERT INTO petType(typeName)
VALUES('소형견');

INSERT INTO petType(typeName)
VALUES('대형견');

INSERT INTO petType(typeName)
VALUES('고양이');

INSERT INTO pettype(typeName)
VALUES('몽키');

INSERT INTO pettype(typeName)
VALUES('공룡');


-- petSubType 테스트 데이터 입력
INSERT INTO petSubType(typeName, subTypeName, subTypeComment)
VALUES('소형견', '푸들', '활달합니다');

INSERT INTO petSubType(typeName, subTypeName, subTypeComment)
VALUES('대형견', '허스키', '에너자이저 입니다');

INSERT INTO petSubType(typeName, subTypeName, subTypeComment)
VALUES('고양이', '치타', '도망가면 못잡습니다');

INSERT INTO petSubType(typeName, subTypeName, subTypeComment)
VALUES('고양이', '집냥이', '귀엽습니다');

INSERT INTO petsubtype(typeName, subTypeName, subTypeComment)
VALUES('몽키', '고릴라', '감당될지 모르겠습니다');

INSERT INTO petsubtype(typeName, subTypeName, subTypeComment)
VALUES('공룡', '티라노 사우르스', '못키워요');

INSERT INTO petsubtype(typeName, subTypeName, subTypeComment)
VALUES('공룡', '랩터', '못키운다구요');


-- pet 테스트 데이터 입력
INSERT INTO pet(subType, age, gender, price, vaccination, neutralization)
VALUES('푸들', 1, '남아', 20000, '1차 완료', '완료');

INSERT INTO pet(subType, age, gender, price, vaccination, neutralization)
VALUES('허스키', 2, '여아', 30000, '2차 완료', '미시술');

INSERT INTO pet(subType, age, gender, price, vaccination, neutralization)
VALUES('치타', 3, '남아', 40000, '구충 완료', '완료');

INSERT INTO pet(subType, age, gender, price, vaccination, neutralization)
VALUES('집냥이', 4, '여아', 50000, '미접종', '미시술');


-- petBoard 테스트 데이터 입력
INSERT INTO petBoard(boardIDX, writerID, state)
VALUES(2, 'aa', '공개');

INSERT INTO petBoard(boardIDX, writerID, state)
VALUES(3, 'aa', '비공개');

INSERT INTO petBoard(boardIDX, writerID, state)
VALUES(4, 'aa', '분양완료');

INSERT INTO petBoard(boardIDX, writerID, state)
VALUES(5, 'aa', '공개');


-- petImages 테스트 데이터 입력



-- 각 테이블의 JOIN 조회
SELECT petBoard.boardIDX,
		 petSubType.typeName,
		 petSubType.subTypeName,
		 petSubType.subTypeComment,
		 pet.age,
		 pet.gender,
		 pet.price,
		 pet.vaccination,
		 pet.neutralization,
		 petBoard.writerID,
		 petBoard.watch,
		 petBoard.writeDate,
		 petBoard.boardState
FROM pet, petBoard, petSubType
WHERE pet.petID = petBoard.boardIDX 
  AND pet.subType = petSubType.subTypeName
ORDER BY boardIDX DESC;
  
SELECT * FROM petContentsImage
WHERE boardIDX = 20;

UPDATE pettype 
SET typeName='불독'
WHERE typeName='푸들';
		 
		 
		 
CREATE TABLE test_1(
	val_1		INTEGER,
	val_2		VARCHAR(20),
	PRIMARY KEY(val_2)
)

CREATE TABLE test_2(
	value_1	INTEGER,
	value_2	VARCHAR(20),
	FOREIGN KEY(value_2) REFERENCES test_1(val_2) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO test_1(val_1, val_2)
VALUES(1, 'a'), (2, 'b'), (3, 'c');

INSERT INTO test_2(value_1, value_2)
VALUES(11, 'a'), (22, 'b'), (33, 'c');

SELECT * FROM test_1;
SELECT * FROM test_2;

UPDATE test_1 SET val_2 = 'BB'
WHERE val_2 = 'b';

DELETE FROM test_1
WHERE val_2 = 'BB';


SELECT * FROM petboard
WHERE boardState='공개' 
AND boardIDX IN 
		(SELECT petID FROM pet
	 	 WHERE subType IN 
	 			(SELECT subTypeName FROM petsubtype WHERE typeName='고양이'));
	 			
	 			
SELECT * FROM petboard
WHERE boardState='공개' 
AND boardIDX IN
		(SELECT petID FROM pet
		 WHERE subType='스핑크스' 
		 AND subType IN 
		 		(SELECT subTypeName FROM petsubtype WHERE typeName='고양이'));