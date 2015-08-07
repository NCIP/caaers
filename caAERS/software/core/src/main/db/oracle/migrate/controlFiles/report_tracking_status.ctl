
LOAD DATA
	INFILE 'report_tracking_status.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_tracking_status
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STATUS 				    INTEGER EXTERNAL(1) "case :STATUS
													when 't'then to_number(1)
													when 'f'then to_number(0)
													END",
		STATUS_MESSAGE 				    CHAR(4000) "replace(:STATUS_MESSAGE,'\\n',chr(10))",
		RECORDED_TIME				   DATE "YYYY-MM-DD" NULLIF RECORDED_TIME="",
		GRID_ID					    CHAR(2000)
	)

