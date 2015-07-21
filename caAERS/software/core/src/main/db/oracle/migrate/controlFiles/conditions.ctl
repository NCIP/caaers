OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\conditions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE conditions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		VERSION				    INTEGER EXTERNAL(10),
		CONDITION_NAME 				    CHAR,
		GRID_ID					    CHAR
	)

