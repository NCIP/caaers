OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'qrtz_simple_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_simple_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		REPEAT_COUNT				    INTEGER EXTERNAL(7),
		REPEAT_INTERVAL			    INTEGER EXTERNAL(12),
		TIMES_TRIGGERED			    INTEGER EXTERNAL(7)
	)

