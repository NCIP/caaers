OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'caaers_bootstrap_log.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE caaers_bootstrap_log
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		RUNDATE					   DATE "YYYY-MM-DD" NULLIF RUNDATE="",
		OPERATION_CODE 				    INTEGER EXTERNAL(10),
		STATUS_CODE					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

