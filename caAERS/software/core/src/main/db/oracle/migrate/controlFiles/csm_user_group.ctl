
LOAD DATA
	INFILE 'csm_user_group.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE csm_user_group
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		USER_GROUP_ID				    INTEGER EXTERNAL(38),
		USER_ID				    INTEGER EXTERNAL(38),
		GROUP_ID				    INTEGER EXTERNAL(38)
	)

