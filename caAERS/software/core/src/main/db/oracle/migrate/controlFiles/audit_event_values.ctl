OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\audit_event_values.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE audit_event_values
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL,
		AUDIT_EVENT_ID 			    INTEGER EXTERNAL,
		ATTRIBUTE_NAME 			    CHAR,
		PREVIOUS_VALUE 				    CHAR,
		NEW_VALUE					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

