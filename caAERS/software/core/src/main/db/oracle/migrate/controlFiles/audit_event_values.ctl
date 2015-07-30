
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
		PREVIOUS_VALUE 				    CHAR(4000),
		NEW_VALUE					    CHAR(4000),
		VERSION				    INTEGER EXTERNAL 
	)

