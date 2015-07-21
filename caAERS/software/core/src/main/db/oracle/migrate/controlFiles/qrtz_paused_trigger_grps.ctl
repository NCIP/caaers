OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'qrtz_paused_trigger_grps.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_paused_trigger_grps
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_GROUP				    CHAR
	)

