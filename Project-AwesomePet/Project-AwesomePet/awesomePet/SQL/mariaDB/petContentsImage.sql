-- "petBoardImages"
CREATE TABLE petContentsImage(
	boardIDX					INTEGER,
	orderNumber				INTEGER DEFAULT 0,
	imgLocation				VARCHAR(500),
	imgOriginLocation		VARCHAR(500),
	FOREIGN KEY(boardIDX) REFERENCES petBoard(boardIDX) ON DELETE CASCADE
);


DESC petContentsImage;


SELECT * FROM petContentsImage;