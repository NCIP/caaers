OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'csm_privilege.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_privilege
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		PRIVILEGE_ID				    INTEGER EXTERNAL(38),
		PRIVILEGE_NAME 			    CHAR,
		PRIVILEGE_DESCRIPTION				    CHAR,
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)

