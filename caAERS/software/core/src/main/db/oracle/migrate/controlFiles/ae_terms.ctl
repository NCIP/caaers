OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ae_terms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_terms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		ADVERSE_EVENT_ID			    INTEGER EXTERNAL(10),
		TERM_ID					    INTEGER EXTERNAL(10),
		TERM_TYPE					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

