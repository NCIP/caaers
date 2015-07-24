
LOAD DATA
	INFILE 'ae_recom_reports.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_recom_reports
	fields terminated by '\t'
	
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
		GRID_ID					    CHAR(2000)
	)

