OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_role.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_role
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ROLE_ID				    INTEGER EXTERNAL(38),
		ROLE_NAME				    CHAR,
		ROLE_DESCRIPTION				    CHAR,
		APPLICATION_ID 			    INTEGER EXTERNAL(38),
		ACTIVE_FLAG				    INTEGER EXTERNAL(1),
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)

