OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'other_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE other_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		STUDY_THERAPY_TYPE			    INTEGER EXTERNAL(10),
		NAME						    CHAR,
		DESCRIPTION					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR
	)

