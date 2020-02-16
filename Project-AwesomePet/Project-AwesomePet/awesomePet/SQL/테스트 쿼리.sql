CREATE TABLE test_1(
	boardIDX INTEGER PRIMARY KEY,
	val_1		VARCHAR(20),
	val_2		VARCHAR(20)
);

DROP TABLE test_1;

CREATE TABLE test_2(
	parentIDX INTEGER,
	boardIDX INTEGER,
	test2_val_1	VARCHAR(20),
	test2_val_2	VARCHAR(20),
	PRIMARY KEY(boardIDX),
	FOREIGN KEY(parentIDX) REFERENCES test_1(boardIDX)
);


SELECT * FROM test_1;
SELECT * FROM test_2;


INSERT INTO test_1
VALUES(1, '일번', '한번'), (2, '이번', '두번'), (3, '삼번', '세번');


INSERT INTO test_2
VALUES(1, 11, 'first_1', 'one_1'), (1, 12, 'first_2', 'one_2'), (1, 13, 'first_3', 'one_3'), (1, 14, 'first_4', 'one_4');

INSERT INTO test_2
VALUES(2, 21, 'second_1', 'two_1'), (2, 22, 'second_2', 'two_2'), (2, 23, 'second_3', 'two_3'), (2, 24, 'second_4', 'two_4');


SELECT *
FROM test_2, (SELECT parentIDX, MAX(test2_val_1) AS test2_val_1
				  FROM test_2
				  GROUP BY parentIDX) AS temp
				  
WHERE test_2.parentIDX = temp.parentIDX AND test_2.test2_val_1 = temp.test2_val_1;


CREATE TABLE test_3(
	val_1		INTEGER,
	timestamp_1	TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	date_1	DATE DEFAULT (DATE(timestamp_1))
);

DESC test_3;