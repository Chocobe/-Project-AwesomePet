-- "petBoard" table
CREATE TABLE petBoard(
	boardIDX				INTEGER,
	writerID				VARCHAR(20),
	watch					INTEGER DEFAULT 0,
	writeDate			TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	boardState			VARCHAR(10),
	PRIMARY KEY(boardIDX),
	FOREIGN KEY(boardIDX) REFERENCES pet(petID) ON DELETE CASCADE,
	FOREIGN KEY(writerID) REFERENCES awesomePetMember(memberID) ON DELETE CASCADE
);


DESC petBoard;


SELECT * FROM petBoard;