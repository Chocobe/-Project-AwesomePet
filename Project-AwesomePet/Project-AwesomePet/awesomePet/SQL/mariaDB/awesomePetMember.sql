-- awesomePetMember 테이블 생성
CREATE TABLE awesomePetMember(
	memberID					VARCHAR(20)		PRIMARY KEY,
	memberPW					VARCHAR(20)		NOT NULL,
	memberName				VARCHAR(12)		NOT NULL,
	memberBirthDay			DATE				NOT NULL,
	memberEmail				VARCHAR(50)		NOT NULL,
	memberPhone				VARCHAR(13)		NOT NULL,
	memberAddr				VARCHAR(50)		NOT NULL,
	memberGrade				INTEGER			DEFAULT 0,
	memberJoinDate			TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP()
);

UPDATE awesomePetMember
SET memberGrade=1
WHERE memberID='admin';

SELECT * FROM awesomePetMember;