-- communicationReply
CREATE TABLE communicationReply(
	replyIDX				INTEGER AUTO_INCREMENT,
	parentIDX			INTEGER,
	writerID				VARCHAR(20),
	content				VARCHAR(300),
	writeDate			TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	
	PRIMARY KEY(replyIDX),
	FOREIGN KEY(parentIDX) REFERENCES communicationBoard(boardIDX) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomePetMember(memberID) ON DELETE CASCADE
);


DESC communicationReply;


SELECT * FROM communicationReply;