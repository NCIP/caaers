
LOAD DATA
	INFILE 'ae_reports.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_reports
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ASSIGNMENT_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		CREATED_AT				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_AT="",
		REPORTING_PERIOD_ID				    INTEGER EXTERNAL(10),
		IDE_ADMINISTERED				    INTEGER EXTERNAL(1) "case :IDE_ADMINISTERED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		EXTERNAL_ID					    CHAR(2000)
	)

