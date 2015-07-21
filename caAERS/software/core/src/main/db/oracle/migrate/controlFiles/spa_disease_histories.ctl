OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'spa_disease_histories.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE spa_disease_histories
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		CODED_PRIMARY_DISEASE_SITE_ID			    INTEGER EXTERNAL(10),
		STUDY_DISEASE_ID				    INTEGER EXTERNAL(10),
		OTHER_PRIMARY_DISEASE				    CHAR,
		OTHER_PRIMARY_DISEASE_SITE			    CHAR,
		DIAGNOSIS_DATE 				   DATE "YYYY-MM-DD" NULLIF DIAGNOSIS_DATE="",
		GRID_ID					    CHAR,
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		DIAGNOSIS_DAY					    INTEGER EXTERNAL(10),
		DIAGNOSIS_MONTH				    INTEGER EXTERNAL(10),
		DIAGNOSIS_YEAR 				    INTEGER EXTERNAL(10),
		DIAGNOSIS_ZONE 			    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10)
	)

