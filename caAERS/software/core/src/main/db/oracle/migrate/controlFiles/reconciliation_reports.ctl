OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'reconciliation_reports.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE reconciliation_reports
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		REPORTING_PERIOD_ID				    INTEGER EXTERNAL(10),
		CREATED_DATE				   DATE "YYYY-MM-DD" NULLIF CREATED_DATE="",
		UPDATED_DATE					   DATE "YYYY-MM-DD" NULLIF UPDATED_DATE="",
		REVIEWED_BY				    CHAR
	)

