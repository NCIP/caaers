OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ext_ae_reporting_prds.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ext_ae_reporting_prds
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR,
		CYCLE_NUMBER					    CHAR,
		WORKFLOW_ID					    INTEGER EXTERNAL(10),
		REVIEW_STATUS				    CHAR,
		TREATMENT_ASSIGNMENT_CODE			    CHAR,
		OTHER_DESCRIPTION				    CHAR,
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		NAME						    CHAR,
		EXTERNAL_ID					    CHAR,
		GRID_ID					    CHAR,
		CREATION_DATE				   DATE "YYYY-MM-DD" NULLIF CREATION_DATE="",
		REPORTING_PERIOD_ID				    INTEGER EXTERNAL(10)
	)

