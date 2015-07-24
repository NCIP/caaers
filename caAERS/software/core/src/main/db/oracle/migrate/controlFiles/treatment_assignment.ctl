
LOAD DATA
	INFILE 'treatment_assignment.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE treatment_assignment
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CODE					    CHAR(2000),
		STUDY_ID				    INTEGER EXTERNAL(10),
		DOSE_LEVEL_ORDER				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR(4000),
		COMMENTS					    CHAR(4000),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		CTEP_DB_IDENTIFIER				    CHAR(2000)
	)

