OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_routine_reports.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_routine_reports
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		START_DATE				   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE				   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		GRID_ID					    CHAR,
		STATUS_CODE					    INTEGER EXTERNAL(10),
		TREATMENT_ASSIGNMENT_ID			    INTEGER EXTERNAL(10)
	)

