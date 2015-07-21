OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'ae_recom_reports.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_recom_reports
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		AE_REPORTED				    INTEGER EXTERNAL(1) "case :AE_REPORTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		VERSION				    INTEGER EXTERNAL(10),
		ADVERSE_EVENT_ID			    INTEGER EXTERNAL(10),
		REPORT_DEFINITION_ID			    INTEGER EXTERNAL(10),
		CREATED_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_DATE="",
		DUE_DATE				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF DUE_DATE="",
		GRID_ID					    CHAR
	)

