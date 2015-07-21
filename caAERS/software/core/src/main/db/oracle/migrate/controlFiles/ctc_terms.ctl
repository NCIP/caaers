OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ctc_terms.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ctc_terms
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CATEGORY_ID				    INTEGER EXTERNAL(10),
		TERM					    CHAR,
		SELECT_AE					    CHAR,
		CTEP_TERM					    CHAR,
		CTEP_CODE					    CHAR,
		OTHER_REQUIRED 			    INTEGER EXTERNAL(1) "case :OTHER_REQUIRED
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		TERM_DEFINITION				    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

