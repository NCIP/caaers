
LOAD DATA
	INFILE 'disease_terminologies.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE disease_terminologies
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM_CODE					    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		MEDDRA_VERSION_ID				    INTEGER EXTERNAL(10)
	)

