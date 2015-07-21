OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\planned_notifications.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE planned_notifications
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		INDEX_ON_TIME_SCALE			    INTEGER EXTERNAL(10),
		SUBJECT				    CHAR,
		DTYPE					    CHAR,
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		BODY						    CHAR,
		RPT_DEF_NOTIFICATION_TYPE			    INTEGER EXTERNAL(10)
	)

