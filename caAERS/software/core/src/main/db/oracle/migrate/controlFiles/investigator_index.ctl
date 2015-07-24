
LOAD DATA
	INFILE 'investigator_index.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE investigator_index
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR(2000),
		INVESTIGATOR_ID			    INTEGER EXTERNAL,
		GRID_ID					    CHAR(2000),
		ROLE					    INTEGER EXTERNAL(10)
	)

