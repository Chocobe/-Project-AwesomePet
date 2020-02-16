-- communicationHit
CREATE TABLE communicationHit(
	boardIDX			INTEGER,
	hitMemberID		VARCHAR(20),	
	
	PRIMARY KEY(boardIDX, hitMemberID),
	FOREIGN KEY(boardIDX) REFERENCES communicationBoard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(hitMemberID) REFERENCES awesomePetMember(memberID) ON DELETE CASCADE
);


DESC communicationHit;


SELECT * FROM communicationHit;