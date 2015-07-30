
LOAD DATA
	INFILE 'pre_existing_conditions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE pre_existing_conditions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		CONDITION_TEXT 				    CHAR(2000),
		MEDDRA_LLT_CODE				    CHAR(2000),
		MEDDRA_LLT					    CHAR(2000),
		MEDDRA_HLGT					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

