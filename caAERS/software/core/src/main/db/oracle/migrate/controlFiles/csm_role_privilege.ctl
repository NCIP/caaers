OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'csm_role_privilege.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_role_privilege
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ROLE_PRIVILEGE_ID			    INTEGER EXTERNAL(38),
		ROLE_ID				    INTEGER EXTERNAL(38),
		PRIVILEGE_ID				    INTEGER EXTERNAL(38)
	)

