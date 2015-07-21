OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'site_rs_staff_roles.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE site_rs_staff_roles
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		ROLE_CODE				    CHAR,
		SITE_RESEARCH_STAFFS_ID		    INTEGER EXTERNAL(10),
		START_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF START_DATE="",
		END_DATE					    TIMESTAMP(6) "YYYY-MM-DD HH24:MI:SS.FF6" NULLIF END_DATE="",
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

