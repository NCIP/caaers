
LOAD DATA
	INFILE 'report_schedules.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_schedules
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		RCT_ID 					    INTEGER EXTERNAL(10),
		REPORT_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		STATUS_CODE					    INTEGER EXTERNAL(10),
		REQUIRED				    INTEGER EXTERNAL(1) "case :REQUIRED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		EMAIL						    CHAR(2000),
		ASSIGNED_IDENTIFER				    CHAR(2000),
		SUBMISSION_MESSAGE				    CHAR(2000),
		SUBMISSION_URL 				    CHAR(2000),
		MANUALLY_SELECTED				    INTEGER EXTERNAL(1) "case :MANUALLY_SELECTED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		WORKFLOW_ID					    INTEGER EXTERNAL(10),
		REVIEW_STATUS_CODE				    INTEGER EXTERNAL(10),
		CASE_NUMBER					    CHAR(2000),
		META_DATA					    CHAR(2000)
	)

