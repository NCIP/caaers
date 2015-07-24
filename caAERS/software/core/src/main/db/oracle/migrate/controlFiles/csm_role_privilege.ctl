
LOAD DATA
	INFILE 'csm_role_privilege.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE csm_role_privilege
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ROLE_PRIVILEGE_ID			    INTEGER EXTERNAL(38),
		ROLE_ID				    INTEGER EXTERNAL(38),
		PRIVILEGE_ID				    INTEGER EXTERNAL(38)
	)

