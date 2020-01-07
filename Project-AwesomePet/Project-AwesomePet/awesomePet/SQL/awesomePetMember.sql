CREATE DATABASE awesomePet;
USE awesomePet;

-- awesomePetMember 테이블 생성
CREATE TABLE awesomePetMember(
	memberID					VARCHAR(20)		PRIMARY KEY,
	memberPW					VARCHAR(20)		NOT NULL,
	memberEmail				VARCHAR(50)		NOT NULL,
	memberPhone				VARCHAR(13)		NOT NULL,
	memberAddr				VARCHAR(50)		NOT NULL,
	memberGrade				INTEGER			DEFAULT 0,
	memberJoinDateTime	TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP(),
	memberJoinDate			DATE				DEFAULT (DATE(memberJoinDateTime))
);

SELECT * FROM awesomepetmember;

DELETE FROM awesomePetMember;


-- 테스트 데이터 입력
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('aa',
							 		  'aaa',
								 	  'aa@aa.com',
									  '010-1111-2222',
									  '부산광역시 사하구');

INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('bb',
							 		  'bbb',
								 	  'bb@bb.com',
									  '010-3333-4444',
									  '부산광역시 서구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('cc',
							 		  'ccc',
								 	  'cc@cc.com',
									  '010-5555-6666',
									  '부산광역시 남구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('dd',
							 		  'ddd',
								 	  'dd@dd.com',
									  '010-7777-8888',
									  '부산광역시 해운대구');
									  
INSERT INTO awesomePetMember(memberID,
									  memberPW,
									  memberEmail,
									  memberPhone,
									  memberAddr)
						  	 VALUES('aassddffgg',
							 		  'aaasssdddfffggg',
								 	  'asdfg@asdfg.com',
									  '010-9999-0000',
									  '부산광역시 사하구');
									  
