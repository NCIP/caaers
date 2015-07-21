OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_calendars.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_calendars
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		CALENDAR_NAME				    CHAR,
		 blob_filename     FILLER CHAR(100),
		CALENDAR				    LOBFILE(blob_filename) TERMINATED BY EOF
	)

