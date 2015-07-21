OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\integration_log_details.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE integration_log_details
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		LOG_ID 				    INTEGER EXTERNAL(10),
		BUSINESS_ID					    CHAR,
		OUTCOME					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		SYNCH_STATUS					    CHAR,
		FAILED 				    INTEGER EXTERNAL(1) "case :FAILED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

