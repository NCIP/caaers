
LOAD DATA
	INFILE 'audit_event_values.txt'
	DISCARDMAX 9999
	TRUNCATE
    INTO TABLE audit_event_values
	fields terminated by '\t'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL,
		AUDIT_EVENT_ID 			    INTEGER EXTERNAL,
		ATTRIBUTE_NAME 			    CHAR(2000),
		PREVIOUS_VALUE 				    CHAR(4000) "replace(:PREVIOUS_VALUE,'\\n',chr(10))",
		NEW_VALUE					    CHAR(4000) "replace(:NEW_VALUE,'\\n',chr(10))",
		VERSION				    INTEGER EXTERNAL 
	)

