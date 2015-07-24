
LOAD DATA
	INFILE 'caaers_field_defs.txt'
	DISCARDMAX 9999
	TRUNCATE INTO TABLE caaers_field_defs
	fields terminated by '\t'
	
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TAB_NAME				    CHAR(2000),
		FIELD_PATH				    CHAR(2000),
		MANDATORY				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR(2000)
	)

