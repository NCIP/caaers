OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\qrtz_blob_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_blob_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		 blob_filename     FILLER CHAR(100),
		BLOB_DATA					    LOBFILE(blob_filename) TERMINATED BY EOF
	)

