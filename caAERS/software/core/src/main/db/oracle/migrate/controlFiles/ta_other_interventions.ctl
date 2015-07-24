
LOAD DATA
	INFILE 'ta_other_interventions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ta_other_interventions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_OTHER_INTERVENTION_ID			    INTEGER EXTERNAL(10)
	)

