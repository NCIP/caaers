OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\treatments.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE treatments
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10),
		REPORT_ID				    INTEGER EXTERNAL(10),
		FIRST_COURSE_DATE				   DATE "YYYY-MM-DD" NULLIF FIRST_COURSE_DATE="",
		ADVERSE_EVENT_COURSE_DATE			   DATE "YYYY-MM-DD" NULLIF ADVERSE_EVENT_COURSE_DATE="",
		ADVERSE_EVENT_COURSE_NUMBER			    INTEGER EXTERNAL(10),
		TREATMENT_ASSIGNMENT_ID			    INTEGER EXTERNAL(10),
		TOTAL_COURSES					    INTEGER EXTERNAL(10),
		TREATMENT_DESCRIPTION				    CHAR,
		INV_AGENT_ADMINSTRD				    INTEGER EXTERNAL(1) "case :INV_AGENT_ADMINSTRD
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		ADVERSE_EVENT_COURSE_DCODE		    INTEGER EXTERNAL(10)
	)

