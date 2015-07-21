OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'organization_index.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE organization_index
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID					    CHAR,
		ORGANIZATION_ID			    INTEGER EXTERNAL,
		GRID_ID					    CHAR,
		ROLE					    INTEGER EXTERNAL(10)
	)

