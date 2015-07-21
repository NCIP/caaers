OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_trigger_listeners.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_trigger_listeners
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		TRIGGER_LISTENER			    CHAR
	)

