
LOAD DATA
	INFILE 'other_causes.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE other_causes
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		REPORT_ID				    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		CAUSE_TEXT					    CHAR(2000)
	)

