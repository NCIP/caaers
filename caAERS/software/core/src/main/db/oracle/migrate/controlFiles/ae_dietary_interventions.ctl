OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ae_dietary_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_dietary_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR,
		STUDY_INTERVENTION_ID				    INTEGER EXTERNAL(10),
		LIST_INDEX					    INTEGER EXTERNAL(10)
	)

