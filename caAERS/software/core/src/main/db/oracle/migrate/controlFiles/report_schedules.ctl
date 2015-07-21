OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'report_schedules.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_schedules
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		RCT_ID 					    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		STATUS_CODE					    INTEGER EXTERNAL(10),
		REQUIRED				    INTEGER EXTERNAL(1) "case :REQUIRED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		EMAIL						    CHAR,
		ASSIGNED_IDENTIFER				    CHAR,
		SUBMISSION_MESSAGE				    CHAR,
		SUBMISSION_URL 				    CHAR,
		MANUALLY_SELECTED				    INTEGER EXTERNAL(1) "case :MANUALLY_SELECTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		WORKFLOW_ID					    INTEGER EXTERNAL(10),
		REVIEW_STATUS_CODE				    INTEGER EXTERNAL(10),
		CASE_NUMBER					    CHAR,
		META_DATA					    CHAR
	)

