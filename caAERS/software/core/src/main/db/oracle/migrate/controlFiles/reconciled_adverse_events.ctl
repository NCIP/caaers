OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\reconciled_adverse_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE reconciled_adverse_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ITEM_ID					    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		ATTRIBUTION_SUMMARY_CODE			    INTEGER EXTERNAL(10),
		WHY_SERIOUS					    CHAR,
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		VERBATIM					    CHAR,
		ERROR_MESSAGE					    CHAR,
		GRADE_CODE					    INTEGER EXTERNAL(10),
		ACTION_CODE				    INTEGER EXTERNAL(10),
		EXTERNAL_ID					    CHAR,
		TERM_CODE					    CHAR,
		TERM_NAME					    CHAR,
		TERM_OTHER_SPECIFY				    CHAR,
		SYSTEM 				    INTEGER EXTERNAL(10)
	)

