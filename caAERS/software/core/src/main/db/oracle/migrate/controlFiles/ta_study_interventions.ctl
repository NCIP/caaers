OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ta_study_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ta_study_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TREATMENT_ASSIGNMENT_ID			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

