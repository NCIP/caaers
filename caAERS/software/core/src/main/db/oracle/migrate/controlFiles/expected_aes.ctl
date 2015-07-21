OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\expected_aes.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE expected_aes
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		TERM_ID					    INTEGER EXTERNAL(10),
		TERM_TYPE					    CHAR,
		GRID_ID					    CHAR,
		LOW_LEVEL_TERM_ID				    INTEGER EXTERNAL(10),
		VERBATIM					    CHAR,
		OTHER_TOXICITY 				    CHAR,
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE=""
	)

