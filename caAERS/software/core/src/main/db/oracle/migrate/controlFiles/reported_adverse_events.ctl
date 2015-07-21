OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'reported_adverse_events.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE reported_adverse_events
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_VERSION_ID			    INTEGER EXTERNAL(10),
		ADVERSE_EVENT_ID			    INTEGER EXTERNAL(10)
	)

