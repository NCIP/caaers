
LOAD DATA
	INFILE 'other_interventions.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE other_interventions
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		STUDY_THERAPY_TYPE			    INTEGER EXTERNAL(10),
		NAME						    CHAR(2000),
		DESCRIPTION					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		GRID_ID					    CHAR(2000)
	)

