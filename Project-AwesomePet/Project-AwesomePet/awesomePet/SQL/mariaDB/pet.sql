-- "pet" table
CREATE TABLE pet(
	petID		INTEGER	AUTO_INCREMENT, 
	subType	VARCHAR(20) NOT NULL,
	age		INTEGER,
	gender	VARCHAR(10),
	price		INTEGER,
	vaccination	VARCHAR(50),
	neutralization	VARCHAR(10),
	PRIMARY KEY(petID),
	FOREIGN KEY(subType) REFERENCES petSubType(subTypeName) ON DELETE CASCADE ON UPDATE CASCADE
);


DESC pet;


SELECT * FROM pet;