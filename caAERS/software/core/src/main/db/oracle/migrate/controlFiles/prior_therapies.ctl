OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'prior_therapies.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE prior_therapies
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		THERAPY_TEXT					    CHAR,
		MEDDRA_CODE					    CHAR,
		MEDDRA_TERM					    CHAR,
		GRID_ID					    CHAR,
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		THERAPY_TYPE					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

