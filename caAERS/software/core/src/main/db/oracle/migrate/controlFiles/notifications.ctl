
LOAD DATA
	INFILE 'notifications.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE notifications
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CONTENT					    CHAR(2000),
		SUBJECT					    CHAR(2000),
		NAME						    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		ROLES						    CHAR(2000),
		EMAILS 					    CHAR(2000),
		STUDY_ID					    INTEGER EXTERNAL(10)
	)

