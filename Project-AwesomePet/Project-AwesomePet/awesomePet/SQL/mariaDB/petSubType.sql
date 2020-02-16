-- "petSubType" table
CREATE TABLE petSubType(
	typeName			VARCHAR(40) NOT NULL,
	subTypeName		VARCHAR(40),
	subTypeComment	VARCHAR(100),
	PRIMARY KEY(subTypeName),
	FOREIGN KEY(typeName) REFERENCES petType(typeName) ON DELETE CASCADE ON UPDATE CASCADE
);


DESC petSubType;


SELECT * FROM petSubType;