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
	
	writeDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	watch INTEGER DEFAULT 0, 
	replyCnt INTEGER DEFAULT 0,
	
	PRIMARY KEY(boardIDX), 
	FOREIGN KEY(writerID) REFERENCES awesomePetMember(memberID) ON DELETE CASCADE
);


DESC communicationBoard;


SELECT * FROM communicationBoard;