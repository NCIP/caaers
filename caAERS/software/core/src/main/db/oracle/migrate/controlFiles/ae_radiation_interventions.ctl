OPTIONS (SKIP=1) 
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ae_radiation_interventions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ae_radiation_interventions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		RADIATION_ADMINISTRATION_CODE			    INTEGER EXTERNAL(10),
		DOSAGE 					    CHAR,
		DOSAGE_UNIT					    CHAR,
		LAST_TREATMENT_DATE				   DATE "YYYY-MM-DD" NULLIF LAST_TREATMENT_DATE="",
		FRACTION_NUMBER				    CHAR,
		DAYS_ELAPSED					    CHAR,
		ADJUSTMENT					    CHAR,
		LIST_INDEX					    INTEGER EXTERNAL(10),
		STUDY_INTERVENTION_ID				    INTEGER EXTERNAL(10)
	)

