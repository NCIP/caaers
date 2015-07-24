
LOAD DATA
	INFILE 'report_delivery_defs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE report_delivery_defs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		FORMAT 				    CHAR(2000),
		ENTITY_NAME				    CHAR(2000),
		ENTITY_DESCRIPTION				    CHAR(2000),
		END_POINT				    CHAR(2000),
		END_POINT_TYPE 			    CHAR(2000),
		ENTITY_TYPE				    INTEGER EXTERNAL(10),
		RCT_ID 					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000),
		USER_NAME					    CHAR(2000),
		PASSWORD					    CHAR(2000)
	)

