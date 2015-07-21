OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'audit_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE audit_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL,
		IP_ADDRESS				    CHAR,
		USER_NAME				    CHAR,
		"TIME"					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF "TIME"="",
		CLASS_NAME				    CHAR,
		OBJECT_ID				    INTEGER EXTERNAL(38),
		OPERATION				    CHAR,
		URL					    CHAR,
		VERSION				    INTEGER EXTERNAL	
	)

