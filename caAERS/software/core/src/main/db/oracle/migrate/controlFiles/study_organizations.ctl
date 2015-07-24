
LOAD DATA
	INFILE 'study_organizations.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE study_organizations
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		SITE_ID				    INTEGER EXTERNAL(10),
		STUDY_ID				    INTEGER EXTERNAL(10),
		STUDY_IDENTIFIER				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		ROLE_CODE					    CHAR(2000),
		IRB_APPROVAL_DATE				   DATE "YYYY-MM-DD" NULLIF IRB_APPROVAL_DATE="",
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		GRID_ID					    CHAR(2000),
		TYPE					    CHAR(2000),
		RETIRED_INDICATOR				    INTEGER EXTERNAL(1) "case :RETIRED_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END"
	)

