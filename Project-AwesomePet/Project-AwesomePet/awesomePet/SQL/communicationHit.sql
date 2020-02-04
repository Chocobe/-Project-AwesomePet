-- on delete cascade 추가 하기
CREATE TABLE communicationHit(
	boardIDX			INTEGER,
	hitMemberID		VARCHAR(20),	
	
	PRIMARY KEY(boardIDX, hitMemberID),
	FOREIGN KEY(boardIDX) REFERENCES communicationboard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(hitMemberID) REFERENCES awesomepetmember(memberID) ON DELETE CASCADE
);
DROP TABLE communicationhit;

DESC communicationhit;

SELECT * FROM communicationhit;

SELECT COUNT(*) AS COUNT FROM communicationHit
WHERE boardIDX=11 AND hitMemberID='aa';

SELECT * FROM communicationboard 
LEFT JOIN communicationHit
ON communicationboard.boardIDX = communicationHit.boardIDX;

-- communicationBoard 와 communicationHit 의 조인
SELECT contents.boardIDX,
		 writerID,
		 title,
		 content,
		 imgLocation_1,
		 imgOriginLocation_1,
		 imgLocation_2,
		 imgOriginLocation_2,
		 imgLocation_3,
		 imgOriginLocation_3,
		 writeDate,
		 watch,
		 replyCnt,
		 hit.hitCount AS hitCnt
FROM communicationboard AS contents
LEFT JOIN (SELECT boardIDX, COUNT(*) AS hitCount
	  		  FROM communicationHit
			  GROUP BY boardIDX) AS hit
ON contents.boardIDX = hit.boardIDX
WHERE contents.boardIDX=11;