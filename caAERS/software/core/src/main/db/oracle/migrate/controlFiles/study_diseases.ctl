OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\study_diseases.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_diseases
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		LEAD_DISEASE					    INTEGER EXTERNAL(38) "case :LEAD_DISEASE
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		TERM_ID					    INTEGER EXTERNAL(10),
		TERM_TYPE					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

