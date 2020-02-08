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
	FOREIGN KEY(typeName) REFERENCES petType(typeName) ON DELETE CASCADE
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
	FOREIGN KEY(subType) REFERENCES petSubType(subTypeName) ON DELETE CASCADE
);

DESC pet;


-- "petImages" table
CREATE TABLE petImages(
	petID		INTEGER NOT NULL,
	imgLocation				VARCHAR(500),
	imgOriginLocation		VARCHAR(500),
	FOREIGN KEY(petID) REFERENCES pet(petID)
);

DESC petImages;


-- "petBoard" table
CREATE TABLE petBoard(
	boardIDX				INTEGER,
	writerID				VARCHAR(20),
	watch					INTEGER DEFAULT 0,
	writeDateTime		TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	writeDate			DATE DEFAULT (DATE(writeDateTime)),
	state					VARCHAR(10),
	PRIMARY KEY(boardIDX),
	FOREIGN KEY(boardIDX) REFERENCES pet(petID) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);

DESC petBoard;


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

SELECT * FROM petType;

SELECT * FROM petSubType;

SELECT * FROM pet;

SELECT * FROM petBoard;

SELECT * FROM petImages;


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
		 petBoard.state
FROM pet, petBoard, petSubType
WHERE pet.petID = petBoard.boardIDX 
  AND pet.subType = petSubType.subTypeName;
		 