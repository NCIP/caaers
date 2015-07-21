OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'disease_terms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE disease_terms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM						    CHAR,
		CTEP_TERM					    CHAR,
		CATEGORY_ID				    INTEGER EXTERNAL(10),
		MEDRA_CODE					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

