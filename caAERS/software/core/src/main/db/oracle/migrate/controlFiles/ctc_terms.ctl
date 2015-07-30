
LOAD DATA
	INFILE 'ctc_terms.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ctc_terms
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CATEGORY_ID				    INTEGER EXTERNAL(10),
		TERM					    CHAR(2000),
		SELECT_AE					    CHAR(2000),
		CTEP_TERM					    CHAR(2000),
		CTEP_CODE					    CHAR(2000),
		OTHER_REQUIRED 			    INTEGER EXTERNAL(1) "case :OTHER_REQUIRED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		TERM_DEFINITION				    CHAR(2000),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

