OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\outcomes.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE outcomes
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		INCIDENT_DATE					   DATE "YYYY-MM-DD" NULLIF INCIDENT_DATE="",
		OUTCOME_TYPE_CODE				    INTEGER EXTERNAL(10),
		OTHER						    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		REPORT_ID					    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		ADVERSE_EVENT_ID				    INTEGER EXTERNAL(10)
	)

