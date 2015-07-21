OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\notifications.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE notifications
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CONTENT					    CHAR,
		SUBJECT					    CHAR,
		NAME						    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ROLES						    CHAR,
		EMAILS 					    CHAR,
		STUDY_ID					    INTEGER EXTERNAL(10)
	)

