OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'arms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE arms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		DESCRIPTION					    CHAR,
		EPOCH_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

