
LOAD DATA
	INFILE 'prior_therapies.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE prior_therapies
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		THERAPY_TEXT					    CHAR(2000),
		MEDDRA_CODE					    CHAR(2000),
		MEDDRA_TERM					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		LAST_SYNCHED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LAST_SYNCHED_DATE="",
		THERAPY_TYPE					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

