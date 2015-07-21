OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\pre_existing_conditions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE pre_existing_conditions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		CONDITION_TEXT 				    CHAR,
		MEDDRA_LLT_CODE				    CHAR,
		MEDDRA_LLT					    CHAR,
		MEDDRA_HLGT					    CHAR,
		GRID_ID					    CHAR,
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

