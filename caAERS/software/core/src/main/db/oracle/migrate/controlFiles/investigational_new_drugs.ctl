
LOAD DATA
	INFILE 'investigational_new_drugs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE investigational_new_drugs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		IND_NUMBER					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		STATUS 					    CHAR(2000)
	)

