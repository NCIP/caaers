OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'ta_other_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ta_other_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_OTHER_INTERVENTION_ID			    INTEGER EXTERNAL(10)
	)

