
LOAD DATA
	INFILE 'ctc_versions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ctc_versions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

