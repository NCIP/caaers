OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'searches.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE searches
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		LOGIN_ID				    CHAR,
		NAME					    CHAR,
		DESCRIPTION					    CHAR,
		CREATED_DATE				   DATE "YYYY-MM-DD" NULLIF CREATED_DATE="",
		CRITERIA_XML				    CHAR,
		GRID_ID					    CHAR
	)

