OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'treatment_assignment.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE treatment_assignment
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CODE					    CHAR,
		STUDY_ID				    INTEGER EXTERNAL(10),
		DOSE_LEVEL_ORDER				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR,
		COMMENTS					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CTEP_DB_IDENTIFIER				    CHAR
	)

