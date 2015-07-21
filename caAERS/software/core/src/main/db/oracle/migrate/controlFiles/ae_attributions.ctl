OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'ae_attributions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_attributions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ADVERSE_EVENT_ID			    INTEGER EXTERNAL(10),
		ATTRIBUTION_CODE				    INTEGER EXTERNAL(10),
		CAUSE_TYPE				    CHAR,
		CAUSE_ID					    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10)
	)

