OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'csm_user_group.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_user_group
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		USER_GROUP_ID				    INTEGER EXTERNAL(38),
		USER_ID				    INTEGER EXTERNAL(38),
		GROUP_ID				    INTEGER EXTERNAL(38)
	)

