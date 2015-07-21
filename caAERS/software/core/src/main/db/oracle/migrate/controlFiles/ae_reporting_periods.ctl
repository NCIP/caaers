OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_reporting_periods.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_reporting_periods
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		DESCRIPTION					    CHAR,
		CYCLE_NUMBER            INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		TREATMENT_ASSIGNMENT_ID			    INTEGER EXTERNAL(10),
		EPOCH_ID					    INTEGER EXTERNAL(10),
		WORKFLOW_ID					    INTEGER EXTERNAL(10),
		REVIEW_STATUS_CODE				    INTEGER EXTERNAL(10),
		TREATMENT_ASSIGNMENT_DESC			    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		EXTERNAL_ID					    CHAR,
		OLD_AE_MAPPING 				    CHAR
	)

