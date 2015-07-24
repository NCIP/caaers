
LOAD DATA
	INFILE 'identifiers.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE identifiers
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VALUE						    CHAR(2000),
		TYPE						    CHAR(2000),
		SYSTEM_NAME					    CHAR(2000),
		PARTICIPANT_ID 				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		PRIMARY_INDICATOR				    INTEGER EXTERNAL(38) "case :PRIMARY_INDICATOR
															when 't'then to_number(1)
															when 'f'then to_number(0)
															END",
		STU_ID 					    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		ORGANIZATION_ID				    INTEGER EXTERNAL(10),
		DISCRIMINATOR_COLUMN				    INTEGER EXTERNAL(10)
	)

