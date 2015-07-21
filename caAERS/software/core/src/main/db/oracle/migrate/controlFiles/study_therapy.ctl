OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'study_therapy.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_therapy
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_THERAPY_TYPE			    CHAR,
		STUDY_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

