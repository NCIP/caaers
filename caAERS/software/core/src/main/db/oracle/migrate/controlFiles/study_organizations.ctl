OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\study_organizations.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE study_organizations
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SITE_ID				    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		STUDY_IDENTIFIER				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ROLE_CODE					    CHAR,
		IRB_APPROVAL_DATE				   DATE "YYYY-MM-DD" NULLIF IRB_APPROVAL_DATE="",
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		GRID_ID					    CHAR,
		TYPE					    CHAR,
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

