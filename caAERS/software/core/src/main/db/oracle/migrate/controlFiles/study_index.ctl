OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'study_index.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_index
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR,
		STUDY_ID				    INTEGER EXTERNAL,
		GRID_ID					    CHAR,
		ROLE					    INTEGER EXTERNAL(10)
	)

