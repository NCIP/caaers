OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\csm_user.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_user
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		USER_ID				    INTEGER EXTERNAL(38),
		LOGIN_NAME				    CHAR,
		FIRST_NAME				    CHAR,
		LAST_NAME				    CHAR,
		ORGANIZATION					    CHAR,
		DEPARTMENT					    CHAR,
		TITLE						    CHAR,
		PHONE_NUMBER					    CHAR,
		PASSWORD					    CHAR,
		EMAIL_ID					    CHAR,
		START_DATE					   DATE "YYYY-MM-DD" NULLIF START_DATE="",
		END_DATE					   DATE "YYYY-MM-DD" NULLIF END_DATE="",
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE="",
		MIGRATED_FLAG				    INTEGER EXTERNAL(1),
		PREMGRT_LOGIN_NAME				    CHAR
	)

