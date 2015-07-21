OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\spa_pre_existing_conds.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE spa_pre_existing_conds
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ASSIGNMENT_ID				    INTEGER EXTERNAL(10),
		PRE_EXISTING_CONDITION_ID			    INTEGER EXTERNAL(10),
		OTHER						    CHAR,
		LIST_INDEX				    INTEGER EXTERNAL(10)
	)

