OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\report_delivery_defs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE report_delivery_defs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FORMAT 				    CHAR,
		ENTITY_NAME				    CHAR,
		ENTITY_DESCRIPTION				    CHAR,
		END_POINT				    CHAR,
		END_POINT_TYPE 			    CHAR,
		ENTITY_TYPE				    INTEGER EXTERNAL(10),
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR,
		USER_NAME					    CHAR,
		PASSWORD					    CHAR
	)

