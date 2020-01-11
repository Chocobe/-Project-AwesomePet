CREATE DATABASE awesomePet;
USE awesomePet;

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
	memberJoinDateTime	TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP(),
	memberJoinDate			DATE				DEFAULT (DATE(memberJoinDateTime))
);

SELECT * FROM awesomepetmember;

DELETE FROM awesomePetMember;

DROP TABLE awesomepetmember;

-- 테스트 데이터 입력
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberName,
									  memberBirthDay,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('aa',
							 		  'aaa',
							 		  '김영우a',
							 		  '1987-05-17',
								 	  'aa@aa.com',
									  '010-1111-2222',
									  '부산광역시 사하구');

INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberName,
									  memberBirthDay,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('bb',
							 		  'bbb',
							 		  '김영우b',
							 		  '2000.10.1',
								 	  'bb@bb.com',
									  '010-3333-4444',
									  '부산광역시 서구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberName,
									  memberBirthDay,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('cc',
							 		  'ccc',
							 		  '김영우c',
							 		  '2001/1/5',
								 	  'cc@cc.com',
									  '010-5555-6666',
									  '부산광역시 남구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberName,
									  memberBirthDay,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('dd',
							 		  'ddd',
							 		  '김영우d',
							 		  '1990-5-20',
								 	  'dd@dd.com',
									  '010-7777-8888',
									  '부산광역시 해운대구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberName,
									  memberBirthDay,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('aassddffgg',
							 		  'aaasssdddfffggg',
							 		  '김영우abc',
							 		  '2010-12-23',
								 	  'asdfg@asdfg.com',
									  '010-9999-0000',
									  '부산광역시 사하구');
									  
