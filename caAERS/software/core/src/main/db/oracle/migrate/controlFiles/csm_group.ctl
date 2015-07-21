OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'csm_group.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE csm_group
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		GROUP_ID				    INTEGER EXTERNAL(38),
		GROUP_NAME				    CHAR,
		GROUP_DESC					    CHAR,
		UPDATE_DATE				   DATE "YYYY-MM-DD" NULLIF UPDATE_DATE="",
		APPLICATION_ID 			    INTEGER EXTERNAL(38)
	)

