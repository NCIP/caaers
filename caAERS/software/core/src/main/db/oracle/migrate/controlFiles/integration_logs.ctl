OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\integration_logs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE integration_logs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		LOGGED_ON				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF LOGGED_ON="",
		CORRELATION_ID 			    CHAR,
		NOTES						    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ENTITY 					    CHAR,
		OPERATION					    CHAR,
		SYNCH_STATUS					    CHAR
	)

