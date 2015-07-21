OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_scheduler_state.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_scheduler_state
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		INSTANCE_NAME				    CHAR,
		LAST_CHECKIN_TIME			    INTEGER EXTERNAL(13),
		CHECKIN_INTERVAL			    INTEGER EXTERNAL(13)
	)

