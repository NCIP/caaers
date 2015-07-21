OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\study_amendments.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_amendments
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		AMENDMENT_VERSION				    INTEGER EXTERNAL(10),
		AMENDMENT_DATE 				   DATE "YYYY-MM-DD" NULLIF AMENDMENT_DATE="",
		COMMENTS					    CHAR,
		IRB_APPROVAL_DATE			   DATE "YYYY-MM-DD" NULLIF IRB_APPROVAL_DATE="",
		STU_ID 				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

