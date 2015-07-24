
LOAD DATA
	INFILE 'ta_study_interventions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ta_study_interventions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TREATMENT_ASSIGNMENT_ID			    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10)
	)

