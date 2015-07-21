OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'participant_assignments.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE participant_assignments
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		STUDY_SITE_ID				    INTEGER EXTERNAL(10),
		PARTICIPANT_ID 			    INTEGER EXTERNAL(10),
		DATE_OF_ENROLLMENT			   DATE "YYYY-MM-DD" NULLIF DATE_OF_ENROLLMENT="",
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		LOAD_STATUS					    INTEGER EXTERNAL(10),
		STUDY_SUBJECT_IDENTIFIER			    CHAR,
		FIRST_COURSE_DATE				   DATE "YYYY-MM-DD" NULLIF FIRST_COURSE_DATE="",
		BASELINE_PERFORMANCE				    CHAR
	)

