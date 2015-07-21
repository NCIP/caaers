OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'qrtz_blob_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_blob_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		BLOB_DATA					    CHAR(8000)
	)

