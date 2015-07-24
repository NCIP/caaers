
LOAD DATA
	INFILE 'disease_terms.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE disease_terms
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TERM						    CHAR(2000),
		CTEP_TERM					    CHAR(2000),
		CATEGORY_ID				    INTEGER EXTERNAL(10),
		MEDRA_CODE					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

