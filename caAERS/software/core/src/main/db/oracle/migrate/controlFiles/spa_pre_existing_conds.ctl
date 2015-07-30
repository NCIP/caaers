
LOAD DATA
	INFILE 'spa_pre_existing_conds.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE spa_pre_existing_conds
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		PRE_EXISTING_CONDITION_ID			    INTEGER EXTERNAL(10),
		OTHER						    CHAR(2000),
		LIST_INDEX				    INTEGER EXTERNAL(10)
	)

