OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'identifiers.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE identifiers
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VALUE						    CHAR,
		TYPE						    CHAR,
		SYSTEM_NAME					    CHAR,
		PARTICIPANT_ID 				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		PRIMARY_INDICATOR				    INTEGER EXTERNAL(38) "case :PRIMARY_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		STU_ID 					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		ORGANIZATION_ID				    INTEGER EXTERNAL(10),
		DISCRIMINATOR_COLUMN				    INTEGER EXTERNAL(10)
	)

