OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\participant_histories.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE participant_histories
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		BASELINE_PERFORMANCE_STATUS			    CHAR,
		HEIGHT_UNIT					    CHAR,
		WEIGHT_UNIT					    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		HEIGHT 					    INTEGER EXTERNAL,
		WEIGHT 					    INTEGER EXTERNAL,
		HEIGHT_CODE					    INTEGER EXTERNAL(10),
		WEIGHT_CODE					    INTEGER EXTERNAL(10)
	)

