OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\rule_sets.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE rule_sets
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		STUDY_ID					    INTEGER EXTERNAL(10),
		STAGED_VERSION 			    INTEGER EXTERNAL(10),
		DEPLOYED_VERSION			    INTEGER EXTERNAL(10),
		ORGANIZATION_ID				    INTEGER EXTERNAL(10),
		RULE_TYPE_NAME 			    CHAR,
		RULE_LEVEL_NAME				    CHAR,
		STATUS 				    CHAR,
		RULE_BINDURI				    CHAR,
		GRID_ID					    CHAR
	)

