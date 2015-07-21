OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\audit_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE audit_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		IP_ADDRESS				    CHAR,
		USER_NAME				    CHAR,
		"TIME"					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF "TIME"="",
		CLASS_NAME				    CHAR,
		OPERATION				    CHAR,
		URL					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		OBJECT_ID				    INTEGER EXTERNAL(38)
	)

