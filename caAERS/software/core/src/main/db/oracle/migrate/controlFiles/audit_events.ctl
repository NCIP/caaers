
LOAD DATA
	INFILE 'audit_events.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE audit_events
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL,
		IP_ADDRESS				    CHAR(2000),
		USER_NAME				    CHAR(2000),
		"TIME"					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF "TIME"="",
		CLASS_NAME				    CHAR(2000),
		OBJECT_ID				    INTEGER EXTERNAL(38),
		OPERATION				    CHAR(2000),
		URL					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL		
	)

