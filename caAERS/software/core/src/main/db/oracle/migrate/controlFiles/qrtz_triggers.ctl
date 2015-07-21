OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		JOB_NAME				    CHAR,
		JOB_GROUP				    CHAR,
		IS_VOLATILE				    CHAR "case :IS_VOLATILE
												when 't'then to_char('1')
												when 'f'then to_char('0')
												END",
		DESCRIPTION					    CHAR,
		NEXT_FIRE_TIME 				    INTEGER EXTERNAL(13),
		PREV_FIRE_TIME 				    INTEGER EXTERNAL(13),
		PRIORITY					    INTEGER EXTERNAL(13),
		TRIGGER_STATE				    CHAR,
		TRIGGER_TYPE				    CHAR,
		START_TIME				    INTEGER EXTERNAL(13),
		END_TIME					    INTEGER EXTERNAL(13),
		CALENDAR_NAME					    CHAR,
		MISFIRE_INSTR					    INTEGER EXTERNAL(2),
		 blob_filename     FILLER CHAR(100),
		JOB_DATA					    LOBFILE(blob_filename) TERMINATED BY EOF
	)

