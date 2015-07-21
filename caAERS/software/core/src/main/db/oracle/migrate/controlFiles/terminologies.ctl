OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'terminologies.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE terminologies
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM_CODE					    INTEGER EXTERNAL(10),
		CTC_ID 					    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		MEDDRA_VERSION_ID				    INTEGER EXTERNAL(10)
	)

