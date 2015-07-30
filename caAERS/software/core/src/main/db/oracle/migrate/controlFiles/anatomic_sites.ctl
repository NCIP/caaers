
LOAD DATA
	INFILE 'anatomic_sites.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE anatomic_sites
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR(2000),
		CATEGORY				    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

