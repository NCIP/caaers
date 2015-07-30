
LOAD DATA
	INFILE 'study_therapy.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_therapy
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_THERAPY_TYPE			    CHAR(2000),
		STUDY_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)

