
LOAD DATA
	INFILE 'ext_ae_reporting_prds.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ext_ae_reporting_prds
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		DESCRIPTION					    CHAR(2000),
		CYCLE_NUMBER					    CHAR(2000),
		WORKFLOW_ID					    INTEGER EXTERNAL(10),
		REVIEW_STATUS				    CHAR(2000),
		TREATMENT_ASSIGNMENT_CODE			    CHAR(2000),
		OTHER_DESCRIPTION				    CHAR(2000),
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		NAME						    CHAR(2000),
		EXTERNAL_ID					    CHAR(2000),
		GRID_ID					    CHAR(2000),
		CREATION_DATE				   DATE "YYYY-MM-DD" NULLIF CREATION_DATE="",
		REPORTING_PERIOD_ID				    INTEGER EXTERNAL(10)
	)
