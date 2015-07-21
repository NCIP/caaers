OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_surgery_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_surgery_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		TREATMENT_ARM					    CHAR,
		DESCRIPTION					    CHAR,
		INTERVENTION_DATE				   DATE "YYYY-MM-DD" NULLIF INTERVENTION_DATE="",
		LIST_INDEX					    INTEGER EXTERNAL(10),
		INTERVENTION_SITE_ID				    INTEGER EXTERNAL(10),
		STUDY_INTERVENTION_ID				    INTEGER EXTERNAL(10)
	)

