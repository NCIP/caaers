
LOAD DATA
	INFILE 'report_format.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_format
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		REPORT_FORMAT_TYPE			    CHAR(2000),
		STUDY_ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)

