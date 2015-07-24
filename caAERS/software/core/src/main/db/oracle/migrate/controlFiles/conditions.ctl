
LOAD DATA
	INFILE 'conditions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE conditions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		CONDITION_NAME 				    CHAR(2000),
		GRID_ID					    CHAR(2000)
	)

