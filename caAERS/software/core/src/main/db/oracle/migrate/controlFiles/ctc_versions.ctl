OPTIONS (SKIP=1)
LOAD DATA
	INFILE 'C:\Users\Janakiram_G\Downloads\ShellScripts\ctc_versions.csv'
	DISCARDMAX 9999
	APPEND INTO TABLE ctc_versions
	fields terminated by ','
	optionally enclosed by '"' AND '"'
	trailing NULLCOLS
	(
		ID					    INTEGER EXTERNAL(10),
		NAME					    CHAR,
		GRID_ID					    CHAR,
		VERSION				    INTEGER EXTERNAL(10)
	)

