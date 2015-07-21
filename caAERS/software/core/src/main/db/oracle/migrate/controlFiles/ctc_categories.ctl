OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ctc_categories.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ctc_categories
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION_ID				    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

