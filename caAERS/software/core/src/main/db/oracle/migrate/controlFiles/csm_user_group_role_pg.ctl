OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'csm_user_group_role_pg.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_user_group_role_pg
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		USER_GROUP_ROLE_PG_ID			    INTEGER EXTERNAL(38),
		USER_ID					    INTEGER EXTERNAL(38),
		GROUP_ID					    INTEGER EXTERNAL(38),
		ROLE_ID				    INTEGER EXTERNAL(38),
		PROTECTION_GROUP_ID			    INTEGER EXTERNAL(38),
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE=""
	)

