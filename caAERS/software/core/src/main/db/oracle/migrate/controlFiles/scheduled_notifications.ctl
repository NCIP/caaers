OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\scheduled_notifications.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE scheduled_notifications
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SCHEDULED_ON				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF SCHEDULED_ON="",
		CREATED_ON				    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF CREATED_ON="",
		TO_ADDR					    CHAR,
		FROM_ADDR					    CHAR,
		DELIVERY_STATUS_CODE			    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		DTYPE					    CHAR,
		PLNF_ID					    INTEGER EXTERNAL(10),
		RPSH_ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		SUBJECT					    CHAR,
		BODY						    CHAR
	)

