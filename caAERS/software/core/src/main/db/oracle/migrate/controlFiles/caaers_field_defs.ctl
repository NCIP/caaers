OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\caaers_field_defs.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE caaers_field_defs
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		TAB_NAME				    CHAR,
		FIELD_PATH				    CHAR,
		MANDATORY				    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		GRID_ID					    CHAR
	)

