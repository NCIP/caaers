
LOAD DATA
	INFILE 'ae_labs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE ae_labs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		LIST_INDEX				    INTEGER EXTERNAL(10),
		UNITS						    CHAR(2000),
		BASELINE_DATE					   DATE "YYYY-MM-DD" NULLIF BASELINE_DATE="",
		BASELINE_VALUE 				    CHAR(2000),
		NADIR_DATE					   DATE "YYYY-MM-DD" NULLIF NADIR_DATE="",
		NADIR_VALUE					    CHAR(2000),
		RECOVERY_DATE					   DATE "YYYY-MM-DD" NULLIF RECOVERY_DATE="",
		RECOVERY_VALUE 				    CHAR(2000),
		GRID_ID					    CHAR(2000),
		OTHER						    CHAR(2000),
		LAB_TERM_ID					    INTEGER EXTERNAL(10),
		SITE						    CHAR(2000),
		LAB_DATE					   DATE "YYYY-MM-DD" NULLIF LAB_DATE="",
		INFECTIOUS_AGENT				    CHAR(2000),
		BASELINE_ZONE					    INTEGER EXTERNAL(10),
		RECOVERY_ZONE					    INTEGER EXTERNAL(10),
		NADIR_ZONE					    INTEGER EXTERNAL(10),
		NORMAL_RANGE					    CHAR(2000)
	)
