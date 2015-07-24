
LOAD DATA
	INFILE 'reported_adverse_events.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE reported_adverse_events
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_VERSION_ID			    INTEGER EXTERNAL(10),
		ADVERSE_EVENT_ID			    INTEGER EXTERNAL(10)
	)

