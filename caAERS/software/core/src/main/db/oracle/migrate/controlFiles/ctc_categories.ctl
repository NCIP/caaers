
LOAD DATA
	INFILE 'ctc_categories.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ctc_categories
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION_ID				    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

