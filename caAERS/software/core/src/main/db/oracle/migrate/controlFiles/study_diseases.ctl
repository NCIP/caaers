
LOAD DATA
	INFILE 'study_diseases.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_diseases
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		LEAD_DISEASE					    INTEGER EXTERNAL(38) "case :LEAD_DISEASE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		TERM_ID					    INTEGER EXTERNAL(10),
		TERM_TYPE					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

