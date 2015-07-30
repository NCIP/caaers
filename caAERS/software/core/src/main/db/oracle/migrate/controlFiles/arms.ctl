
LOAD DATA
	INFILE 'arms.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE arms
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		DESCRIPTION					    CHAR(2000),
		EPOCH_ID				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)

