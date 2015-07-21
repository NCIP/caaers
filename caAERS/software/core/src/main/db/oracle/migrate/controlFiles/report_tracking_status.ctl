OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\report_tracking_status.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_tracking_status
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STATUS 				    INTEGER EXTERNAL(1) "case :STATUS
													when 't'then to_number(1)
													when 'f'then to_number(0)
													END",
		STATUS_MESSAGE 				    CHAR,
		RECORDED_TIME				   DATE "YYYY-MM-DD" NULLIF RECORDED_TIME="",
		GRID_ID					    CHAR
	)

