OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'qrtz_cron_triggers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE qrtz_cron_triggers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		TRIGGER_NAME				    CHAR,
		TRIGGER_GROUP				    CHAR,
		CRON_EXPRESSION			    CHAR,
		TIME_ZONE_ID					    CHAR
	)

