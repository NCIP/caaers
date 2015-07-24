LOAD DATA
	INFILE 'planned_notifications.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE planned_notifications
	fields terminated by '\t'	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		INDEX_ON_TIME_SCALE			    INTEGER EXTERNAL(10),
		SUBJECT				    CHAR(2000),
		DTYPE					    CHAR(2000),
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		BODY						    CHAR(4000) "replace(:BODY, '\n', '')",
		RPT_DEF_NOTIFICATION_TYPE			    INTEGER EXTERNAL(10)
	)

