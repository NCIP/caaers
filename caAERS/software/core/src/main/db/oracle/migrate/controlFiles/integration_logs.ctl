
LOAD DATA
	INFILE 'integration_logs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE integration_logs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		LOGGED_ON				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LOGGED_ON="",
		CORRELATION_ID 			    CHAR(2000),
		NOTES						    CHAR(4000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		ENTITY 					    CHAR(2000),
		OPERATION					    CHAR(2000),
		SYNCH_STATUS					    CHAR(2000)
	)

