
LOAD DATA
	INFILE 'ae_behavioral_interventions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_behavioral_interventions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR(2000),
		STUDY_INTERVENTION_ID				    INTEGER EXTERNAL(10),
		LIST_INDEX					    INTEGER EXTERNAL(10)
	)

