OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_fired_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_fired_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ENTRY_ID				    CHAR,
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		IS_VOLATILE				    CHAR "case :IS_VOLATILE
											when 't'then to_char('1')
											when 'f'then to_char('0')
											END",
		INSTANCE_NAME				    CHAR,
		FIRED_TIME				    INTEGER EXTERNAL(13),
		PRIORITY				    INTEGER EXTERNAL(13),
		STATE					    CHAR,
		JOB_NAME					    CHAR,
		JOB_GROUP					    CHAR,
		IS_STATEFUL					    CHAR,
		REQUESTS_RECOVERY				    CHAR
	)

