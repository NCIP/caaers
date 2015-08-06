
LOAD DATA
	INFILE 'integration_log_details.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE integration_log_details
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		LOG_ID 				    INTEGER EXTERNAL(10),
		BUSINESS_ID					    CHAR(2000),
		OUTCOME					    CHAR(100000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		SYNCH_STATUS					    CHAR(2000),
		FAILED 				    INTEGER EXTERNAL(1) "case :FAILED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

